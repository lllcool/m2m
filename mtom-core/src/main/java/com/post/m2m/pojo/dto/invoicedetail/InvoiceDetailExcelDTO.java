package com.post.m2m.pojo.dto.invoicedetail;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.post.common.util.SafeUtil;
import com.post.m2m.pojo.dto.AbstractExcelDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.post.m2m.pojo.example.invoicedetail.InvoiceDetailExample.*;

/**
 * excel导入【InvoiceDetail】的数据传输对象
 *
 * @author ljm
 * @date 2021/01/25
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class InvoiceDetailExcelDTO extends AbstractExcelDTO {

    @ExcelProperty("数量*")
    @ColumnWidth(0)
    private Integer count;

    @ExcelProperty("开票人")
    @ColumnWidth(19)
    private String people;

    @ExcelProperty("价格*")
    @ColumnWidth(19)
    private Double price;

    @ExcelProperty("发票ID*")
    @ColumnWidth(15)
    private String invoiceId;


    /**
     * 创建模板示例
     *
     * @return
     */
    public static InvoiceDetailExcelDTO example() {
        InvoiceDetailExcelDTO example = new InvoiceDetailExcelDTO();
        example.setCount(SafeUtil.getInteger(E_COUNT));
        example.setPeople(E_PEOPLE);
        example.setPrice(SafeUtil.getDouble(E_PRICE));
        example.setInvoiceId(E_INVOICE_ID);
        return example;
    }

}


