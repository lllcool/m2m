package com.post.m2m.pojo.vo.teama;

import com.post.common.pojo.vo.AbstractVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.post.m2m.pojo.example.teama.TeamaExample.*;

/**
 * 【teama】详情展示对象
 *
 * @author ljm
 * @date 2021/01/25
 */
@Data
@EqualsAndHashCode(callSuper=true)
@ApiModel(description = "【teama】详情展示对象")
public class TeamaShowVO extends AbstractVO {

    @ApiModelProperty(notes = N_ID, example = E_ID)
    private String id;

    @ApiModelProperty(notes = N_TEAM_NAME, example = E_TEAM_NAME)
    private String teamName;



}

