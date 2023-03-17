package com.cleardewy.aoki.manager.account;

import com.cleardewy.aoki.constant.Constants;
import com.cleardewy.aoki.constant.ResultStatus;
import com.cleardewy.aoki.entity.dto.UserDto;
import com.cleardewy.aoki.entity.vo.AccountVo;
import com.cleardewy.aoki.entity.vo.UserVo;
import com.cleardewy.aoki.exception.AokiException;
import com.cleardewy.aoki.service.entity.Impl.UserEntityServiceImpl;
import com.cleardewy.aoki.service.entity.UserEntityService;
import com.cleardewy.aoki.utils.JwtUtils;
import com.cleardewy.aoki.utils.RedisUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Component
public class AccountManager {
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    RedisUtils redisUtils;

    @Autowired
    UserEntityService userEntityService;

    public UserVo login(AccountVo accountVo){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();        // 获取请求头
        HttpServletResponse response = servletRequestAttributes.getResponse();
        String key= Constants.Account.ACCOUNT_TRY_TIME+accountVo.getUsername();
        Integer tryLoginCount = (Integer) redisUtils.get(key);

        if (tryLoginCount != null && tryLoginCount >= 20) {
            throw new AokiException(ResultStatus.FAIL_TRY_TIMES_EXCEEDED_LIMIT);
        }
        UserDto userDto=null;
        try {
            userDto = userEntityService.getUserByUsername(accountVo.getUsername());
        }catch (AokiException e){
            throw new AokiException(ResultStatus.FAIL_USERNAME_OR_PASSWORD_WRONG);
        }

        if (!userDto.getPassword().equals(accountVo.getPassword())){
            if (tryLoginCount == null) {
                redisUtils.set(key, 1, 60 * 30); // 三十分钟不尝试，该限制会自动清空消失
            } else {
                redisUtils.set(key, tryLoginCount + 1, 60 * 30);
            }
            throw new AokiException(ResultStatus.FAIL_USERNAME_OR_PASSWORD_WRONG);
        }
        // 认证成功，清除锁定限制
        if (tryLoginCount != null) {
            redisUtils.del(key);
        }

        String jwt=jwtUtils.generateToken(userDto.getId());
        response.setHeader("Authorization", jwt); //放到信息头部
        response.setHeader("Access-Control-Expose-Headers", "Authorization");

        return userEntityService.userDtoToUserVo(userDto);
    }


    public void logout(){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String jwt=request.getHeader("Authorization");
        jwtUtils.cleanToken(jwt);
    }
}
