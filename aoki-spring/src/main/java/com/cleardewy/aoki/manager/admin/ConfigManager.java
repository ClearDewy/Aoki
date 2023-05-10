package com.cleardewy.aoki.manager.admin;

import com.cleardewy.aoki.config.AokiConfigProperties;
import com.cleardewy.aoki.utils.NacosUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Component
public class ConfigManager {
    @Autowired
    private AokiConfigProperties aokiConfigProperties;
    @Autowired
    private NacosUtils nacosUtils;
    public void updateMailConfig(AokiConfigProperties.Mail mailConfig) {
        aokiConfigProperties.getSpring().setMail(mailConfig);
        nacosUtils.updateConfig();
    }

    public AokiConfigProperties.Mail getMailConfig() {
        return aokiConfigProperties.getSpring().getMail();
    }
}
