package com.cleardewy.aoki.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @ Author: ClearDewy
 * @ Description:
 */
public final class ResultStatus {
    @Getter
    @AllArgsConstructor
    public enum Message{
        TRY_TIMES_EXCEEDED_LIMIT("对不起！登录失败次数过多！您的账号有风险，半个小时内暂时无法登录！"),
        USERNAME_OR_PASSWORD_WRONG("用户名或密码错误"),
        INVALID_TOKEN("无效的token"),
        _NO_ROLE("无效的身份"),
        NO_MAJOR("无效的专业"),

        USERNAME_EXIST("该用户名已存在"),
        NUMBER_EXIST("该学工号已存在"),
        EMAIL_EXIST("该邮箱已被使用"),
        NO_PERMISSIONS("无权访问"),
        OVERDUE_TOKEN("登录已过期，请重新登录");

        private final String message;
    }

    @Getter
    @AllArgsConstructor
    public enum Status{
        /**
         * code:200 成功
         */
        SUCCESS(200,"成功"),

        /**
         * code:400 失败
         */
        FAIL(400,"失败"),


        /**
         * code:401 访问受限
         */
        ACCESS_DENIED(401,"访问受限"),

        /**
         * code:402 过期，重新登录
         */
        OVERDUE(402,"过期"),

        /**
         * code:403 访问受限
         */
        FORBIDDEN(403,"拒绝访问"),

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
}