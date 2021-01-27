package com.post.m2m.web;

import com.post.common.convert.MyCustomDateEditor;
import com.post.common.convert.MyCustomLocalDateEditor;
import com.post.common.convert.MyCustomLocalDateTimeEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 抽象controller
 *
 * @author ljm
 * @date 2021/01/25
 */
public abstract class AbstractController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new MyCustomDateEditor());
        binder.registerCustomEditor(LocalDate.class, new MyCustomLocalDateEditor());
        binder.registerCustomEditor(LocalDateTime.class, new MyCustomLocalDateTimeEditor());
    }

}

