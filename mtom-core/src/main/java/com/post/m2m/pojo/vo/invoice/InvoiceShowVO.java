package com.post.m2m.pojo.vo.invoice;

import com.post.common.pojo.vo.AbstractVO;
import com.post.m2m.constant.InvoiceType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.post.m2m.pojo.example.invoice.InvoiceExample.*;

/**
 * 【Invoice】详情展示对象
 *
 * @author ljm
 * @date 2021/01/25
 */
@Data
@EqualsAndHashCode(callSuper=true)
@ApiModel(description = "【Invoice】详情展示对象")
public class InvoiceShowVO extends AbstractVO {

    @ApiModelProperty(notes = N_ID, example = E_ID)
    private String id;

    @ApiModelProperty(notes = N_TYPE, example = E_TYPE, allowableValues = InvoiceType.VALUES_STR)
    private Integer type;

    @ApiModelProperty(notes = N_INVOICE_NAME, example = E_INVOICE_NAME)
    private String invoiceName;

    @ApiModelProperty(notes = N_INVOICE_TITLE, example = E_INVOICE_TITLE)
    private String invoiceTitle;



}

