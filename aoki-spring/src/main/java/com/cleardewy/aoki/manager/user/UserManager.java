package com.cleardewy.aoki.manager.user;

import com.cleardewy.aoki.constant.ResultStatus;
import com.cleardewy.aoki.entity.dto.*;
import com.cleardewy.aoki.entity.vo.lesson.*;
import com.cleardewy.aoki.entity.vo.user.*;
import com.cleardewy.aoki.exception.AokiException;
import com.cleardewy.aoki.manager.account.EmailVerifyManager;
import com.cleardewy.aoki.manager.entity.*;
import com.cleardewy.aoki.manager.file.FileManager;
import com.cleardewy.aoki.utils.RedisUtils;
import com.cleardewy.aoki.utils.ThreadLocalUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Slf4j
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
    TeamEntityManager teamEntityManager;

    @Autowired
    FileManager fileManager;
    @Autowired
    RedisUtils redisUtils;
    @Autowired
    MilestonesEntityManager milestonesEntityManager;
    @Autowired
    TaskEntityManager taskEntityManager;


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
        if (!lessonEntityManager.verifyLessonMember(id,threadLocalUtils.getCurrentUser().getId())){
            throw AokiException.forbidden();
        }
        return lessonEntityManager.getLesson(id);
    }

    public List<UserListVo> getLessonMember(Integer id) {
        if (!lessonEntityManager.verifyLessonMember(id,threadLocalUtils.getCurrentUser().getId())){
            throw AokiException.forbidden();
        }
        return lessonEntityManager.getLessonMember(id);
    }

    public List<MilestonesDto> getMilestones(Integer id) {
        if (!lessonEntityManager.verifyLessonMember(id,threadLocalUtils.getCurrentUser().getId())){
            throw AokiException.forbidden();
        }
        return milestonesEntityManager.gerMilestones(id);
    }

    public void createTeam(String name, Integer lessonId) {
        Integer id=threadLocalUtils.getCurrentUser().getId();
//        lessonEntityManager.verifyLessonMember(lessonId,id);
        if (teamEntityManager.getTeamId(lessonId,id)!=null){
            throw AokiException.fail(ResultStatus.Message.HAVE_TEAM);
        }
        TeamDto teamDto=new TeamDto(null,name,lessonId,id);
        teamEntityManager.createTeam(teamDto);
        teamEntityManager.addTeamMember(new TeamMemberDto(null,teamDto.getId(),id));
    }

    public List<TeamVo> getTeams(Integer lessonId) {
        if (!lessonEntityManager.verifyLessonMember(lessonId,threadLocalUtils.getCurrentUser().getId())){
            throw AokiException.forbidden();
        }
        List<TeamVo> teamList=teamEntityManager.getTeams(lessonId);
        teamList.forEach((team -> {
            team.setMemberList(teamEntityManager.getTeamMembers(team.getId()));
        }));
        return teamList;
    }

    public List<TeamVo> getNoTeamMembers(Integer lessonId) {
        return teamEntityManager.getNoTeamMembers(lessonId);
    }

    public void addTeamMember(Integer teamId,Integer id) {
        if (!teamEntityManager.verifyTeamOwner(threadLocalUtils.getCurrentUser().getId(),teamId))
            throw AokiException.forbidden();
        if (!lessonEntityManager.verifyLessonMember(teamEntityManager.getTeamLessonId(teamId),id)){
            throw AokiException.forbidden();
        }
        teamEntityManager.addTeamMember(new TeamMemberDto(null,teamId,id));
    }

    public void removeTeamMember(Integer teamId, Integer id) {
        UserDto currentUser = threadLocalUtils.getCurrentUser();
        if (!teamEntityManager.verifyTeamOwner(currentUser.getId(),teamId))
            throw AokiException.forbidden();
        if (currentUser.getId().equals(id)){
            teamEntityManager.deleteTeam(teamId);
            Integer lessonId = teamEntityManager.getTeamLessonId(teamId);
            TopicListVo myTopic = lessonEntityManager.getMyTopic(lessonId, teamId);
            if (myTopic!=null){
                lessonEntityManager.removeTopicMember(new TopicMemberDto(null,myTopic.getId(),teamId));
                taskEntityManager.deleteAnswers(myTopic.getId(),id);
                taskEntityManager.deleteTaskSubmitted(myTopic.getId(), id);
            }

        }else{
            teamEntityManager.removeTeamMember(teamId,id);
        }
    }

    public TopicTimeVo getTopicTime(Integer lessonId) {
        return lessonEntityManager.getTopicTime(lessonId);
    }

    public List<TopicListVo> getTopics(Integer lessonId) {
        return lessonEntityManager.getTopics(lessonId);
    }

    public TopicListVo getMyTopic(Integer lessonId) {
        Integer id=threadLocalUtils.getCurrentUser().getId();
        if (!lessonEntityManager.verifyLessonMember(lessonId,id))
            throw AokiException.forbidden();
        LessonDto lesson = lessonEntityManager.getLesson(lessonId);

        if (lesson.isTeamMode()){
            if ((id = teamEntityManager.getTeamId(lessonId, id))==null)
                throw AokiException.fail(ResultStatus.Message.HAVE_NO_TEAM);
        }
        return lessonEntityManager.getMyTopic(lessonId,id);
    }

    public void addTopicMember(Integer topicId) {
        Integer id=threadLocalUtils.getCurrentUser().getId();
        LessonDto lesson = lessonEntityManager.getTopicLesson(topicId);
        TopicTimeVo topicTimeVo = lessonEntityManager.getTopicTime(lesson.getId());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        try{
            if (topicTimeVo.getBeginTime().isBlank()||now.before(sdf.parse(topicTimeVo.getBeginTime()))||now.after(sdf.parse(topicTimeVo.getEndTime())))
                throw AokiException.fail(ResultStatus.Message.NOT_WITHIN_TIME_RANGE);
        }catch (ParseException e) {
            throw new RuntimeException(e);
        }
        if (!lessonEntityManager.verifyLessonMember(lesson.getId(),id))
            throw AokiException.forbidden();
        if (lesson.isTeamMode()){
            if ((id = teamEntityManager.getTeamId(lesson.getId(), id))==null)
                throw AokiException.fail(ResultStatus.Message.HAVE_NO_TEAM);
        }
        TopicListVo myTopic = lessonEntityManager.getMyTopic(lesson.getId(), id);
        if (myTopic!=null)
            throw AokiException.fail(ResultStatus.Message.TOPIC_EXIT);

        lessonEntityManager.addTopicMember(new TopicMemberDto(null,topicId,id));
    }

    public void removeTopicMember(Integer topicId) {
        Integer id=threadLocalUtils.getCurrentUser().getId();
        LessonDto lesson = lessonEntityManager.getTopicLesson(topicId);
        TopicTimeVo topicTimeVo = lessonEntityManager.getTopicTime(lesson.getId());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        try{
            if (now.before(sdf.parse(topicTimeVo.getBeginTime()))||now.after(sdf.parse(topicTimeVo.getEndTime())))
                throw AokiException.fail(ResultStatus.Message.NOT_WITHIN_TIME_RANGE);
        }catch (ParseException e) {
            throw new RuntimeException(e);
        }
        if (!lessonEntityManager.verifyLessonMember(lesson.getId(),id))
            throw AokiException.forbidden();
        if (lesson.isTeamMode()){
            if ((id = teamEntityManager.getTeamId(lesson.getId(), id))==null)
                throw AokiException.fail(ResultStatus.Message.HAVE_NO_TEAM);
        }
        lessonEntityManager.removeTopicMember(new TopicMemberDto(null,topicId,id));
    }

    public List<TaskListMemberVo> getMemberTasks(Integer topicId) {
        Integer id=threadLocalUtils.getCurrentUser().getId();
        LessonDto lesson = lessonEntityManager.getLessonByTopic(topicId);
        if (lesson.isTeamMode()){
            id=teamEntityManager.getTeamId(lesson.getId(),id);
        }
        return taskEntityManager.getMemberTasks(id,topicId);
    }


    public List<TaskQuestionAnswerList> getTaskQuestionAnswer(Integer taskId) {
        int id=threadLocalUtils.getCurrentUser().getId();
        LessonDto lesson = lessonEntityManager.getLessonByTaskId(taskId);
        if (lesson.isTeamMode()){
            id=teamEntityManager.getTeamId(lesson.getId(),id);
        }
        return taskEntityManager.getTaskQuestionAnswer(taskId,id);
    }

    public void submitTask(SubmitTaskVo submitTaskVo, Boolean submitted) {
        Integer id=threadLocalUtils.getCurrentUser().getId();
        TaskDto task = taskEntityManager.getTask(submitTaskVo.getTaskId());
        Date now=new Date();
        if (now.before(task.getBeginTime())||now.after(task.getEndTime()))
            throw AokiException.fail(ResultStatus.Message.NOT_WITHIN_TIME_RANGE);
        LessonDto lesson = lessonEntityManager.getLessonByTaskId(submitTaskVo.getTaskId());
        if (lesson.isTeamMode()){
            id=teamEntityManager.getTeamId(lesson.getId(),id);
        }
        Integer finalId = id;
        submitTaskVo.getAnswerList().forEach((submitAnswer)-> taskEntityManager.submitAnswer(new AnswerDto(null,submitAnswer.questionId,submitAnswer.answerContent, finalId)));
        taskEntityManager.updateTaskSubmitted(new TaskSubmittedDto(null,submitTaskVo.getTaskId(),id,submitted));
    }
}
