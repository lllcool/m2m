package com.post.m2m.pojo.vo.stu_ref_teach;

import com.post.common.pojo.vo.AbstractVO;
import com.post.m2m.pojo.example.stu_ref_teach.StudentExample;
import com.post.m2m.pojo.example.stu_ref_teach.TeacherExample;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.post.m2m.pojo.example.stu_ref_teach.StuRefTeachExample.*;

/**
 * 【StuRefTeach】列表展示对象
 *
 * @author ljm
 * @date 2021/01/25
 */
@Data
@EqualsAndHashCode(callSuper=true)
@ApiModel(description = "【StuRefTeach】列表展示对象")
public class StuRefTeachListVO extends AbstractVO {

    @ApiModelProperty(notes = N_ID, example = E_ID)
    private String id;

    @ApiModelProperty(notes = N_TEACHER_ID, example = E_TEACHER_ID)
    private String teacherId;

    @ApiModelProperty(notes = StudentExample.N_NAME, example = StudentExample.E_NAME)
    private String stuName;

    @ApiModelProperty(notes = TeacherExample.N_TEACHER_NAME, example = TeacherExample.E_TEACHER_NAME)
    private String teaName;

    @ApiModelProperty(notes = TeacherExample.N_SUBJECT, example = TeacherExample.E_SUBJECT)
    private String teaSubject;



}

