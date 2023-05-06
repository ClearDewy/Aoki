package com.cleardewy.aoki.controller.admin;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import lombok.Data;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Data
@RestController
@RefreshScope
public class ConfigController {
    @Value(value = "${test.name:LiSi}")
    private String name;

    @RequestMapping("/config")
    String home() {
        return "Hello " + name;
    }
}
