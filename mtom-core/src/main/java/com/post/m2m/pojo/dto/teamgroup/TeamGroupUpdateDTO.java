package com.post.m2m.pojo.dto.teamgroup;

import com.post.common.pojo.dto.AbstractDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

import static com.post.m2m.pojo.example.teamgroup.TeamGroupExample.*;

/**
 * 修改【teamGroup】的参数
 *
 * @author ljm
 * @date 2021/01/25
 */
@Data
@EqualsAndHashCode(callSuper=true)
@ApiModel(description = "修改【teamGroup】的参数")
public class TeamGroupUpdateDTO extends AbstractDTO {

    @ApiModelProperty(notes = N_ID, example = E_ID, required = true)
    @NotNull
    @Length(max = 32)
    private String id;

    @ApiModelProperty(notes = N_COUNT, example = E_COUNT)
    @Length(max = 5)
    private String count;

    @ApiModelProperty(notes = N_TEAM_ID, example = E_TEAM_ID)
    @Length(max = 32)
    private String teamId;



}

