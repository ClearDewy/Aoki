package com.cleardewy.aoki.constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * @ Author: ClearDewy
 * @ Description: 业务中的常量
 **/
public final class Constants {
    @Getter
    public static final class RequestHeaderConstants{
        public static final String AUTHORIZATION="authorization";
        public static final String REFRESH_AUTHORIZATION="refresh-authorization";
    }

    @AllArgsConstructor
    public enum Account{

        ACCOUNT_TRY_TIME("account_try_time:");

        private final String data;
    }

}
