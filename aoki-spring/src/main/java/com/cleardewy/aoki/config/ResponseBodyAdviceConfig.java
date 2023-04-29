package com.cleardewy.aoki.config;

import com.cleardewy.aoki.constant.Constants;
import com.cleardewy.aoki.entity.dto.UserDto;
import com.cleardewy.aoki.utils.ThreadLocalUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @ Author: ClearDewy
 * @ Description: 主要用于日志记录
 **/
@RestControllerAdvice
@Slf4j
public class ResponseBodyAdviceConfig implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Autowired
    ThreadLocalUtils threadLocalUtils;

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        log.info("==================BEGAN================");
        log.info("RequestUrl->{}",request.getURI());
        log.info("RequestAddress->{}",request.getRemoteAddress());
        try {
            log.info("RequestBody->{}", StreamUtils.copyToString(request.getBody(), StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.info("RequestType->{}",request.getMethod());
        log.info("RequestToken->{}",request.getHeaders().get(Constants.RequestHeaderConstants.AUTHORIZATION));

        UserDto user =threadLocalUtils.getCurrentUser();
        if (user!=null)
            log.info("User->id:{};name:{};role:{};",user.getId(),user.getName(),user.getRole());

        log.info("ResponseBody->{}",body);
        log.info("==================END================");
        return body;
    }
}
