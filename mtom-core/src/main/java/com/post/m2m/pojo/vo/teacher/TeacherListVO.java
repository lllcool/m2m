package com.post.m2m.pojo.vo.teacher;

import com.post.common.pojo.vo.AbstractVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.post.m2m.pojo.example.teacher.TeacherExample.*;

/**
 * 【Teacher】列表展示对象
 *
 * @author ljm
 * @date 2021/01/25
 */
@Data
@EqualsAndHashCode(callSuper=true)
@ApiModel(description = "【Teacher】列表展示对象")
public class TeacherListVO extends AbstractVO {

    @ApiModelProperty(notes = N_ID, example = E_ID)
    private String id;

    @ApiModelProperty(notes = N_SUBJECT, example = E_SUBJECT)
    private String subject;

    @ApiModelProperty(notes = N_T_NAME, example = E_T_NAME)
    private String tName;



}

