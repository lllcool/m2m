package com.post.m2m.pojo.qo.ball;

import com.post.common.pojo.qo.PageQO;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import static com.post.m2m.pojo.example.ball.BallExample.*;

/**
 * 查询【Ball】的参数
 *
 * @author ljm
 * @date 2021/01/25
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class BallQO extends PageQO {

    @ApiParam(value = N_ID, example = E_ID)
    @Length(max = 32, message = "id最大长度不能超过{max}")
    private String id;

    @ApiParam(value = N_BALL_NAME, example = E_BALL_NAME)
    @Length(max = 10, message = "ballName最大长度不能超过{max}")
    private String ballName;

    @ApiParam(value = N_PRICE, example = E_PRICE)
    private Integer price;


}

