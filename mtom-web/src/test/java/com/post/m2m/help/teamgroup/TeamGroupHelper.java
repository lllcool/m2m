package com.post.m2m.help.teamgroup;

import com.post.m2m.pojo.dto.teamgroup.*;
import com.post.m2m.pojo.po.teamgroup.*;
import com.post.m2m.service.teamgroup.TeamGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.post.m2m.pojo.example.teamgroup.TeamGroupExample.*;

@Component
public class TeamGroupHelper {

    @Autowired
    private TeamGroupService teamGroupService;

    /**
     * 生成add测试数据
     *
     * @return
     */
    public TeamGroupAddDTO getTeamGroupAddDTO(String teamId) {
        TeamGroupAddDTO dto = new TeamGroupAddDTO();
        dto.setCount(E_COUNT);
        dto.setTeamId(teamId);
        return dto;
    }


    /**
     * 生成update测试数据
     *
     * @return
     */
    public TeamGroupUpdateDTO getTeamGroupUpdateDTO(TeamGroupPO teamGroup) {
        TeamGroupUpdateDTO dto = new TeamGroupUpdateDTO();
        dto.setId(teamGroup.getId());
        dto.setCount(teamGroup.getCount());
        dto.setTeamId(teamGroup.getTeamId());
        return dto;
    }

    /**
     * 保存示例
     *
     * @return
     */
    public TeamGroupPO saveTeamGroupExample(String teamId) {
        TeamGroupAddDTO addDTO = this.getTeamGroupAddDTO(teamId);
        return teamGroupService.save(addDTO);
    }


}

