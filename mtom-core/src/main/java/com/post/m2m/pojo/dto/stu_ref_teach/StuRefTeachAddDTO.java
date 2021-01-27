package com.post.m2m.pojo.dto.stu_ref_teach;

import com.post.common.pojo.dto.AbstractDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import static com.post.m2m.pojo.example.stu_ref_teach.StuRefTeachExample.*;

/**
 * 新增【StuRefTeach】的参数
 *
 * @author ljm
 * @date 2021/01/25
 */
@Data
@EqualsAndHashCode(callSuper=true)
@ApiModel(description = "新增【StuRefTeach】的参数")
public class StuRefTeachAddDTO extends AbstractDTO {

    @ApiModelProperty(notes = N_S_ID, example = E_S_ID)
    @Length(max = 32)
    private String sId;

    @ApiModelProperty(notes = N_T_ID, example = E_T_ID)
    @Length(max = 32)
    private String tId;


}


