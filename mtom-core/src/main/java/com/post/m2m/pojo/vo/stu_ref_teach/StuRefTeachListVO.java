package com.post.m2m.pojo.vo.stu_ref_teach;

import com.post.common.pojo.vo.AbstractVO;

import com.post.m2m.pojo.example.student.StudentExample;
import com.post.m2m.pojo.example.teacher.TeacherExample;
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

    @ApiModelProperty(notes = N_T_ID, example = E_T_ID)
    private String tId;

    @ApiModelProperty(notes = StudentExample.N_NAME, example = StudentExample.E_NAME)
    private String sName;

    @ApiModelProperty(notes = TeacherExample.N_T_NAME, example = TeacherExample.E_T_NAME)
    private String tName;

    @ApiModelProperty(notes = TeacherExample.N_SUBJECT, example = TeacherExample.E_SUBJECT)
    private String subject;



}

