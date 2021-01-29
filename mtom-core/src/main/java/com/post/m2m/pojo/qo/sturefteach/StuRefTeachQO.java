package com.post.m2m.pojo.qo.sturefteach;

import com.post.common.pojo.qo.PageQO;
import com.post.m2m.pojo.example.student.StudentExample;
import com.post.m2m.pojo.example.teacher.TeacherExample;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import static com.post.m2m.pojo.example.sturefteach.StuRefTeachExample.*;

/**
 * 查询【StuRefTeach】的参数
 *
 * @author ljm
 * @date 2021/01/25
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class StuRefTeachQO extends PageQO {

    @ApiParam(value = N_STU_ID, example = E_STU_ID)
    @Length(max = 32, message = "stuId最大长度不能超过{max}")
    private String stuId;

    @ApiParam(value = N_TEACHER_ID, example = E_TEACHER_ID)
    @Length(max = 32, message = "teacherId最大长度不能超过{max}")
    private String teacherId;

    @ApiParam(value = StudentExample.N_NAME, example = StudentExample.E_NAME)
    @Length(max = 32, message = "stuName最大长度不能超过{max}")
    private String stuName;

    @ApiParam(value = TeacherExample.N_TEACHER_NAME, example = TeacherExample.E_TEACHER_NAME)
    @Length(max = 10, message = "teaName最大长度不能超过{max}")
    private String teaName;

    @ApiParam(value = TeacherExample.N_SUBJECT, example = TeacherExample.E_SUBJECT)
    @Length(max = 10, message = "teaSubject最大长度不能超过{max}")
    private String teaSubject;


}

