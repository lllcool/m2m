package com.post.m2m.service.teamgroup;

import com.post.common.constant.ErrorCode;
import com.post.common.exception.BusinessException;
import com.post.common.pojo.vo.PageVO;
import com.post.common.util.UUIDUtil;
import com.post.m2m.dao.teamgroup.TeamGroupDAO;
import com.post.m2m.dao.teamgroup.TeamaDAO;
import com.post.m2m.pojo.dto.teamgroup.TeamGroupAddDTO;
import com.post.m2m.pojo.dto.teamgroup.TeamGroupUpdateDTO;
import com.post.m2m.pojo.mapper.teamgroup.TeamGroupMapper;
import com.post.m2m.pojo.po.teamgroup.TeamGroupPO;
import com.post.m2m.pojo.po.teamgroup.TeamaPO;
import com.post.m2m.pojo.qo.teamgroup.TeamGroupQO;
import com.post.m2m.pojo.vo.teamgroup.TeamGroupListVO;
import com.post.m2m.pojo.vo.teamgroup.TeamGroupShowVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * 【teamGroup】增删改查服务
 *
 * @author ljm
 * @date 2021/01/25
 */
@Service
public class TeamGroupService {

    @Autowired
    private TeamGroupDAO teamGroupDAO;
    @Autowired
    private TeamaDAO teamaDAO;


    /**
     * 新增【teamGroup】
     *
     * @param teamGroupDTO
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public TeamGroupPO save(TeamGroupAddDTO teamGroupDTO) {
        TeamGroupPO teamGroup = TeamGroupMapper.INSTANCE.fromAddDTO(teamGroupDTO);
        if (teamGroup.getTeamId() != null) {
            Assert.isTrue(teamaDAO.exist(teamGroup.getTeamId()), "团队有误");
        }
        teamGroup.setId(UUIDUtil.getUUID());
        teamGroupDAO.save(teamGroup);
        return teamGroup;
    }

    /**
     * 修改【teamGroup】
     *
     * @param teamGroupUpdateDTO
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public TeamGroupPO update(TeamGroupUpdateDTO teamGroupUpdateDTO) {
        String id = teamGroupUpdateDTO.getId();
        TeamGroupPO teamGroup = this.getTeamGroup(id, true);
        TeamGroupMapper.INSTANCE.setUpdateDTO(teamGroup, teamGroupUpdateDTO);
        if (teamGroup.getTeamId() != null) {
            Assert.isTrue(teamaDAO.exist(teamGroup.getTeamId()), "团队有误");
        }
        teamGroupDAO.update(teamGroup);
        return teamGroup;
    }

    /**
     * 查询分页列表
     *
     * @param teamGroupQO
     * @return
     */
    public PageVO<TeamGroupListVO> list(TeamGroupQO teamGroupQO) {
        PageVO<TeamGroupListVO> page = teamGroupDAO.findByPage(teamGroupQO);
        return page;
    }

    /**
     * 根据主键获取【teamGroup】
     *
     * @param id 主键
     * @param force 是否强制获取
     * @return
     */
    public TeamGroupPO getTeamGroup(String id, boolean force) {
        TeamGroupPO teamGroup = teamGroupDAO.findById(id);
        if (force && teamGroup == null) {
            throw new BusinessException(ErrorCode.RECORD_NOT_FIND);
        }
        return teamGroup;
    }


    /**
     * 查询【teamGroup】详情
     *
     * @param id
     * @return
     */
    public TeamGroupShowVO show(String id) {
        TeamGroupPO teamGroup = this.getTeamGroup(id, true);
        TeamGroupShowVO showVO = TeamGroupMapper.INSTANCE.toShowVO(teamGroup);
        if (teamGroup.getTeamId() != null) {
            TeamaPO _teamaPO = teamaDAO.findById(teamGroup.getTeamId());
            showVO.setTeamName(_teamaPO.getTeamName());
        }
        return showVO;
    }

    /**
     * 删除【teamGroup】
     *
     * @param ids
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public int delete(String... ids) {
        int count = 0;
        for (String id : ids) {
            count += teamGroupDAO.delete(id);
        }
        return count;
    }


}


