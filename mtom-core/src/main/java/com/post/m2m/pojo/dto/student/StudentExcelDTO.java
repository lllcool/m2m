package com.post.m2m.pojo.dto.student;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.post.common.util.SafeUtil;
import com.post.m2m.pojo.dto.AbstractExcelDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.post.m2m.pojo.example.student.StudentExample.*;

/**
 * excel导入【Student】的数据传输对象
 *
 * @author ljm
 * @date 2021/01/25
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class StudentExcelDTO extends AbstractExcelDTO {

    @ExcelProperty("姓名*")
    @ColumnWidth(19)
    private String name;

    @ExcelProperty("年龄*")
    @ColumnWidth(12)
    private Integer age;


    /**
     * 创建模板示例
     *
     * @return
     */
    public static StudentExcelDTO example() {
        StudentExcelDTO example = new StudentExcelDTO();
        example.setName(E_NAME);
        example.setAge(SafeUtil.getInteger(E_AGE));
        return example;
    }

}


