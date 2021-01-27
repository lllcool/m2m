package com.post.m2m.pojo.vo.teacher;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.post.common.pojo.vo.AbstractVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 【Teacher】excel导出对象
 *
 * @author ljm
 * @date 2021/01/25
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class TeacherExcelVO extends AbstractVO {

    @ExcelProperty("主键")
    @ColumnWidth(19)
    private String id;

    @ExcelProperty("科目")
    @ColumnWidth(12)
    private String subject;

    @ExcelProperty("名字")
    @ColumnWidth(19)
    private String teacherName;



}

