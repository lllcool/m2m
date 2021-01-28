package com.post.m2m.pojo.qo.teamgroup;

import com.post.common.pojo.qo.PageQO;
import com.post.m2m.pojo.example.teamgroup.TeamaExample;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import static com.post.m2m.pojo.example.teamgroup.TeamGroupExample.*;

/**
 * 查询【teamGroup】的参数
 *
 * @author ljm
 * @date 2021/01/25
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class TeamGroupQO extends PageQO {

    @ApiParam(value = N_TEAM_ID, example = E_TEAM_ID)
    @Length(max = 32, message = "teamId最大长度不能超过{max}")
    private String teamId;

    @ApiParam(value = TeamaExample.N_TEAM_NAME, example = TeamaExample.E_TEAM_NAME)
    @Length(max = 20, message = "teamName最大长度不能超过{max}")
    private String teamName;


}

