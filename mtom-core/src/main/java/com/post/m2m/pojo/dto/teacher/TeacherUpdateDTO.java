package com.post.m2m.pojo.dto.teacher;

import com.post.common.pojo.dto.AbstractDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

import static com.post.m2m.pojo.example.teacher.TeacherExample.*;

/**
 * 修改【Teacher】的参数
 *
 * @author ljm
 * @date 2021/01/25
 */
@Data
@EqualsAndHashCode(callSuper=true)
@ApiModel(description = "修改【Teacher】的参数")
public class TeacherUpdateDTO extends AbstractDTO {

    @ApiModelProperty(notes = N_ID, example = E_ID, required = true)
    @NotNull
    @Length(max = 32)
    private String id;

    @ApiModelProperty(notes = N_SUBJECT, example = E_SUBJECT)
    @Length(max = 10)
    private String subject;

    @ApiModelProperty(notes = N_T_NAME, example = E_T_NAME)
    @Length(max = 10)
    private String tName;



}

