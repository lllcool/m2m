package com.post.common.context;


/**
 * 登录上下文接口
 *
 * @author ljm
 * @date 2021/01/25
 */
public interface LoginContext {

    /**
     * 获取当前登录用户唯一标识
     *
     * @return 用户唯一标识
     */
    String getCurrentUser();

}

