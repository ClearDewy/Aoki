package com.cleardewy.aoki.manager.teacher;

import com.cleardewy.aoki.entity.dto.LessonDto;
import com.cleardewy.aoki.entity.dto.LessonMemberDto;
import com.cleardewy.aoki.entity.dto.UserDto;
import com.cleardewy.aoki.entity.vo.lesson.CreateLessonVo;
import com.cleardewy.aoki.entity.vo.user.UserListVo;
import com.cleardewy.aoki.manager.entity.LessonEntityManager;
import com.cleardewy.aoki.manager.entity.LessonMemberEntityManager;
import com.cleardewy.aoki.manager.entity.UserEntityManager;
import com.cleardewy.aoki.mapper.LessonMapper;
import com.cleardewy.aoki.utils.ThreadLocalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Component
public class TeacherManager {

    @Autowired
    UserEntityManager userEntityManager;
    @Autowired
    LessonEntityManager lessonEntityManager;
    @Autowired
    ThreadLocalUtils threadLocalUtils;

    @Autowired
    LessonMemberEntityManager lessonMemberEntityManager;

    public void createLesson(CreateLessonVo createLessonVo) {
        LessonDto lessonDto= new LessonDto();
        lessonDto.setName(createLessonVo.getName());
        lessonDto.setIntroduction(createLessonVo.getIntroduction());
        lessonDto.setTopicMod(createLessonVo.isTopicMod());
        lessonDto.setAvatarURL(createLessonVo.getAvatarURL());
        lessonDto.setOwnerId(threadLocalUtils.getCurrentUser().getId());

        lessonEntityManager.addLesson(lessonDto);
        lessonMemberEntityManager.addLessonMember(new LessonMemberDto(null,lessonDto.getOwnerId(),lessonDto.getId()));
    }

    public UserListVo addLessonMember(Integer id, String username) {
        lessonEntityManager.verifyLessonOwner(threadLocalUtils.getCurrentUser().getId(),id);
        UserDto user = userEntityManager.getUserByUsername(username);
        lessonMemberEntityManager.addLessonMember(new LessonMemberDto(null,user.getId(),id));
        return new UserListVo(user.getId(),user.getUsername(),user.getName(),user.getEmail(),userEntityManager.getMajorByMajorId(user.getMajorId()),user.getRole());
    }
}
