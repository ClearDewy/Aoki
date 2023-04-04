package com.cleardewy.aoki.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.io.IOException;

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

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        log.info("==================BEGAN================");
        log.info("RequestUrl->{}",request.getURI());
        log.info("RequestAddress->{}",request.getRemoteAddress());
        log.info("RequestType->{}",request.getMethod());
        log.info("ResponseBody->{}",body);
        log.info("==================END================");
        return body;
    }
}
