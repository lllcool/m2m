package com.post.m2m.pojo.vo.ball;

import com.post.common.pojo.vo.AbstractVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.post.m2m.pojo.example.ball.BallExample.*;

/**
 * 【Ball】详情展示对象
 *
 * @author ljm
 * @date 2021/01/25
 */
@Data
@EqualsAndHashCode(callSuper=true)
@ApiModel(description = "【Ball】详情展示对象")
public class BallShowVO extends AbstractVO {

    @ApiModelProperty(notes = N_ID, example = E_ID)
    private String id;

    @ApiModelProperty(notes = N_B_NAME, example = E_B_NAME)
    private String bName;

    @ApiModelProperty(notes = N_PRICE, example = E_PRICE)
    private Integer price;



}

