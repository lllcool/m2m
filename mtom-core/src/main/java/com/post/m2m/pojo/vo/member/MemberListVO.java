package com.post.m2m.pojo.vo.member;

import com.post.common.pojo.vo.AbstractVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.post.m2m.pojo.example.member.MemberExample.*;

/**
 * 【Member】列表展示对象
 *
 * @author ljm
 * @date 2021/01/25
 */
@Data
@EqualsAndHashCode(callSuper=true)
@ApiModel(description = "【Member】列表展示对象")
public class MemberListVO extends AbstractVO {

    @ApiModelProperty(notes = N_ID, example = E_ID)
    private String id;

    @ApiModelProperty(notes = N_M_NAME, example = E_M_NAME)
    private String mName;

    @ApiModelProperty(notes = N_AGE, example = E_AGE)
    private Integer age;



}

