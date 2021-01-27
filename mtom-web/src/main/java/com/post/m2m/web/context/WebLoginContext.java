package com.post.m2m.web.context;

import com.post.common.context.LoginContext;
import org.springframework.stereotype.Component;

/**
 * web登录用户上下文
 *
 * @author ljm
 * @date 2021/01/25
 */
@Component
public class WebLoginContext implements LoginContext {

    /**
     * 获取当前操作员id
     *
     * @return
     */
    @Override
    public String getCurrentUser() {
        return "admin";
    }

}

