package com.post.m2m;

import com.post.common.optimistic.EnableOptimisticLock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 启动类
 *
 * @author ljm
 * @date 2021/01/25
 */
@SpringBootApplication
@EnableOptimisticLock
@EnableDiscoveryClient
public class MtomApp extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MtomApp.class);
        app.run(args);
    }

    /**
     * 兼容tomcat部署模式
     *
     * @param application
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MtomApp.class);
    }
}

