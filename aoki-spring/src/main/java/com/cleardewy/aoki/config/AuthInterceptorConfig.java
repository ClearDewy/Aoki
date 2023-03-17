package com.cleardewy.aoki.config;

import com.cleardewy.aoki.filter.JwtFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ ClassName: AuthInterceptorConfig
 * @ Author: ClearDewy
 * @ Date: 2023/3/16 20:47
 * @ Description:
 **/
@Configuration
public class AuthInterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtFilter())
                //拦截的路径
                .addPathPatterns("/**");
                //排除登录接口
                // .excludePathPatterns("/user/login");
    }
}
