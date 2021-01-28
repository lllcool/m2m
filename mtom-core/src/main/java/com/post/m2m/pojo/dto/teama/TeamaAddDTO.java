package com.post.m2m.pojo.dto.teama;

import com.post.common.pojo.dto.AbstractDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import static com.post.m2m.pojo.example.teama.TeamaExample.*;

/**
 * 新增【teama】的参数
 *
 * @author ljm
 * @date 2021/01/25
 */
@Data
@EqualsAndHashCode(callSuper=true)
@ApiModel(description = "新增【teama】的参数")
public class TeamaAddDTO extends AbstractDTO {

    @ApiModelProperty(notes = N_TEAM_NAME, example = E_TEAM_NAME)
    @Length(max = 20)
    private String teamName;


}


