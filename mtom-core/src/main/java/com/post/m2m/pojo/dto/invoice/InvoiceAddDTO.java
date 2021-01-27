package com.post.m2m.pojo.dto.invoice;

import com.post.common.pojo.dto.AbstractDTO;
import com.post.common.validator.Const;
import com.post.m2m.constant.InvoiceType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

import static com.post.m2m.pojo.example.invoice.InvoiceExample.*;

/**
 * 新增【Invoice】的参数
 *
 * @author ljm
 * @date 2021/01/25
 */
@Data
@EqualsAndHashCode(callSuper=true)
@ApiModel(description = "新增【Invoice】的参数")
public class InvoiceAddDTO extends AbstractDTO {

    @ApiModelProperty(notes = N_TYPE, example = E_TYPE, required = true, allowableValues = InvoiceType.VALUES_STR)
    @NotNull
    @Const(constClass = InvoiceType.class)
    private Integer type;

    @ApiModelProperty(notes = N_INVOICE_NAME, example = E_INVOICE_NAME)
    @Length(max = 10)
    private String invoiceName;

    @ApiModelProperty(notes = N_INVOICE_TITLE, example = E_INVOICE_TITLE, required = true)
    @NotNull
    @Length(max = 20)
    private String invoiceTitle;


}


