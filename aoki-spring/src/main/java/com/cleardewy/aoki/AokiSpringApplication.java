package com.cleardewy.aoki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync(proxyTargetClass=true) //开启异步注解
public class AokiSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(AokiSpringApplication.class, args);
    }

}
