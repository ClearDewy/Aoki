package com.cleardewy.aoki.service.admin;

import com.cleardewy.aoki.config.AokiConfigProperties;
import com.cleardewy.aoki.utils.Result;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
public interface ConfigService {

    Result updateMailConfig(AokiConfigProperties.Mail mailConfig);

    Result getMailConfig();
}
