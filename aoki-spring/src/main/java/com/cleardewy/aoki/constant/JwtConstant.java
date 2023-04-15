package com.cleardewy.aoki.constant;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
public final class JwtConstant {
    public static final String ISSUER="cleardewy";
    public static final String TOKEN_SECRET="野有蔓草，零露漙兮";
    public static final long EXPIRE=5*60*60;         // 单位为s
    public static final String REDIS_KEY="aoki:token:uid:";
}
