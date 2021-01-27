package com.post.m2m.pojo.dto.stu_ref_teach;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.post.m2m.pojo.dto.AbstractExcelDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.post.m2m.pojo.example.stu_ref_teach.StuRefTeachExample.*;

/**
 * excel导入【StuRefTeach】的数据传输对象
 *
 * @author ljm
 * @date 2021/01/25
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class StuRefTeachExcelDTO extends AbstractExcelDTO {

    @ExcelProperty("学生ID")
    @ColumnWidth(15)
    private String sId;

    @ExcelProperty("老师ID")
    @ColumnWidth(19)
    private String tId;


    /**
     * 创建模板示例
     *
     * @return
     */
    public static StuRefTeachExcelDTO example() {
        StuRefTeachExcelDTO example = new StuRefTeachExcelDTO();
        example.setSId(E_S_ID);
        example.setTId(E_T_ID);
        return example;
    }

}


