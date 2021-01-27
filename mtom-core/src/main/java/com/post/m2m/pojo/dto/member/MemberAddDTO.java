package com.post.m2m.pojo.dto.member;

import com.post.common.pojo.dto.AbstractDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import static com.post.m2m.pojo.example.member.MemberExample.*;

/**
 * 新增【Member】的参数
 *
 * @author ljm
 * @date 2021/01/25
 */
@Data
@EqualsAndHashCode(callSuper=true)
@ApiModel(description = "新增【Member】的参数")
public class MemberAddDTO extends AbstractDTO {

    @ApiModelProperty(notes = N_M_NAME, example = E_M_NAME)
    @Length(max = 10)
    private String mName;

    @ApiModelProperty(notes = N_AGE, example = E_AGE)
    private Integer age;


}


