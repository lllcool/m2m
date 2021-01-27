package com.post.m2m.pojo.qo.invoicedetail;

import com.post.common.pojo.qo.PageQO;

import com.post.m2m.pojo.example.invoice.InvoiceExample;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import static com.post.m2m.pojo.example.invoicedetail.InvoiceDetailExample.*;

/**
 * 查询【InvoiceDetail】的参数
 *
 * @author ljm
 * @date 2021/01/25
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class InvoiceDetailQO extends PageQO {

    @ApiParam(value = N_PEOPLE, example = E_PEOPLE)
    @Length(max = 20, message = "people最大长度不能超过{max}")
    private String people;

    @ApiParam(value = N_INVOICE_ID, example = E_INVOICE_ID)
    @Length(max = 32, message = "invoiceId最大长度不能超过{max}")
    private String invoiceId;

    @ApiParam(value = InvoiceExample.N_INVOICE_NAME, example = InvoiceExample.E_INVOICE_NAME)
    @Length(max = 10, message = "invoiceName最大长度不能超过{max}")
    private String invoiceName;


}

