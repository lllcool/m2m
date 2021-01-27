package com.post.common.optimistic;

import java.lang.annotation.*;

/**
 * 乐观锁注解
 *
 * @author ljm
 * @date 2021/01/25
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface OptimisticLock {

    /**
     * 捕获异常
     *
     * @return
     */
    Class<? extends Exception>[] catchType() default {OptimisticException.class};

    /**
     * 异常重试次数
     *
     * @return
     */
    int retry() default 3;

}

