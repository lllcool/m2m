package com.post.m2m;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 测试配置类
 *
 * @author ljm
 * @date 2021/01/25
 */
@Configuration
public class TestConfiguration {

    @Bean
    public H2Flusher h2Flusher(JdbcTemplate jdbcTemplate) {
        return new H2Flusher(jdbcTemplate, "DB/mtom.sql");
    }

}

