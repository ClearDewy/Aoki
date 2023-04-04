package com.cleardewy.aoki.manager.user;

import com.cleardewy.aoki.constant.ResultStatus;
import com.cleardewy.aoki.entity.dto.UserDto;
import com.cleardewy.aoki.entity.vo.user.EmailVerifyVo;
import com.cleardewy.aoki.entity.vo.user.UpdateEmailVo;
import com.cleardewy.aoki.entity.vo.user.UpdatePasswordVo;
import com.cleardewy.aoki.entity.vo.user.UserVo;
import com.cleardewy.aoki.exception.AokiException;
import com.cleardewy.aoki.manager.account.EmailVerifyManager;
import com.cleardewy.aoki.manager.entity.UserEntityManager;
import com.cleardewy.aoki.utils.ThreadLocalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Component
public class UserManager {
    @Autowired
    UserEntityManager userEntityManager;

    @Autowired
    ThreadLocalUtils threadLocalUtils;

    @Autowired
    EmailVerifyManager emailVerifyManager;

    public UserVo userDtoToUserVo(UserDto userDto){
        return new UserVo(userDto.getUsername(),"",userDto.getName(),userDto.getEmail(),userEntityManager.getMajorByMajorId(userDto.getMajorId()),userDto.getRole(),userDto.getAvatarURL());
    }

    public UserDto userVoToUserDto(UserVo userVo){
        return new UserDto(-1,userVo.getUsername(),userVo.getPassword(),userVo.getName(),userVo.getEmail(),userEntityManager.getMajorIdByMajor(userVo.getMajor()),userVo.getRole(),userVo.getAvatarURL());
    }

    public void updatePassword(UpdatePasswordVo updatePasswordVo) {
        UserDto userDto=threadLocalUtils.getCurrentUser();
        if (!userDto.getPassword().equals(updatePasswordVo.getOldPassword()))
            throw new AokiException(ResultStatus.Status.FAIL, ResultStatus.Message.OLD_PASSWORD_WRONG);
        userDto.setPassword(updatePasswordVo.getNewPassword());
        userEntityManager.updateUserById(userDto);
    }

    public void updateEmail(UpdateEmailVo updateEmailVo) {
        UserDto userDto=threadLocalUtils.getCurrentUser();
        if (!userDto.getPassword().equals(updateEmailVo.getPassword()))
            throw new AokiException(ResultStatus.Status.FAIL, ResultStatus.Message.OLD_PASSWORD_WRONG);
        emailVerifyManager.verifyCode(new EmailVerifyVo(updateEmailVo.getEmail(), updateEmailVo.getCode()));
        userDto.setEmail(updateEmailVo.getEmail());
        userEntityManager.updateUserById(userDto);
    }
}
