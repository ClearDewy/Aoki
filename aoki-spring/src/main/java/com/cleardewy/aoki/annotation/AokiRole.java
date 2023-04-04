package com.cleardewy.aoki.annotation;

import com.cleardewy.aoki.constant.RoleConstans;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ Author: ClearDewy
 * @ Description:权限注解，用于设定每个接口的权限
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AokiRole {
    int []value();
}
