package com.cleardewy.aoki.config;

import cn.hutool.core.io.FileUtil;
import com.cleardewy.aoki.constant.Constants;
import com.cleardewy.aoki.constant.FileConstants;
import com.cleardewy.aoki.filter.JwtFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.*;

import java.io.File;

/**
 * @ Author: ClearDewy
 * @ Description: 拦截器
 **/
@Configuration
@Slf4j
public class AuthInterceptorConfig implements WebMvcConfigurer {

    private static final String[] EXCLUDE_PATH_PATTERNS=new String[]{
            FileConstants.AVATAR_PATH+"**",FileConstants.IMG_PATH+"**"
    };

    @Autowired
    JwtFilter jwtFilter;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(jwtFilter)
                //拦截的路径
                .addPathPatterns("/**")
                //排除登录接口
                 .excludePathPatterns(EXCLUDE_PATH_PATTERNS);
    }

    /**
     * @ Author: ClearDewy
     * @ Description: 将访问链接映射到本机图片
     **/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(FileConstants.AVATAR_API+"**",FileConstants.IMG_API+"**")
                .addResourceLocations(
                        "file:"+ FileUtil.getAbsolutePath(FileConstants.AVATAR_PATH) + File.separator,
                                        "file:"+FileUtil.getAbsolutePath(FileConstants.IMG_PATH)+File.separator
                        );

        WebMvcConfigurer.super.addResourceHandlers(registry);
    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }
}
