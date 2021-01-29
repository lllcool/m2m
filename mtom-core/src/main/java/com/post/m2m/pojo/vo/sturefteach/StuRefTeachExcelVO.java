package com.post.m2m.pojo.vo.sturefteach;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.post.common.pojo.vo.AbstractVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 【StuRefTeach】excel导出对象
 *
 * @author ljm
 * @date 2021/01/25
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class StuRefTeachExcelVO extends AbstractVO {

    @ExcelProperty("主键")
    @ColumnWidth(19)
    private String id;

    @ExcelProperty("老师ID")
    @ColumnWidth(19)
    private String teacherId;

    @ExcelProperty("学生姓名")
    @ColumnWidth(19)
    private String stuName;

    @ExcelProperty("老师姓名")
    @ColumnWidth(19)
    private String teaName;

    @ExcelProperty("科目")
    @ColumnWidth(12)
    private String teaSubject;



}

