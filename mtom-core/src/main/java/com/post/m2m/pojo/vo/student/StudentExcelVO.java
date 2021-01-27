package com.post.m2m.pojo.vo.student;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.post.common.pojo.vo.AbstractVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 【Student】excel导出对象
 *
 * @author ljm
 * @date 2021/01/25
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class StudentExcelVO extends AbstractVO {

    @ExcelProperty("主键")
    @ColumnWidth(19)
    private String id;

    @ExcelProperty("姓名")
    @ColumnWidth(19)
    private String name;

    @ExcelProperty("年龄")
    @ColumnWidth(12)
    private Integer age;



}

