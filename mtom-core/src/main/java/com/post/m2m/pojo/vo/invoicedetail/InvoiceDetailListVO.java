package com.post.m2m.pojo.vo.invoicedetail;

import com.post.common.pojo.vo.AbstractVO;

import com.post.m2m.pojo.example.invoice.InvoiceExample;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.post.m2m.pojo.example.invoicedetail.InvoiceDetailExample.*;

/**
 * 【InvoiceDetail】列表展示对象
 *
 * @author ljm
 * @date 2021/01/25
 */
@Data
@EqualsAndHashCode(callSuper=true)
@ApiModel(description = "【InvoiceDetail】列表展示对象")
public class InvoiceDetailListVO extends AbstractVO {

    @ApiModelProperty(notes = N_ID, example = E_ID)
    private String id;

    @ApiModelProperty(notes = N_PEOPLE, example = E_PEOPLE)
    private String people;

    @ApiModelProperty(notes = N_PRICE, example = E_PRICE)
    private Double price;

    @ApiModelProperty(notes = InvoiceExample.N_INVOICE_NAME, example = InvoiceExample.E_INVOICE_NAME)
    private String invoiceName;



}

