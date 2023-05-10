package com.cleardewy.aoki.service.admin.impl;

import com.cleardewy.aoki.config.AokiConfigProperties;
import com.cleardewy.aoki.manager.admin.ConfigManager;
import com.cleardewy.aoki.service.admin.ConfigService;
import com.cleardewy.aoki.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Service
public class ConfigServiceImpl implements ConfigService {
    @Autowired
    private ConfigManager configManager;

    @Override
    public Result getMailConfig() {
        return Result.success(configManager.getMailConfig());
    }
    @Override
    public Result updateMailConfig(AokiConfigProperties.Mail mailConfig) {
        configManager.updateMailConfig(mailConfig);
        return Result.success();
    }
}
