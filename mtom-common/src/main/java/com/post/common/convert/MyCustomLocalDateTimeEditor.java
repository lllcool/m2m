package com.post.common.convert;

import com.post.common.util.DateUtil;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.time.LocalDateTime;

/**
 * 自定义日期装换
 *
 * @author ljm
 * @date 2021/01/25
 */
public class MyCustomLocalDateTimeEditor extends PropertyEditorSupport {


    @Override
    public String getAsText() {
        LocalDateTime value = (LocalDateTime) getValue();
        return DateUtil.getLocalDateTimeStr(value);
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (!StringUtils.hasText(text)) {
            // Treat empty String as null value.
            setValue(null);
        } else {
            setValue(DateUtil.parseLocalDateTime(text));
        }
    }


}


