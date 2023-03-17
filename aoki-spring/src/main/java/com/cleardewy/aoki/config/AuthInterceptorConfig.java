package com.cleardewy.aoki.config;

import com.cleardewy.aoki.filter.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ Author: ClearDewy
 * @ Description: 拦截器
 **/
@Configuration
public class AuthInterceptorConfig implements WebMvcConfigurer {
    @Autowired
    JwtFilter jwtFilter;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(jwtFilter)
                //拦截的路径
                .addPathPatterns("/**");
                //排除登录接口
                // .excludePathPatterns("/user/login");
    }
}
