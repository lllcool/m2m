package com.post.m2m.pojo.dto;

import com.alibaba.excel.annotation.ExcelIgnore;

import com.post.common.pojo.dto.AbstractDTO;
import lombok.Data;

/**
 * 抽象excel数据传输对象
 *
 * @author ljm
 * @date 2021/01/25
 */
@Data
public abstract class AbstractExcelDTO extends AbstractDTO {

    /**
     * 行号
     */
    @ExcelIgnore
    private Integer rowIndex;


}

