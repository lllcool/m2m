package com.post.m2m.pojo.dto.member;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.post.common.util.SafeUtil;
import com.post.m2m.pojo.dto.AbstractExcelDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.post.m2m.pojo.example.member.MemberExample.*;

/**
 * excel导入【Member】的数据传输对象
 *
 * @author ljm
 * @date 2021/01/25
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class MemberExcelDTO extends AbstractExcelDTO {

    @ExcelProperty("成员姓名")
    @ColumnWidth(12)
    private String mName;

    @ExcelProperty("成员年龄")
    @ColumnWidth(12)
    private Integer age;


    /**
     * 创建模板示例
     *
     * @return
     */
    public static MemberExcelDTO example() {
        MemberExcelDTO example = new MemberExcelDTO();
        example.setMName(E_M_NAME);
        example.setAge(SafeUtil.getInteger(E_AGE));
        return example;
    }

}


