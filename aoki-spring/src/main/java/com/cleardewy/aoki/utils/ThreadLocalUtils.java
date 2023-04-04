package com.cleardewy.aoki.utils;

import com.cleardewy.aoki.entity.dto.UserDto;
import org.springframework.stereotype.Component;

/**
 * @ Author: ClearDewy
 * @ Description: 用于存放该请求的用户信息
 **/
@Component
public class ThreadLocalUtils {

    private static ThreadLocal<UserDto> threadLocal = new ThreadLocal<>();

    /**
     * 添加当前登录用户方法  在拦截器方法执行前调用设置获取用户
     *
     * @param user
     */
    public void addCurrentUser(UserDto user) {
        threadLocal.set(user);
    }

    /**
     * 获取当前登录用户方法
     */
    public UserDto getCurrentUser() {
        return threadLocal.get();
    }


    /**
     * 删除当前登录用户方法  在拦截器方法执行后 移除当前用户对象
     */
    public void remove() {
        threadLocal.remove();
    }
}
