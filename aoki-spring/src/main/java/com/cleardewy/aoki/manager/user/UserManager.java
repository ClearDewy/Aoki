package com.cleardewy.aoki.manager.user;

import com.cleardewy.aoki.constant.ResultStatus;
import com.cleardewy.aoki.entity.dto.LessonDto;
import com.cleardewy.aoki.entity.dto.UserDto;
import com.cleardewy.aoki.entity.vo.lesson.LessonListVo;
import com.cleardewy.aoki.entity.vo.user.*;
import com.cleardewy.aoki.exception.AokiException;
import com.cleardewy.aoki.manager.account.EmailVerifyManager;
import com.cleardewy.aoki.manager.entity.LessonEntityManager;
import com.cleardewy.aoki.manager.entity.LessonMemberEntityManager;
import com.cleardewy.aoki.manager.entity.UserEntityManager;
import com.cleardewy.aoki.manager.file.FileManager;
import com.cleardewy.aoki.utils.RedisUtils;
import com.cleardewy.aoki.utils.ThreadLocalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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

    @Autowired
    LessonEntityManager lessonEntityManager;

    @Autowired
    FileManager fileManager;
    @Autowired
    RedisUtils redisUtils;

    @Autowired
    LessonMemberEntityManager lessonMemberEntityManager;

    public UserVo userDtoToUserVo(UserDto userDto){
        return new UserVo(userDto.getUsername(),"",userDto.getName(),userDto.getEmail(),userEntityManager.getMajorByMajorId(userDto.getMajorId()),userDto.getRole(),userDto.getAvatarURL());
    }

    public UserDto userVoToUserDto(UserVo userVo){
        return new UserDto(null,userVo.getUsername(),userVo.getPassword(),userVo.getName(),userVo.getEmail(),userEntityManager.getMajorIdByMajor(userVo.getMajor()),userVo.getRole(),userVo.getAvatarURL());
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
        redisUtils.del("email_verify_code:"+updateEmailVo.getEmail());
        userDto.setEmail(updateEmailVo.getEmail());
        userEntityManager.updateUserById(userDto);
    }

    public void updateAvatar(String avatarURL){
        UserDto user=threadLocalUtils.getCurrentUser();
        user.setAvatarURL(avatarURL);
        userEntityManager.updateUserById(user);
    }

    public List<LessonListVo> getLessonList() {
        return lessonEntityManager.getLessonList(threadLocalUtils.getCurrentUser().getId());
    }

    public LessonDto getLesson(Integer id) {
        lessonMemberEntityManager.verifyLessonMember(id,threadLocalUtils.getCurrentUser().getId());
        return lessonEntityManager.getLesson(id);
    }

    public List<UserListVo> getLessonMember(Integer id) {
        lessonMemberEntityManager.verifyLessonMember(id,threadLocalUtils.getCurrentUser().getId());
        return lessonMemberEntityManager.getLessonMember(id);
    }
}
