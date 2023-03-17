package com.cleardewy.aoki.filter;

import com.cleardewy.aoki.annotation.AokiRole;
import com.cleardewy.aoki.constant.ResultStatus;
import com.cleardewy.aoki.exception.AokiException;
import com.cleardewy.aoki.service.entity.Impl.UserEntityServiceImpl;

import com.cleardewy.aoki.utils.JwtUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

/**
 * @ Author: ClearDewy
 * @ Description: 自定义jwt过滤器，对token进行处理
 **/
@Component
@Slf4j
public class JwtFilter implements HandlerInterceptor{

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserEntityServiceImpl userEntityService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof ResourceHttpRequestHandler) {
            return true;
        }

        //获取当前路由所对应的注解;先从函数获得注解，如果函数上没有就从类上获得
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        AokiRole aokiRole = handlerMethod.getMethod().getAnnotation(AokiRole.class);
        if (aokiRole==null)aokiRole=handlerMethod.getClass().getAnnotation(AokiRole.class);

        //如果没有注解  视为FilterType.anno 也就是不需要登录或其它操作 返回true代表通过，返回false代表拦截此请求
        if (aokiRole == null) {
            return true;
        }

        //到了需要鉴权的时候了
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        //获取token
        String tokenValue = request.getHeader("Authorization");
        //没有token一律不准通过
        if (tokenValue==null||tokenValue.isBlank()) {
            throw new AokiException(ResultStatus.FORBIDDEN);
        }
        //查看token是否过期
        if (jwtUtils.isTokenExpiredByToken(tokenValue)) {
            throw new AokiException(ResultStatus.OVERDUE_TOKEN);
        }

        //获取权限
        String userRole = userEntityService.getRoleById(jwtUtils.getUserIdByToken(tokenValue));
        // 判断该角色是否有权限
        if(!ArrayUtils.contains(aokiRole.value(), userRole)){
            throw new AokiException(ResultStatus.FORBIDDEN_NO_PERMISSIONS);
        }
        return true;
    }
}
