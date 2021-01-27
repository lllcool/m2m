package com.post.m2m.pojo.dto.invoicedetail;

import com.post.common.pojo.dto.AbstractDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

import static com.post.m2m.pojo.example.invoicedetail.InvoiceDetailExample.*;

/**
 * 新增【InvoiceDetail】的参数
 *
 * @author ljm
 * @date 2021/01/25
 */
@Data
@EqualsAndHashCode(callSuper=true)
@ApiModel(description = "新增【InvoiceDetail】的参数")
public class InvoiceDetailAddDTO extends AbstractDTO {

    @ApiModelProperty(notes = N_COUNT, example = E_COUNT, required = true)
    @NotNull
    private Integer count;

    @ApiModelProperty(notes = N_PEOPLE, example = E_PEOPLE)
    @Length(max = 20)
    private String people;

    @ApiModelProperty(notes = N_PRICE, example = E_PRICE, required = true)
    @NotNull
    private Double price;

    @ApiModelProperty(notes = N_INVOICE_ID, example = E_INVOICE_ID, required = true)
    @NotNull
    @Length(max = 32)
    private String invoiceId;


}


