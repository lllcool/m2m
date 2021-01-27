package com.post.m2m.pojo.dto.invoice;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.post.common.util.SafeUtil;
import com.post.m2m.constant.InvoiceType;
import com.post.m2m.pojo.dto.AbstractExcelDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.post.m2m.pojo.example.invoice.InvoiceExample.*;

/**
 * excel导入【Invoice】的数据传输对象
 *
 * @author ljm
 * @date 2021/01/25
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class InvoiceExcelDTO extends AbstractExcelDTO {

    @ExcelProperty("类别*")
    @ColumnWidth(19)
    private String type;

    @ExcelProperty("发票名称")
    @ColumnWidth(19)
    private String invoiceName;

    @ExcelProperty("发票标题*")
    @ColumnWidth(19)
    private String invoiceTitle;


    /**
     * 创建模板示例
     *
     * @return
     */
    public static InvoiceExcelDTO example() {
        InvoiceExcelDTO example = new InvoiceExcelDTO();
        example.setType(InvoiceType.valueToDesc(SafeUtil.getInteger(E_TYPE)));
        example.setInvoiceName(E_INVOICE_NAME);
        example.setInvoiceTitle(E_INVOICE_TITLE);
        return example;
    }

}


