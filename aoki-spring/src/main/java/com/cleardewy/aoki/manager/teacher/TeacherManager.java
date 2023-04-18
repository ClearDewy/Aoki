package com.cleardewy.aoki.manager.teacher;

import com.cleardewy.aoki.entity.dto.LessonDto;
import com.cleardewy.aoki.entity.dto.LessonMemberDto;
import com.cleardewy.aoki.entity.dto.MilestonesDto;
import com.cleardewy.aoki.entity.dto.UserDto;
import com.cleardewy.aoki.entity.vo.lesson.CreateLessonVo;
import com.cleardewy.aoki.entity.vo.user.UserListVo;
import com.cleardewy.aoki.manager.entity.LessonEntityManager;
import com.cleardewy.aoki.manager.entity.LessonMemberEntityManager;
import com.cleardewy.aoki.manager.entity.MilestonesEntityManager;
import com.cleardewy.aoki.manager.entity.UserEntityManager;
import com.cleardewy.aoki.utils.ThreadLocalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

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
    MilestonesEntityManager milestonesEntityManager;

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

    public void removeLessonMembers(Integer id, Integer[] idList) {
        lessonEntityManager.verifyLessonOwner(threadLocalUtils.getCurrentUser().getId(),id);
        if (idList.length!=0)
            lessonMemberEntityManager.deleteLessonMembers(id, Arrays.asList(idList));
    }

    public void addMilestones(MilestonesDto milestonesDto) {
        milestonesEntityManager.addMilestones(milestonesDto);
    }

    public List<MilestonesDto> getMilestones(Integer id) {
        return milestonesEntityManager.gerMilestones(id);
    }

    public void deleteMilestones(Integer lessonId, Integer[] idList) {
        lessonEntityManager.verifyLessonOwner(threadLocalUtils.getCurrentUser().getId(),lessonId);
        milestonesEntityManager.deleteMilestones(lessonId,Arrays.asList(idList));
    }

    public void updateMilestones(MilestonesDto milestonesDto) {
        milestonesEntityManager.updateMilestones(milestonesDto);
    }
}
