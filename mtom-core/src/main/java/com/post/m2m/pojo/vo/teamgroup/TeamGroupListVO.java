package com.post.m2m.pojo.vo.teamgroup;

import com.post.common.pojo.vo.AbstractVO;
import com.post.m2m.pojo.example.teamgroup.TeamaExample;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.post.m2m.pojo.example.teamgroup.TeamGroupExample.*;

/**
 * 【teamGroup】列表展示对象
 *
 * @author ljm
 * @date 2021/01/25
 */
@Data
@EqualsAndHashCode(callSuper=true)
@ApiModel(description = "【teamGroup】列表展示对象")
public class TeamGroupListVO extends AbstractVO {

    @ApiModelProperty(notes = N_ID, example = E_ID)
    private String id;

    @ApiModelProperty(notes = N_COUNT, example = E_COUNT)
    private String count;

    @ApiModelProperty(notes = N_TEAM_ID, example = E_TEAM_ID)
    private String teamId;

    @ApiModelProperty(notes = TeamaExample.N_TEAM_NAME, example = TeamaExample.E_TEAM_NAME)
    private String teamName;



}

