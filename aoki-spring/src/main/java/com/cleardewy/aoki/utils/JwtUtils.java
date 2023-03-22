package com.cleardewy.aoki.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cleardewy.aoki.constant.JwtConstant;
import com.cleardewy.aoki.constant.ResultStatus;
import com.cleardewy.aoki.exception.AokiException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.el.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ Author: ClearDewy
 * @ Description: Jwt工具类
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
        redisUtils.set(JwtConstant.REDIS_KEY + userId, token, JwtConstant.EXPIRE*2);
        return token;
    }

    public Integer getUserIdByToken(String token){
        return getDecodedJWTByToken(token).getClaim("userId").asInt();
    }
    public DecodedJWT getDecodedJWTByToken(String token) {
        try {
            log.info("获取到了token:"+token);
            return JWT.require(Algorithm.HMAC256(JwtConstant.TOKEN_SECRET)).withIssuer(JwtConstant.ISSUER).build().verify(token);
        } catch (Exception e) {
            throw new AokiException(ResultStatus.Status.OVERDUE,ResultStatus.Message.INVALID_TOKEN);
        }
    }

    public void cleanToken(String token){
        redisUtils.del(JwtConstant.REDIS_KEY + getUserIdByToken(token));
    }


    public void cleanTokenByUserId(Integer uid) {
        redisUtils.del(JwtConstant.REDIS_KEY + uid);
    }

    public boolean hasToken(String token) {
        return redisUtils.hasKey(JwtConstant.REDIS_KEY + getUserIdByToken(token));
    }

    public boolean hasTokenById(Integer id){
        return redisUtils.hasKey(JwtConstant.REDIS_KEY + id);
    }

    /**
     * @ Param: [java.lang.String]
     * @ Return: boolean
     * @ Description: 通过token判断token是否过期
     **/
    public boolean isTokenExpiredByToken(String token){
        return getDecodedJWTByToken(token).getExpiresAt().before(new Date());
    }
}
