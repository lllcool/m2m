package com.post.m2m.service.member;

import com.post.common.constant.ErrorCode;
import com.post.common.exception.BusinessException;
import com.post.common.pojo.vo.PageVO;
import com.post.common.util.UUIDUtil;
import com.post.m2m.dao.member.MemberDAO;
import com.post.m2m.pojo.dto.member.MemberAddDTO;
import com.post.m2m.pojo.dto.member.MemberUpdateDTO;
import com.post.m2m.pojo.mapper.member.MemberMapper;
import com.post.m2m.pojo.po.member.MemberPO;
import com.post.m2m.pojo.qo.member.MemberQO;
import com.post.m2m.pojo.vo.member.MemberListVO;
import com.post.m2m.pojo.vo.member.MemberShowVO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 【Member】增删改查服务
 *
 * @author ljm
 * @date 2021/01/25
 */
@Service
public class MemberService {

    @Autowired
    private MemberDAO memberDAO;


    /**
     * 新增【Member】
     *
     * @param memberDTO
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public MemberPO save(MemberAddDTO memberDTO) {
        MemberPO member = MemberMapper.INSTANCE.fromAddDTO(memberDTO);
        member.setId(UUIDUtil.getUUID());
        memberDAO.save(member);
        return member;
    }

    /**
     * 批量新增【Member】
     *
     * @param list
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public int batchSave(List<MemberAddDTO> list) {
        if (CollectionUtils.isEmpty(list)) {
            return 0;
        }
        for (MemberAddDTO addDTO : list) {
            this.save(addDTO);
        }
        return list.size();
    }

    /**
     * 修改【Member】
     *
     * @param memberUpdateDTO
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public MemberPO update(MemberUpdateDTO memberUpdateDTO) {
        String id = memberUpdateDTO.getId();
        MemberPO member = this.getMember(id, true);
        MemberMapper.INSTANCE.setUpdateDTO(member, memberUpdateDTO);
        memberDAO.update(member);
        return member;
    }

    /**
     * 查询分页列表
     *
     * @param memberQO
     * @return
     */
    public PageVO<MemberListVO> list(MemberQO memberQO) {
        PageVO<MemberListVO> page = memberDAO.findByPage(memberQO);
        return page;
    }

    /**
     * 根据主键获取【Member】
     *
     * @param id 主键
     * @param force 是否强制获取
     * @return
     */
    public MemberPO getMember(String id, boolean force) {
        MemberPO member = memberDAO.findById(id);
        if (force && member == null) {
            throw new BusinessException(ErrorCode.RECORD_NOT_FIND);
        }
        return member;
    }


    /**
     * 查询【Member】详情
     *
     * @param id
     * @return
     */
    public MemberShowVO show(String id) {
        MemberPO member = this.getMember(id, true);
        MemberShowVO showVO = MemberMapper.INSTANCE.toShowVO(member);
        return showVO;
    }

    /**
     * 删除【Member】
     *
     * @param ids
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public int delete(String... ids) {
        int count = 0;
        for (String id : ids) {
            count += memberDAO.delete(id);
        }
        return count;
    }


}


