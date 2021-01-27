package com.post.m2m.pojo.vo.invoice;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.post.common.pojo.vo.AbstractVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 【Invoice】excel导出对象
 *
 * @author ljm
 * @date 2021/01/25
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class InvoiceExcelVO extends AbstractVO {

    @ExcelProperty("主键")
    @ColumnWidth(19)
    private String id;

    @ExcelProperty("类别")
    @ColumnWidth(19)
    private String type;

    @ExcelProperty("发票名称")
    @ColumnWidth(19)
    private String invoiceName;

    @ExcelProperty("发票标题")
    @ColumnWidth(19)
    private String invoiceTitle;



}

