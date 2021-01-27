package com.post.m2m.pojo.dto;

import lombok.Data;

/**
 * 用户登录请求体
 *
 * @author ljm
 * @date 2021/01/25
 */
@Data
public class UserLoginDTO {

    private String username;

    private String password;


}

