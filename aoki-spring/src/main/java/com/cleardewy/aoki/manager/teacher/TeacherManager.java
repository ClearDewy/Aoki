package com.cleardewy.aoki.manager.teacher;

import com.cleardewy.aoki.constant.ResultStatus;
import com.cleardewy.aoki.entity.dto.*;
import com.cleardewy.aoki.entity.vo.lesson.CreateLessonVo;
import com.cleardewy.aoki.entity.vo.lesson.TopicTimeVo;
import com.cleardewy.aoki.entity.vo.user.UserListVo;
import com.cleardewy.aoki.exception.AokiException;
import com.cleardewy.aoki.manager.entity.LessonEntityManager;
import com.cleardewy.aoki.manager.entity.MilestonesEntityManager;
import com.cleardewy.aoki.manager.entity.UserEntityManager;
import com.cleardewy.aoki.utils.ThreadLocalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public void createLesson(CreateLessonVo createLessonVo) {
        LessonDto lessonDto= new LessonDto(
                null,
                createLessonVo.getName(),
                createLessonVo.getIntroduction(),
                threadLocalUtils.getCurrentUser().getId(),
                createLessonVo.isTeamMemberLimit(),
                createLessonVo.isTopicMod(),
                createLessonVo.getAvatarURL()
        );

        lessonEntityManager.addLesson(lessonDto);
        lessonEntityManager.addLessonMember(new LessonMemberDto(null,lessonDto.getOwnerId(),lessonDto.getId()));
        if (!lessonDto.isTopicMod())
            lessonEntityManager.addTopicTime(new TopicTimeDto(null,null,null,lessonDto.getId()));
    }

    public UserListVo addLessonMember(Integer id, String username) {
        if (!lessonEntityManager.verifyLessonOwner(threadLocalUtils.getCurrentUser().getId(),id)){
            throw AokiException.forbidden();
        }
        UserDto user = userEntityManager.getUserByUsername(username);
        lessonEntityManager.addLessonMember(new LessonMemberDto(null,user.getId(),id));
        return new UserListVo(user.getId(),user.getUsername(),user.getName(),user.getEmail(),userEntityManager.getMajorByMajorId(user.getMajorId()),user.getRole());
    }

    public void removeLessonMembers(Integer id, Integer[] idList) {
        if (!lessonEntityManager.verifyLessonOwner(threadLocalUtils.getCurrentUser().getId(),id)){
            throw AokiException.forbidden();
        }
        if (idList.length!=0)
            lessonEntityManager.deleteLessonMembers(id, Arrays.asList(idList));
    }

    public void addMilestones(MilestonesDto milestonesDto) {
        milestonesEntityManager.addMilestones(milestonesDto);
    }


    public void deleteMilestones(Integer lessonId, Integer[] idList) {
        if (!lessonEntityManager.verifyLessonOwner(threadLocalUtils.getCurrentUser().getId(),lessonId)){
            throw AokiException.forbidden();
        }
        milestonesEntityManager.deleteMilestones(lessonId,Arrays.asList(idList));
    }

    public void updateMilestones(MilestonesDto milestonesDto) {
        milestonesEntityManager.updateMilestones(milestonesDto);
    }

    public void updateTopicTime(TopicTimeVo topicTimeVo) {
        if (!lessonEntityManager.verifyLessonOwner(threadLocalUtils.getCurrentUser().getId(),topicTimeVo.getLessonId())){
            throw AokiException.forbidden();
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            TopicTimeDto topicTimeDto=new TopicTimeDto(
                    null,
                    new Timestamp(sdf.parse(topicTimeVo.getBeginTime()).getTime()),
                    new Timestamp(sdf.parse(topicTimeVo.getEndTime()).getTime()),
                    topicTimeVo.getLessonId()
            );
            lessonEntityManager.updateTopicTime(topicTimeDto);
        }catch (ParseException ignored){
            throw AokiException.fail(ResultStatus.Message.DATE_ANOMALIES);
        }
    }

    public void createTopic(TopicDto topicDto) {
        topicDto.setOwnerId(threadLocalUtils.getCurrentUser().getId());
        lessonEntityManager.createTopic(topicDto);
    }


    public void updateTopic(TopicDto topicDto) {
        lessonEntityManager.updateTopic(topicDto);
    }

    public void deleteTopic(Integer id) {
        lessonEntityManager.deleteTopic(id);
    }
}
