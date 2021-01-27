package com.post.m2m.pojo.dto.ball;

import com.post.common.pojo.dto.AbstractDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

import static com.post.m2m.pojo.example.ball.BallExample.*;

/**
 * 修改【Ball】的参数
 *
 * @author ljm
 * @date 2021/01/25
 */
@Data
@EqualsAndHashCode(callSuper=true)
@ApiModel(description = "修改【Ball】的参数")
public class BallUpdateDTO extends AbstractDTO {

    @ApiModelProperty(notes = N_ID, example = E_ID, required = true)
    @NotNull
    @Length(max = 32)
    private String id;

    @ApiModelProperty(notes = N_B_NAME, example = E_B_NAME, required = true)
    @NotNull
    @Length(max = 10)
    private String bName;

    @ApiModelProperty(notes = N_PRICE, example = E_PRICE)
    private Integer price;



}

