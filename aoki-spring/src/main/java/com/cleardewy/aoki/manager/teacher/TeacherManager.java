package com.cleardewy.aoki.manager.teacher;

import com.cleardewy.aoki.constant.ResultStatus;
import com.cleardewy.aoki.entity.dto.*;
import com.cleardewy.aoki.entity.vo.lesson.*;
import com.cleardewy.aoki.entity.vo.user.EmailVerifyVo;
import com.cleardewy.aoki.entity.vo.user.UserListVo;
import com.cleardewy.aoki.exception.AokiException;
import com.cleardewy.aoki.manager.account.EmailVerifyManager;
import com.cleardewy.aoki.manager.entity.*;
import com.cleardewy.aoki.utils.ThreadLocalUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Slf4j
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
    @Autowired
    TaskEntityManager taskEntityManager;

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

    public void removeLessonMembers(Integer lessonId, Integer id) {
        Integer uid=threadLocalUtils.getCurrentUser().getId();
        if (uid.equals(id)){
            throw AokiException.fail();
        }

        if (!lessonEntityManager.verifyLessonOwner(uid,lessonId)){
            throw AokiException.forbidden();
        }
        lessonEntityManager.deleteLessonMembers(lessonId, id);
        TopicListVo myTopic = lessonEntityManager.getMyTopic(lessonId, id);
        if (myTopic!=null)
            lessonEntityManager.removeTopicMember(new TopicMemberDto(null, myTopic.getId(), id));
        Integer teamId = teamEntityManager.getTeamId(lessonId, id);
        if (teamId!=null)
            teamEntityManager.removeTeamMember(teamId,id);

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

    public void updateTopicTime(TopicTimeDto topicTimeDto) {
        if (!lessonEntityManager.verifyLessonOwner(threadLocalUtils.getCurrentUser().getId(),topicTimeDto.getLessonId())){
            throw AokiException.forbidden();
        }
        LessonDto lesson = lessonEntityManager.getLesson(topicTimeDto.getLessonId());
        if (lesson.isTopicMode())
            throw AokiException.fail();
        lessonEntityManager.updateTopicTime(topicTimeDto);
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
        if (!lessonEntityManager.verifyNoTopic(lessonEntityManager.getLessonByTopic(topicId).getId(),memberId))
            throw AokiException.fail();
        lessonEntityManager.addTopicMember(new TopicMemberDto(null,topicId,memberId));
    }

    public void removeTopicMember(Integer topicId, Integer memberId) {
        if (!lessonEntityManager.verifyTopicOwner(topicId,threadLocalUtils.getCurrentUser().getId()))
            throw AokiException.forbidden();
        taskEntityManager.deleteAnswers(topicId,memberId);
        taskEntityManager.deleteTaskSubmitted(topicId, memberId);
        lessonEntityManager.removeTopicMember(new TopicMemberDto(null,topicId,memberId));
    }

    public void deleteLesson(Integer lessonId, String code) {
        UserDto user = threadLocalUtils.getCurrentUser();
        if (!lessonEntityManager.verifyLessonOwner(user.getId(),lessonId))
            throw AokiException.forbidden();
        emailVerifyManager.verifyCode(new EmailVerifyVo(user.getEmail(),code));
        lessonEntityManager.deleteLesson(lessonId);
    }

    public void createTask(TaskDto taskDto) {
        Integer id=threadLocalUtils.getCurrentUser().getId();
        if (!lessonEntityManager.verifyTopicOwner(taskDto.getTopicId(),id))
            throw AokiException.forbidden();
        taskEntityManager.createTask(taskDto);
    }

    public void updateTask(TaskDto taskDto) {
        Integer id=threadLocalUtils.getCurrentUser().getId();
        if (!lessonEntityManager.verifyTopicOwner(taskDto.getTopicId(),id))
            throw AokiException.forbidden();
        taskEntityManager.updateTask(taskDto);
    }

    public void deleteTask(Integer taskId) {
        Integer id=threadLocalUtils.getCurrentUser().getId();
        if (!lessonEntityManager.verifyTopicOwner(taskId,id))
            throw AokiException.forbidden();
        taskEntityManager.deleteTask(taskId);
    }

    public List<TaskListOwnerVo> getOwnerTasks(Integer lessonId) {
        Integer id=threadLocalUtils.getCurrentUser().getId();
        return taskEntityManager.getOwnerTasks(id,lessonId);
    }

    public void toggleTaskPublish(Integer taskId) {
        Integer id=threadLocalUtils.getCurrentUser().getId();
        if (!taskEntityManager.verifyTaskOwner(taskId,id))
            throw AokiException.forbidden();
        taskEntityManager.toggleTaskPublish(taskId);
    }

    public void editTaskQuestions(QuestionDto[] questions) {
        Integer id=threadLocalUtils.getCurrentUser().getId();
        Integer taskId=questions[0].getTaskId();
        if (!taskEntityManager.verifyTaskOwner(taskId,id))
            throw AokiException.forbidden();

        Set<Integer> oldQuestionId = taskEntityManager.getTaskQuestions(taskId).stream()
                .map(QuestionDto::getId)
                .collect(Collectors.toSet());
        List<QuestionDto>createQuestionIdList=new ArrayList<>();
        for (QuestionDto question : questions) {
            if (oldQuestionId.contains(question.getId())) {
                oldQuestionId.remove(question.getId());
                taskEntityManager.updateTaskQuestions(question);
            }else{
                createQuestionIdList.add(question);
            }
        }
        taskEntityManager.createTaskQuestions(createQuestionIdList);
        taskEntityManager.deleteTaskQuestions(oldQuestionId.stream().toList());
    }

    public List<QuestionDto> getTaskQuestions(Integer taskId) {
        return taskEntityManager.getTaskQuestions(taskId);
    }

    public void createScoreRule(ScoreRuleDto scoreRuleDto) {
        Integer id=threadLocalUtils.getCurrentUser().getId();
        if (!taskEntityManager.verifyTaskOwner(scoreRuleDto.getTaskId(),id))
            throw AokiException.forbidden();
        taskEntityManager.createScoreRule(scoreRuleDto);
    }

    public void updateScoreRule(ScoreRuleDto scoreRuleDto) {
        Integer id=threadLocalUtils.getCurrentUser().getId();
        if (!taskEntityManager.verifyScoreRuleOwner(scoreRuleDto.getTaskId(),id))
            throw AokiException.forbidden();
        taskEntityManager.updateScoreRule(scoreRuleDto);
    }

    public void deleteScoreRule(Integer srId) {
        Integer id=threadLocalUtils.getCurrentUser().getId();
        if (!taskEntityManager.verifyScoreRuleOwner(srId,id))
            throw AokiException.forbidden();
        taskEntityManager.deleteScoreRule(srId);
    }
    public List<ScoreRuleDto> getScoreRule(Integer taskId) {
        Integer id=threadLocalUtils.getCurrentUser().getId();
        if (!taskEntityManager.verifyTaskOwner(taskId,id))
            throw AokiException.forbidden();
        return taskEntityManager.getScoreRule(taskId);
    }

    public Object getTaskMember(Integer taskId) {
        LessonDto lesson = lessonEntityManager.getLessonByTaskId(taskId);
        if (lesson.isTeamMode()){
            List<TeamVo> topicTeams = teamEntityManager.getTaskMemberTeam(taskId);
            topicTeams.forEach(team -> {
                team.setMemberList(teamEntityManager.getTeamMembers(team.getId()));
            });
            return topicTeams;
        }else{
            return taskEntityManager.getTaskMember(taskId);
        }
    }

    public Object getNoTaskMember(Integer taskId) {
        LessonDto lesson = lessonEntityManager.getLessonByTaskId(taskId);
        if (lesson.isTeamMode()){
            List<TeamVo> topicTeams = teamEntityManager.getNoTaskMemberTeam(taskId);
            topicTeams.forEach(team -> {
                team.setMemberList(teamEntityManager.getTeamMembers(team.getId()));
            });
            return topicTeams;
        }else{
            return taskEntityManager.getNoTaskMember(taskId);
        }
    }


    public List<TaskQuestionAnswerList> getTaskQuestionAnswer(Integer taskId, Integer id) {
        if (!taskEntityManager.verifyTaskOwner(taskId,threadLocalUtils.getCurrentUser().getId()))
            throw AokiException.forbidden();
        return taskEntityManager.getTaskQuestionAnswer(taskId,id);
    }

    public List<ScoreEditVo> getScoreRecord(Integer taskId, Integer id) {
        if (!taskEntityManager.verifyTaskOwner(taskId,threadLocalUtils.getCurrentUser().getId()))
            throw AokiException.forbidden();
        return taskEntityManager.getScoreRecord(taskId,id);
    }

    public void submitScoreRecord(ScoreRecordVo scoreRecordVo) {
        if (!taskEntityManager.verifyTaskOwner(scoreRecordVo.getTaskId(),threadLocalUtils.getCurrentUser().getId()))
            throw AokiException.forbidden();

        LessonDto lesson = lessonEntityManager.getLessonByTaskId(scoreRecordVo.getTaskId());
        Integer id;
        if (lesson.isTeamMode()){
            id=scoreRecordVo.getTeamId();
        }else{
            id=userEntityManager.getUserByUsername(scoreRecordVo.getUsername()).getId();
        }
        scoreRecordVo.getScoreList().forEach(sr->taskEntityManager.submitScoreRecord(new ScoreRecordDto(
                null,sr.scoreRuleId,scoreRecordVo.getTaskId(),id, sr.score
        )));
    }
}
