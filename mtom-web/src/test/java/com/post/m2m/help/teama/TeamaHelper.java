package com.post.m2m.help.teama;

import com.post.m2m.pojo.dto.teama.*;
import com.post.m2m.pojo.po.teama.*;
import com.post.m2m.service.teama.TeamaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.post.m2m.pojo.example.teama.TeamaExample.*;

@Component
public class TeamaHelper {

    @Autowired
    private TeamaService teamaService;

    /**
     * 生成add测试数据
     *
     * @return
     */
    public TeamaAddDTO getTeamaAddDTO() {
        TeamaAddDTO dto = new TeamaAddDTO();
        dto.setTeamName(E_TEAM_NAME);
        return dto;
    }


    /**
     * 生成update测试数据
     *
     * @return
     */
    public TeamaUpdateDTO getTeamaUpdateDTO(TeamaPO teama) {
        TeamaUpdateDTO dto = new TeamaUpdateDTO();
        dto.setId(teama.getId());
        dto.setTeamName(teama.getTeamName());
        return dto;
    }

    /**
     * 保存示例
     *
     * @return
     */
    public TeamaPO saveTeamaExample() {
        TeamaAddDTO addDTO = this.getTeamaAddDTO();
        return teamaService.save(addDTO);
    }


}

