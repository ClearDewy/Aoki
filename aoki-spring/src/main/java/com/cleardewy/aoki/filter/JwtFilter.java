package com.cleardewy.aoki.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.cleardewy.aoki.annotation.AokiRole;
import com.cleardewy.aoki.mapper.UserMapper;
import com.cleardewy.aoki.service.user.UserService;
import com.cleardewy.aoki.utils.Result;
import com.cleardewy.aoki.constant.ResultStatus;

import com.cleardewy.aoki.utils.JwtUtils;
import com.cleardewy.aoki.utils.RedisUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ ClassName: JwtFilter
 * @ Author: ClearDewy
 * @ Date: 2023/3/16 15:36
 * @ Description: 自定义jwt过滤器，对token进行处理
 **/
@Component
@Slf4j
public class JwtFilter implements HandlerInterceptor{

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (handler instanceof ResourceHttpRequestHandler) {
            return true;
        }

        String requestUrl = request.getRequestURI();

        String queryString = request.getQueryString();

        //去掉项目配置的contextPath
//        if(!contextPath.isBlank()){
//            requestUrl = requestUrl.replace(contextPath,"");
//        }
//
//        //开放一些需要开放的但又不能加入自定义注解的路径 比如 /swagger-ui.html
//        if(ArrayUtils.contains(SysConstants.ANNO_URLS,requestUrl)){
//            return true;
//        }

        //获取当前路由所对应的注解
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        AokiRole aokiRole = handlerMethod.getMethod().getAnnotation(AokiRole.class);

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
        if (tokenValue.isBlank()) {
            return false;
        }
        //查看token是否过期
        if (jwtUtils.isTokenExpiredByToken(tokenValue)) {
            return false;
        }

        //检测权限
        String userRole = userService.getRoleById(jwtUtils.getUserIdByToken(tokenValue));     // 获取用户角色
        // 判断该角色是否有权限
        if (ArrayUtils.contains(aokiRole.value(), userRole)) {
            return false;
        }
        return true;
    }
}
