package com.post.common.convert;

import com.post.common.util.DateUtil;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.util.Date;

/**
 * 自定义日期装换
 *
 * @author ljm
 * @date 2021/01/25
 */
public class MyCustomDateEditor extends PropertyEditorSupport {


    @Override
    public String getAsText() {
        Date value = (Date) getValue();
        return DateUtil.getDateStr(value);
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (!StringUtils.hasText(text)) {
            // Treat empty String as null value.
            setValue(null);
        } else {
            setValue(DateUtil.parseDate(text));
        }
    }


}

