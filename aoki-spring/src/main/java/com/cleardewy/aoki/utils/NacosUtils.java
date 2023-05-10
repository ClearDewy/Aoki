package com.cleardewy.aoki.utils;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.api.exception.NacosException;
import com.cleardewy.aoki.config.AokiConfigProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Slf4j
@Component
public class NacosUtils {
    @Autowired
    private AokiConfigProperties aokiConfigProperties;

    @Autowired
    private ConfigService configService;

    @Value("${spring.cloud.nacos.config.prefix}")
    private String dataId;

    @Value("${spring.cloud.nacos.config.group}")
    private String group;


    public void updateConfig() {
        try {
            configService.publishConfig(dataId,group,aokiConfigProperties.toYamlString(), ConfigType.YAML.getType());
        } catch (NacosException e) {
            throw new RuntimeException(e);
        }
    }
}
