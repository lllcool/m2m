package com.post.m2m.service.teama;

import com.post.common.constant.ErrorCode;
import com.post.common.exception.BusinessException;
import com.post.common.pojo.qo.OptionQO;
import com.post.common.pojo.vo.OptionVO;
import com.post.common.pojo.vo.PageVO;
import com.post.common.util.UUIDUtil;
import com.post.m2m.dao.teama.TeamGroup3DAO;
import com.post.m2m.dao.teama.TeamaDAO;
import com.post.m2m.pojo.dto.teama.TeamaAddDTO;
import com.post.m2m.pojo.dto.teama.TeamaUpdateDTO;
import com.post.m2m.pojo.mapper.teama.TeamaMapper;
import com.post.m2m.pojo.po.teama.TeamaPO;
import com.post.m2m.pojo.qo.teama.TeamaQO;
import com.post.m2m.pojo.vo.teama.TeamaListVO;
import com.post.m2m.pojo.vo.teama.TeamaShowVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 【teama】增删改查服务
 *
 * @author ljm
 * @date 2021/01/25
 */
@Service
public class TeamaService {

    @Autowired
    private TeamGroup3DAO teamGroup3DAO;
    @Autowired
    private TeamaDAO teamaDAO;


    /**
     * 新增【teama】
     *
     * @param teamaDTO
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public TeamaPO save(TeamaAddDTO teamaDTO) {
        TeamaPO teama = TeamaMapper.INSTANCE.fromAddDTO(teamaDTO);
        teama.setId(UUIDUtil.getUUID());
        teamaDAO.save(teama);
        return teama;
    }

    /**
     * 修改【teama】
     *
     * @param teamaUpdateDTO
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public TeamaPO update(TeamaUpdateDTO teamaUpdateDTO) {
        String id = teamaUpdateDTO.getId();
        TeamaPO teama = this.getTeama(id, true);
        TeamaMapper.INSTANCE.setUpdateDTO(teama, teamaUpdateDTO);
        teamaDAO.update(teama);
        return teama;
    }

    /**
     * 查询分页列表
     *
     * @param teamaQO
     * @return
     */
    public PageVO<TeamaListVO> list(TeamaQO teamaQO) {
        PageVO<TeamaListVO> page = teamaDAO.findByPage(teamaQO);
        return page;
    }

    /**
     * 查询【teama】选项列表
     *
     * @return
     */
    public List<OptionVO<String, String>> findOptions(OptionQO<String, String> qo) {
        List<OptionVO<String, String>> options = teamaDAO.findOptions(qo);
        return options;
    }

    /**
     * 根据主键获取【teama】
     *
     * @param id 主键
     * @param force 是否强制获取
     * @return
     */
    public TeamaPO getTeama(String id, boolean force) {
        TeamaPO teama = teamaDAO.findById(id);
        if (force && teama == null) {
            throw new BusinessException(ErrorCode.RECORD_NOT_FIND);
        }
        return teama;
    }


    /**
     * 查询【teama】详情
     *
     * @param id
     * @return
     */
    public TeamaShowVO show(String id) {
        TeamaPO teama = this.getTeama(id, true);
        TeamaShowVO showVO = TeamaMapper.INSTANCE.toShowVO(teama);
        return showVO;
    }

    /**
     * 删除【teama】
     *
     * @param ids
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public int delete(String... ids) {
        int count = 0;
        for (String id : ids) {
            this.checkDeleteByTeamGroup(id);
            count += teamaDAO.delete(id);
        }
        return count;
    }

    /**
     * 校验是否存在【teamGroup】关联
     *
     * @param id
     */
    private void checkDeleteByTeamGroup(String id) {
        int count = teamGroupDAO.getCountByTeamId(id);
        if (count > 0) {
            throw new BusinessException(ErrorCode.CASCADE_DELETE_ERROR);
        }
    }


}


