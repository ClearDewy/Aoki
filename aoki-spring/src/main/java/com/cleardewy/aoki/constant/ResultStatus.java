package com.cleardewy.aoki.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ Author: ClearDewy
 * @ Description:
 */
@Getter
@AllArgsConstructor
public enum ResultStatus {

    /**
     * code:200 成功
     */
    SUCCESS(200,null),

    /**
     * code:400 失败
     */
    FAIL(400,"失败"),
    FAIL_TRY_TIMES_EXCEEDED_LIMIT(400,"对不起！登录失败次数过多！您的账号有风险，半个小时内暂时无法登录！"),
    FAIL_USERNAME_OR_PASSWORD_WRONG(400,"用户名或密码错误"),
    FAIL_INVALID_TOKEN(400,"无效的token"),


    /**
     * code:401 访问受限
     */
    ACCESS_DENIED(401,"访问受限"),


    /**
     * code:402 过期
     */
    OVERDUE(402,"过期"),
    OVERDUE_TOKEN(402,"登录已过期，请重新登录"),

    /**
     * code:403 访问受限
     */
    FORBIDDEN(403,"拒绝访问"),
    FORBIDDEN_NO_PERMISSIONS(403,"无权访问"),

    /**
     * code:404 数据不存在
     */
    NOT_FOUND(404,"数据不存在"),

    /**
     * code:500 系统错误
     */
    SYSTEM_ERROR(500,"系统错误");


    private final int status;
    private final String message;
}