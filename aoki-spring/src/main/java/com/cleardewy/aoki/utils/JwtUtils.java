package com.cleardewy.aoki.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cleardewy.aoki.constant.JwtConstant;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ClassName: JwtUtil
 * @Author: ClearDewy
 * @Date: 2023/3/16 13:39
 * @@Description: Jwt工具类
 **/
@Slf4j
@Component
@Data
public class JwtUtils {


    @Autowired
    private RedisUtils redisUtils;

    /**
     * 生成jwt token
     */
    public String generateToken(Integer userId) {
        Date nowDate = new Date();
        //过期时间
        Date expireDate = new Date(nowDate.getTime() + JwtConstant.EXPIRE);

        String token = JWT.create()
                        .withIssuer(JwtConstant.ISSUER)
                        .withClaim("userId",userId)
                        .withExpiresAt(expireDate)
                        .sign(Algorithm.HMAC256(JwtConstant.TOKEN_SECRET));
        redisUtils.set(JwtConstant.REDIS_KEY + userId, token, JwtConstant.EXPIRE);
        return token;
    }

    public Integer getUserIdByToken(String token){
        return getDecodedJWTByToken(token).getClaim("userId").asInt();
    }
    public DecodedJWT getDecodedJWTByToken(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(JwtConstant.TOKEN_SECRET)).withIssuer(JwtConstant.ISSUER).build().verify(token);
        } catch (Exception e) {
            log.debug("validate is token error ", e);
            return null;
        }
    }

    public void cleanToken(Integer uid) {
        redisUtils.del(JwtConstant.REDIS_KEY + uid);
    }

    public boolean hasToken(Integer uid) {
        return redisUtils.hasKey(JwtConstant.REDIS_KEY + uid);
    }

    /**
     * token是否过期
     *
     * @return true：过期
     */
    public boolean isTokenExpiredByUid(Integer uid) {
        return !redisUtils.hasKey(JwtConstant.REDIS_KEY+uid);
    }

    public boolean isTokenExpiredByToken(String token){
        return !redisUtils.hasKey(JwtConstant.REDIS_KEY + getUserIdByToken(token));
    }
}
