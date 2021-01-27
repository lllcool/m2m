package com.post.m2m.web;

import com.post.m2m.AbstractTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

/**
 * web单元测试抽象类
 *
 * @author ljm
 * @date 2021/01/25
 */
@AutoConfigureMockMvc(printOnlyOnFailure = false)
public abstract class AbstractWebTest extends AbstractTest {

    @Autowired
    protected MockMvc restMockMvc;

}

