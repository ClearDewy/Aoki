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

        EMAIL_SEND_FAIL("邮件发送失败"),
        VERIFY_CODE_WRONG("验证码错误"),
        VERIFY_CODE_FREQUENT("验证码发送频繁"),
        NO_PERMISSIONS("无权访问"),
        OVERDUE_TOKEN("登录已过期，请重新登录"),

        OLD_PASSWORD_WRONG("当前密码错误"),

        AVATAR_IS_EMPTY("头像不能为空"),

        AVATAR_SIZE_LIMIT("头像大小不能超过2M"),

        IMG_IS_EMPTY("图片不能为空"),

        IMG_SIZE_LIMIT("图片大小不能超过2M"),

        IMG_SUFFIX_ERROR("请选择jpg,jpeg,gif,png,webp格式的头像图片！"),

        IMG_UPLOAD_FAIL("图片上传失败"),

        FILE_IS_EMPTY("文件不能为空"),

        FILE_SIZE_LIMIT("文件大小不能超过1 GB"),
        FILE_SUFFIX_ERROR("请选择正确格式的文件！"),

        FILE_UPLOAD_FAIL("文件上传失败"),

        HAVE_TEAM("已有团队"),

        HAVE_NO_TEAM("无团队"),

        DATE_ANOMALIES("日期异常"),

        TOPIC_MEMBER_FULL("选题人数已满"),

        NOT_WITHIN_TIME_RANGE("不在时间范围内"),

        TOPIC_EXIT("已有课程")
        ;

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