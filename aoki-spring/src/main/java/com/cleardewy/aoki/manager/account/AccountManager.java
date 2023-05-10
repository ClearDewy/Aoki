package com.cleardewy.aoki.manager.account;

import com.cleardewy.aoki.constant.Constants;
import com.cleardewy.aoki.constant.ResultStatus;
import com.cleardewy.aoki.entity.dto.UserDto;
import com.cleardewy.aoki.entity.vo.user.AccountVo;
import com.cleardewy.aoki.entity.vo.user.EmailVerifyVo;
import com.cleardewy.aoki.entity.vo.user.UserRegisterVo;
import com.cleardewy.aoki.entity.vo.user.UserVo;
import com.cleardewy.aoki.exception.AokiException;
import com.cleardewy.aoki.manager.user.UserManager;
import com.cleardewy.aoki.manager.entity.UserEntityManager;
import com.cleardewy.aoki.utils.JwtUtils;
import com.cleardewy.aoki.utils.RedisUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Component
@Slf4j
public class AccountManager {
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    RedisUtils redisUtils;
    @Autowired
    UserEntityManager userEntityManager;
    @Autowired
    UserManager userManager;

    @Autowired
    EmailVerifyManager emailVerifyManager;

    public UserVo login(AccountVo accountVo){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = servletRequestAttributes.getResponse();      // 获取响应
        String key= Constants.Account.ACCOUNT_TRY_TIME+accountVo.getUsername();
        Integer tryLoginCount = (Integer) redisUtils.get(key);

        if (tryLoginCount != null && tryLoginCount >= 20) {
            throw new AokiException(ResultStatus.Status.FAIL,ResultStatus.Message.TRY_TIMES_EXCEEDED_LIMIT);
        }
        UserDto userDto;
        try {
            userDto = userEntityManager.getUserByUsername(accountVo.getUsername());
        }catch (AokiException e){
            throw new AokiException(ResultStatus.Status.FAIL,ResultStatus.Message.USERNAME_OR_PASSWORD_WRONG);
        }

        if (!userDto.getPassword().equals(accountVo.getPassword())){
            if (tryLoginCount == null) {
                redisUtils.set(key, 1, 60 * 30); // 三十分钟不尝试，该限制会自动清空消失
            } else {
                redisUtils.set(key, tryLoginCount + 1, 60 * 30);
            }
            throw new AokiException(ResultStatus.Status.FAIL,ResultStatus.Message.USERNAME_OR_PASSWORD_WRONG);
        }
        // 认证成功，清除锁定限制
        if (tryLoginCount != null) {
            redisUtils.del(key);
        }

        String jwt=jwtUtils.generateToken(userDto.getId());
        response.setHeader("authorization", jwt);
        response.setHeader("refresh-authorization", String.valueOf(true)); //放到信息头部
        response.setHeader("Access-Control-Expose-Headers", "authorization");

        return new UserVo(
                userDto.getId(),
                userDto.getUsername(),
                "",
                userDto.getName(),
                userDto.getEmail(),
                userEntityManager.getMajorByMajorId(userDto.getMajorId()),
                userDto.getRole(),
                userDto.getAvatarURL());
    }


    public void logout(){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String jwt=request.getHeader("authorization");
        jwtUtils.cleanToken(jwt);
    }

    public void register(UserRegisterVo userRegisterVo, String code){
        // 检验用户名是否唯一
        if (userEntityManager.countUsername(userRegisterVo.getUsername())!=0)
            throw new AokiException(ResultStatus.Status.FAIL,ResultStatus.Message.USERNAME_EXIST);
        // 检验邮箱是否唯一
        if (userEntityManager.countEmail(userRegisterVo.getEmail())!=0)
            throw new AokiException(ResultStatus.Status.FAIL,ResultStatus.Message.EMAIL_EXIST);
        emailVerifyManager.verifyCode(new EmailVerifyVo(userRegisterVo.getEmail(),code));
        redisUtils.del("email_verify_code:"+userRegisterVo.getEmail());
        // 添加用户
        userEntityManager.addUser(new UserDto(
                null,
                userRegisterVo.getUsername()
                ,userRegisterVo.getPassword(),
                userRegisterVo.getName(),
                userRegisterVo.getEmail(),
                userEntityManager.getMajorIdByMajor(userRegisterVo.getMajor()),
                userRegisterVo.getRole()
                ,userRegisterVo.getAvatarURL()));
    }


    public UserVo emailLogin(EmailVerifyVo emailVerifyVo){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = servletRequestAttributes.getResponse();      // 过去响应
        String key= Constants.Account.ACCOUNT_TRY_TIME+emailVerifyVo.getEmail();
        Integer tryLoginCount = (Integer) redisUtils.get(key);

        if (tryLoginCount != null && tryLoginCount >= 20) {
            throw new AokiException(ResultStatus.Status.FAIL,ResultStatus.Message.TRY_TIMES_EXCEEDED_LIMIT);
        }
        UserDto userDto;
        try {
            userDto = userEntityManager.getUserByEmail(emailVerifyVo.getEmail());
        }catch (AokiException e){
            throw new AokiException(ResultStatus.Status.FAIL,ResultStatus.Message.USERNAME_OR_PASSWORD_WRONG);
        }

        try {
            emailVerifyManager.verifyCode(emailVerifyVo);
        }catch (AokiException e){
            if (tryLoginCount == null) {
                redisUtils.set(key, 1, 60 * 30); // 三十分钟不尝试，该限制会自动清空消失
            } else {
                redisUtils.set(key, tryLoginCount + 1, 60 * 30);
            }
            throw e;
        }
        // 认证成功，清除锁定限制
        if (tryLoginCount != null) {
            redisUtils.del(key);
        }

        String jwt=jwtUtils.generateToken(userDto.getId());
        response.setHeader("authorization", jwt);
        response.setHeader("refresh-authorization", String.valueOf(true)); //放到信息头部
        response.setHeader("Access-Control-Expose-Headers", "authorization");

        return new UserVo(
                userDto.getId(),
                userDto.getUsername(),
                "",
                userDto.getName(),
                userDto.getEmail(),
                userEntityManager.getMajorByMajorId(userDto.getMajorId()),
                userDto.getRole(),
                userDto.getAvatarURL());
    }
}
