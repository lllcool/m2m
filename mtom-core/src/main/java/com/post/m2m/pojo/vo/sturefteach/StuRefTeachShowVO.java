package com.post.m2m.pojo.vo.sturefteach;

import com.post.common.pojo.vo.AbstractVO;
import com.post.m2m.pojo.example.sturefteach.StudentExample;
import com.post.m2m.pojo.example.sturefteach.TeacherExample;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.post.m2m.pojo.example.sturefteach.StuRefTeachExample.*;

/**
 * 【StuRefTeach】详情展示对象
 *
 * @author ljm
 * @date 2021/01/25
 */
@Data
@EqualsAndHashCode(callSuper=true)
@ApiModel(description = "【StuRefTeach】详情展示对象")
public class StuRefTeachShowVO extends AbstractVO {

    @ApiModelProperty(notes = N_ID, example = E_ID)
    private String id;

    @ApiModelProperty(notes = N_STU_ID, example = E_STU_ID)
    private String stuId;

    @ApiModelProperty(notes = N_TEACHER_ID, example = E_TEACHER_ID)
    private String teacherId;

    @ApiModelProperty(notes = StudentExample.N_NAME, example = StudentExample.E_NAME)
    private String stuName;

    @ApiModelProperty(notes = TeacherExample.N_TEACHER_NAME, example = TeacherExample.E_TEACHER_NAME)
    private String teaName;

    @ApiModelProperty(notes = TeacherExample.N_SUBJECT, example = TeacherExample.E_SUBJECT)
    private String teaSubject;



}

