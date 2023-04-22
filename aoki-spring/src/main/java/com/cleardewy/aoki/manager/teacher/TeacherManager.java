package com.cleardewy.aoki.manager.teacher;

import com.cleardewy.aoki.constant.ResultStatus;
import com.cleardewy.aoki.entity.dto.*;
import com.cleardewy.aoki.entity.vo.lesson.*;
import com.cleardewy.aoki.entity.vo.user.EmailVerifyVo;
import com.cleardewy.aoki.entity.vo.user.UserListVo;
import com.cleardewy.aoki.exception.AokiException;
import com.cleardewy.aoki.manager.account.EmailVerifyManager;
import com.cleardewy.aoki.manager.entity.LessonEntityManager;
import com.cleardewy.aoki.manager.entity.MilestonesEntityManager;
import com.cleardewy.aoki.manager.entity.TeamEntityManager;
import com.cleardewy.aoki.manager.entity.UserEntityManager;
import com.cleardewy.aoki.utils.ThreadLocalUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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
    TeamEntityManager teamEntityManager;
    @Autowired
    EmailVerifyManager emailVerifyManager;

    public void createLesson(CreateLessonVo createLessonVo) {
        LessonDto lessonDto= new LessonDto(
                null,
                createLessonVo.getName(),
                createLessonVo.getIntroduction(),
                threadLocalUtils.getCurrentUser().getId(),
                createLessonVo.isTeamMode(),
                createLessonVo.isTopicMode(),
                createLessonVo.getAvatarURL()
        );

        lessonEntityManager.addLesson(lessonDto);
        lessonEntityManager.addLessonMember(new LessonMemberDto(null,lessonDto.getOwnerId(),lessonDto.getId()));
        if (!lessonDto.isTopicMode())
            lessonEntityManager.addTopicTime(new TopicTimeDto(null,null,null,lessonDto.getId()));
    }
    public void updateLesson(EditLessonVo editLessonVo) {
        Integer id=threadLocalUtils.getCurrentUser().getId();
        lessonEntityManager.verifyLessonOwner(id,editLessonVo.getId());
        lessonEntityManager.updateLesson(editLessonVo);
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
        LessonDto lesson = lessonEntityManager.getLesson(topicTimeVo.getLessonId());
        if (lesson.isTopicMode())
            throw AokiException.fail();
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

    public void createTopic(EditTopicVo editTopicVo) {
        lessonEntityManager.createTopic(new TopicDto(
                null,
                editTopicVo.getName(),
                0,
                editTopicVo.getLimit(),
                editTopicVo.getDifficult(),
                threadLocalUtils.getCurrentUser().getId(),
                editTopicVo.getLessonId()
        ));
    }


    public void updateTopic(EditTopicVo editTopicVo) {
        lessonEntityManager.updateTopic(new TopicDto(
                editTopicVo.getId(),
                editTopicVo.getName(),
                0,
                editTopicVo.getLimit(),
                editTopicVo.getDifficult(),
                threadLocalUtils.getCurrentUser().getId(),
                editTopicVo.getLessonId()
        ));
    }

    public void deleteTopic(Integer id) {
        lessonEntityManager.deleteTopic(id);
    }

    public List<TopicListVo> getMyTopics(Integer lessonId) {
        Integer id=threadLocalUtils.getCurrentUser().getId();
        if (!lessonEntityManager.verifyLessonMember(lessonId,id))
            throw AokiException.forbidden();
        return lessonEntityManager.getMyTopics(lessonId,id);
    }

    public Object getTopicMembers(Integer id) {
        Integer uid=threadLocalUtils.getCurrentUser().getId();
        if (!lessonEntityManager.verifyTopicOwner(id,uid))
            throw AokiException.forbidden();
        LessonDto lesson = lessonEntityManager.getTopicLesson(id);
        if (lesson.isTeamMode()){
            List<TeamVo> topicTeams = teamEntityManager.getTopicTeams(id);
            topicTeams.forEach(team -> {
                team.setMemberList(teamEntityManager.getTeamMembers(team.getId()));
            });

            return topicTeams;
        }else{
            return lessonEntityManager.getTopicMembers(id);
        }
    }

    public List<NoTopicMemberList> getNoTopicMembers(Integer lessonId) {
        Integer id=threadLocalUtils.getCurrentUser().getId();
        if (!lessonEntityManager.verifyLessonMember(lessonId,id))
            throw AokiException.forbidden();
        LessonDto lesson = lessonEntityManager.getLesson(lessonId);
        if (lesson.isTeamMode()){
            return teamEntityManager.getNoTopicTeams(lessonId);
        }else{
            return lessonEntityManager.getNoTopicMembers(lessonId);
        }
    }

    public void addTopicMember(Integer topicId, Integer memberId) {
        if (!lessonEntityManager.verifyTopicOwner(topicId,threadLocalUtils.getCurrentUser().getId()))
            throw AokiException.forbidden();
        lessonEntityManager.addTopicMember(new TopicMemberDto(null,topicId,memberId));
    }

    public void removeTopicMember(Integer topicId, Integer memberId) {
        if (!lessonEntityManager.verifyTopicOwner(topicId,threadLocalUtils.getCurrentUser().getId()))
            throw AokiException.forbidden();
        lessonEntityManager.removeTopicMember(new TopicMemberDto(null,topicId,memberId));
    }

    public void deleteLesson(Integer lessonId, String code) {
        UserDto user = threadLocalUtils.getCurrentUser();
        if (!lessonEntityManager.verifyLessonOwner(lessonId,user.getId()))
            throw AokiException.forbidden();
        emailVerifyManager.verifyCode(new EmailVerifyVo(user.getEmail(),code));
        lessonEntityManager.deleteLesson(lessonId);
    }
}
