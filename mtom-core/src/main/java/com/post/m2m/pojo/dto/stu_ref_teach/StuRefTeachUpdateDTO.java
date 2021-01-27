package com.post.m2m.pojo.dto.stu_ref_teach;

import com.post.common.pojo.dto.AbstractDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

import static com.post.m2m.pojo.example.stu_ref_teach.StuRefTeachExample.*;

/**
 * 修改【StuRefTeach】的参数
 *
 * @author ljm
 * @date 2021/01/25
 */
@Data
@EqualsAndHashCode(callSuper=true)
@ApiModel(description = "修改【StuRefTeach】的参数")
public class StuRefTeachUpdateDTO extends AbstractDTO {

    @ApiModelProperty(notes = N_ID, example = E_ID, required = true)
    @NotNull
    @Length(max = 32)
    private String id;

    @ApiModelProperty(notes = N_STU_ID, example = E_STU_ID)
    @Length(max = 32)
    private String stuId;

    @ApiModelProperty(notes = N_TEACHER_ID, example = E_TEACHER_ID)
    @Length(max = 32)
    private String teacherId;



}

