package com.cleardewy.aoki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync(proxyTargetClass=true) //开启异步注解
@EnableDiscoveryClient
public class AokiSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(AokiSpringApplication.class, args);
    }

}
