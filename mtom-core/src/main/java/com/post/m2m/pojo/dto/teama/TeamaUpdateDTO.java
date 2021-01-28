package com.post.m2m.pojo.dto.teama;

import com.post.common.pojo.dto.AbstractDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

import static com.post.m2m.pojo.example.teama.TeamaExample.*;

/**
 * 修改【teama】的参数
 *
 * @author ljm
 * @date 2021/01/25
 */
@Data
@EqualsAndHashCode(callSuper=true)
@ApiModel(description = "修改【teama】的参数")
public class TeamaUpdateDTO extends AbstractDTO {

    @ApiModelProperty(notes = N_ID, example = E_ID, required = true)
    @NotNull
    @Length(max = 32)
    private String id;

    @ApiModelProperty(notes = N_TEAM_NAME, example = E_TEAM_NAME)
    @Length(max = 20)
    private String teamName;



}

