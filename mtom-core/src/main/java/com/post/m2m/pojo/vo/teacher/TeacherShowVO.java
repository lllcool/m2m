package com.post.m2m.pojo.vo.teacher;

import com.post.common.pojo.vo.AbstractVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.post.m2m.pojo.example.teacher.TeacherExample.*;

/**
 * 【Teacher】详情展示对象
 *
 * @author ljm
 * @date 2021/01/25
 */
@Data
@EqualsAndHashCode(callSuper=true)
@ApiModel(description = "【Teacher】详情展示对象")
public class TeacherShowVO extends AbstractVO {

    @ApiModelProperty(notes = N_ID, example = E_ID)
    private String id;

    @ApiModelProperty(notes = N_SUBJECT, example = E_SUBJECT)
    private String subject;

    @ApiModelProperty(notes = N_TEACHER_NAME, example = E_TEACHER_NAME)
    private String teacherName;



}

