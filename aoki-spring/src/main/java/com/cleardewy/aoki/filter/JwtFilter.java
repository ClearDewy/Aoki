package com.cleardewy.aoki.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.cleardewy.aoki.annotation.AokiRole;
import com.cleardewy.aoki.constant.ResultStatus;
import com.cleardewy.aoki.exception.AokiException;
import com.cleardewy.aoki.manager.entity.UserEntityManager;

import com.cleardewy.aoki.utils.JwtUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import java.util.Date;

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
    private UserEntityManager userEntityService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Request:{}->{}",request.getRemoteAddr(),request.getRequestURI());
        if (!(handler instanceof HandlerMethod handlerMethod)) {
            return false;
        }

        //获取当前路由所对应的注解;先从函数获得注解，如果函数上没有就从类上获得
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
        String tokenValue = request.getHeader("authorization");
        log.info("tokenValue:"+tokenValue);
        //没有token一律不准通过
        if (tokenValue==null||tokenValue.isBlank()) {
            throw new AokiException(ResultStatus.Status.FORBIDDEN);
        }
        //查看token状态
        DecodedJWT decodedJWT = jwtUtils.getDecodedJWTByToken(tokenValue);
        Integer userId=decodedJWT.getClaim("userId").asInt();
        // 过期
        if (!jwtUtils.hasTokenById(userId)){
            throw new AokiException(ResultStatus.Status.OVERDUE,ResultStatus.Message.OVERDUE_TOKEN);
        }
        // 续约
        if (decodedJWT.getExpiresAt().before(new Date())){
            String newToken= jwtUtils.generateToken(userId);
            response.addHeader("authorization",newToken);
            response.addHeader("refresh-authorization", String.valueOf(true));
            response.setHeader("Access-Control-Expose-Headers", "authorization");
        }

        //获取权限
        String userRole = userEntityService.getRoleById(userId);
        // 判断该角色是否有权限
        if(!ArrayUtils.contains(aokiRole.value(), userRole)){
            throw new AokiException(ResultStatus.Status.ACCESS_DENIED,ResultStatus.Message.NO_PERMISSIONS);
        }
        return true;
    }
}
