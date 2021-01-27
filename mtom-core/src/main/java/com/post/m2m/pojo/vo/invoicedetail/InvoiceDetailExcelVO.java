package com.post.m2m.pojo.vo.invoicedetail;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.post.common.pojo.vo.AbstractVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 【InvoiceDetail】excel导出对象
 *
 * @author ljm
 * @date 2021/01/25
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class InvoiceDetailExcelVO extends AbstractVO {

    @ExcelProperty("主键")
    @ColumnWidth(19)
    private String id;

    @ExcelProperty("开票人")
    @ColumnWidth(19)
    private String people;

    @ExcelProperty("价格")
    @ColumnWidth(19)
    private Double price;

    @ExcelProperty("发票名称")
    @ColumnWidth(19)
    private String invoiceName;



}

