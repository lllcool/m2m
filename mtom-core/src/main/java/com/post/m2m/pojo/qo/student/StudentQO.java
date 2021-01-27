package com.post.m2m.pojo.qo.student;

import com.post.common.pojo.qo.PageQO;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import static com.post.m2m.pojo.example.student.StudentExample.*;

/**
 * 查询【Student】的参数
 *
 * @author ljm
 * @date 2021/01/25
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class StudentQO extends PageQO {

    @ApiParam(value = N_NAME, example = E_NAME)
    @Length(max = 32, message = "name最大长度不能超过{max}")
    private String name;

    @ApiParam(value = N_AGE, example = E_AGE)
    private Integer age;


}

