package com.post.m2m.pojo.dto.teacher;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.post.m2m.pojo.dto.AbstractExcelDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.post.m2m.pojo.example.teacher.TeacherExample.*;

/**
 * excel导入【Teacher】的数据传输对象
 *
 * @author ljm
 * @date 2021/01/25
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class TeacherExcelDTO extends AbstractExcelDTO {

    @ExcelProperty("科目")
    @ColumnWidth(12)
    private String subject;

    @ExcelProperty("名字")
    @ColumnWidth(19)
    private String tName;


    /**
     * 创建模板示例
     *
     * @return
     */
    public static TeacherExcelDTO example() {
        TeacherExcelDTO example = new TeacherExcelDTO();
        example.setSubject(E_SUBJECT);
        example.setTName(E_T_NAME);
        return example;
    }

}


