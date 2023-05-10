package com.cleardewy.aoki.controller.admin;

import com.cleardewy.aoki.annotation.AokiRole;
import com.cleardewy.aoki.config.AokiConfigProperties;
import com.cleardewy.aoki.service.admin.ConfigService;
import com.cleardewy.aoki.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.cleardewy.aoki.constant.RoleConstans.ADMIN;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@RestController
@RefreshScope
@RequestMapping("/api/admin")
@AokiRole({ADMIN})
public class ConfigController {
    @Autowired
    private ConfigService configService;

    @GetMapping("/get-mail-config")
    public Result getMailConfig(){
        return configService.getMailConfig();
    }

    @RequestMapping("/update-mail-config")
    public Result updateMailConfig(AokiConfigProperties.Mail mailConfig){
        return configService.updateMailConfig(mailConfig);
    }

}
