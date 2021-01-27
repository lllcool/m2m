package com.post.m2m.pojo.qo.invoice;

import com.post.common.pojo.qo.PageQO;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import static com.post.m2m.pojo.example.invoice.InvoiceExample.*;

/**
 * 查询【Invoice】的参数
 *
 * @author ljm
 * @date 2021/01/25
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class InvoiceQO extends PageQO {

    @ApiParam(value = N_ID, example = E_ID)
    @Length(max = 32, message = "id最大长度不能超过{max}")
    private String id;

    @ApiParam(value = N_TYPE, example = E_TYPE)
    private Integer type;

    @ApiParam(value = N_INVOICE_NAME, example = E_INVOICE_NAME)
    @Length(max = 10, message = "invoiceName最大长度不能超过{max}")
    private String invoiceName;

    @ApiParam(value = N_INVOICE_TITLE, example = E_INVOICE_TITLE)
    @Length(max = 20, message = "invoiceTitle最大长度不能超过{max}")
    private String invoiceTitle;


}

