package com.cleardewy.aoki.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ InterfaceName: AokiRole
 * @ Author: ClearDewy
 * @ Date: 2023/3/16 20:40
 * @ Description:
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AokiRole {
    String []value();
}
