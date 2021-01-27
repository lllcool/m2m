package com.post.common.xss;

import java.lang.annotation.*;

/**
 * 无视XSS脚本
 *
 * @author ljm
 * @date 2021/01/25
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface IgnoreXSS {


}

