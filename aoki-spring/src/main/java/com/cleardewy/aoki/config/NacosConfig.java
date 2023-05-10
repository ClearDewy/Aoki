package com.cleardewy.aoki.config;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Configuration
@Data
public class NacosConfig {
    @Value("${spring.cloud.nacos.config.username}")
    private String username;
    @Value("${spring.cloud.nacos.config.password}")
    private String password;
    @Value("${spring.cloud.nacos.config.server-addr}")
    private String server_addr;

    @Bean
    public ConfigService ConfigService(){
        Properties properties = new Properties();
        properties.put("serverAddr", server_addr);
        // if need username and password to login
        properties.put("username", username);
        properties.put("password", password);
        try {
            return NacosFactory.createConfigService(properties);
        } catch (NacosException e) {
            throw new RuntimeException(e);
        }
    }

}
