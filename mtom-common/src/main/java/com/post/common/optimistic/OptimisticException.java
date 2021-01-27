package com.post.common.optimistic;


/**
 * 乐观锁异常
 *
 * @author ljm
 * @date 2021/01/25
 */
public class OptimisticException extends RuntimeException {

    public OptimisticException() {

    }

    public OptimisticException(String message) {
        super(message);
    }
}

