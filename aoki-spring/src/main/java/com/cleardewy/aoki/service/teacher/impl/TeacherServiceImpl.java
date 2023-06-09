package com.cleardewy.aoki.service.teacher.impl;

import com.cleardewy.aoki.entity.dto.*;
import com.cleardewy.aoki.entity.vo.lesson.*;
import com.cleardewy.aoki.manager.teacher.TeacherManager;
import com.cleardewy.aoki.service.teacher.TeacherService;
import com.cleardewy.aoki.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherManager teacherManager;

    @Override
    public Result createLesson(CreateLessonVo createLessonVo) {
        teacherManager.createLesson(createLessonVo);
        return Result.success();
    }

    @Override
    public Result updateLesson(EditLessonVo editLessonVo) {
        teacherManager.updateLesson(editLessonVo);
        return Result.success();
    }

    @Override
    public Result deleteLesson(Integer lessonId, String code) {
        teacherManager.deleteLesson(lessonId,code);
        return Result.success();
    }

    @Override
    public Result getUsersByUsernames(String[] usernames) {
        return Result.success(teacherManager.getUsersByUsernames(usernames));
    }

    @Override
    public Result addLessonMember(Integer lessonId, Integer[] idList) {
        teacherManager.addLessonMember(lessonId,idList);
        return Result.success();
    }


    @Override
    public Result removeLessonMembers(Integer lesson, Integer id) {
        teacherManager.removeLessonMembers(lesson,id);
        return Result.success();
    }

    @Override
    public Result addMilestones(MilestonesDto milestonesDto) {
        teacherManager.addMilestones(milestonesDto);
        return Result.success();
    }



    @Override
    public Result deleteMilestones(Integer lessonId, Integer[] idList) {
        teacherManager.deleteMilestones(lessonId,idList);
        return Result.success();
    }

    @Override
    public Result updateMilestones(MilestonesDto milestonesDto) {
        teacherManager.updateMilestones(milestonesDto);
        return Result.success();
    }

    @Override
    public Result updateTopicTime(TopicTimeDto topicTimeDto) {
        teacherManager.updateTopicTime(topicTimeDto);
        return Result.success();
    }

    @Override
    public Result createTopic(EditTopicVo editTopicVo) {
        teacherManager.createTopic(editTopicVo);
        return Result.success();
    }

    @Override
    public Result updateTopic(EditTopicVo editTopicVo) {
        teacherManager.updateTopic(editTopicVo);
        return Result.success();
    }

    @Override
    public Result deleteTopic(Integer id) {
        teacherManager.deleteTopic(id);
        return Result.success();
    }

    @Override
    public Result getMyTopics(Integer lessonId) {
        return Result.success(teacherManager.getMyTopics(lessonId));
    }

    @Override
    public Result getTopicMembers(Integer id) {
        return Result.success(teacherManager.getTopicMembers(id));
    }

    @Override
    public Result getNoTopicMembers(Integer lessonId) {
        return Result.success(teacherManager.getNoTopicMembers(lessonId));
    }

    @Override
    public Result addTopicMember(Integer topicId, Integer memberId) {
        teacherManager.addTopicMember(topicId,memberId);
        return Result.success();
    }

    @Override
    public Result removeTopicMember(Integer topicId, Integer memberId) {
        teacherManager.removeTopicMember(topicId,memberId);
        return Result.success();
    }

    @Override
    public Result createTask(TaskDto taskDto) {
        teacherManager.createTask(taskDto);
        return Result.success();
    }

    @Override
    public Result updateTask(TaskDto taskDto) {
        teacherManager.updateTask(taskDto);
        return Result.success();
    }

    @Override
    public Result deleteTask(Integer taskId) {
        teacherManager.deleteTask(taskId);
        return Result.success();
    }

    @Override
    public Result getOwnerTasks(Integer lessonId) {
        return Result.success(teacherManager.getOwnerTasks(lessonId));
    }

    @Override
    public Result toggleTaskPublish(Integer taskId) {
        teacherManager.toggleTaskPublish(taskId);
        return Result.success();
    }

    @Override
    public Result editTaskQuestions(QuestionDto[] questions) {
        teacherManager.editTaskQuestions(questions);
        return Result.success();
    }

    @Override
    public Result getTaskQuestions(Integer taskId) {
        return Result.success(teacherManager.getTaskQuestions(taskId));
    }

    @Override
    public Result createScoreRule(ScoreRuleDto scoreRuleDto) {
        teacherManager.createScoreRule(scoreRuleDto);
        return Result.success();
    }

    @Override
    public Result updateScoreRule(ScoreRuleDto scoreRuleDto) {
        teacherManager.updateScoreRule(scoreRuleDto);
        return Result.success();
    }

    @Override
    public Result deleteScoreRule(Integer srId) {
        teacherManager.deleteScoreRule(srId);
        return Result.success();
    }
    @Override
    public Result getScoreRule(Integer taskId) {
        return Result.success(teacherManager.getScoreRule(taskId));
    }

    @Override
    public Result getTaskMember(Integer taskId) {
        return Result.success(teacherManager.getTaskMember(taskId));
    }

    @Override
    public Result getNoTaskMember(Integer taskId) {
        return Result.success(teacherManager.getNoTaskMember(taskId));
    }

    @Override
    public Result getTaskQuestionAnswer(Integer taskId, Integer id) {
        return Result.success(teacherManager.getTaskQuestionAnswer(taskId,id));
    }

    @Override
    public Result getScoreRecord(Integer taskId, Integer id) {
        return Result.success(teacherManager.getScoreRecord(taskId,id));
    }


    @Override
    public Result submitScoreRecord(ScoreRecordVo scoreRecordVo) {
        teacherManager.submitScoreRecord(scoreRecordVo);
        return Result.success();
    }
}
