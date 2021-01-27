package com.post.m2m.excel.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.post.common.util.DateUtil;

import java.time.LocalDateTime;

/**
 * LocalDateTime格式的日期转换器
 *
 * @author ljm
 * @date 2021/01/25
 */
public class LocalDateTimeConverter implements Converter<LocalDateTime> {

    @Override
    public Class supportJavaTypeKey() {
        return LocalDateTime.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public LocalDateTime convertToJavaData(CellData cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        return DateUtil.parseLocalDateTime(cellData.getStringValue());
    }

    @Override
    public CellData convertToExcelData(LocalDateTime value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        return new CellData(DateUtil.getLocalDateTimeStr(value));
    }

}


