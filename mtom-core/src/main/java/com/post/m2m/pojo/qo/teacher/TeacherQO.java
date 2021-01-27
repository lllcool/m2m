package com.post.m2m.pojo.qo.teacher;

import com.post.common.pojo.qo.PageQO;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import static com.post.m2m.pojo.example.teacher.TeacherExample.*;

/**
 * 查询【Teacher】的参数
 *
 * @author ljm
 * @date 2021/01/25
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class TeacherQO extends PageQO {

    @ApiParam(value = N_SUBJECT, example = E_SUBJECT)
    @Length(max = 10, message = "subject最大长度不能超过{max}")
    private String subject;

    @ApiParam(value = N_TEACHER_NAME, example = E_TEACHER_NAME)
    @Length(max = 10, message = "teacherName最大长度不能超过{max}")
    private String teacherName;


}

