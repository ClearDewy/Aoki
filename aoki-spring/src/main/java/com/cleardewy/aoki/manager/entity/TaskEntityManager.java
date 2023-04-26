package com.cleardewy.aoki.manager.entity;

import com.cleardewy.aoki.entity.dto.*;
import com.cleardewy.aoki.entity.vo.lesson.*;
import com.cleardewy.aoki.mapper.TaskMapper;
import com.cleardewy.aoki.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Component
public class TaskEntityManager {
    @Autowired
    TaskMapper taskMapper;

    public TaskDto getTask(Integer id){
        return taskMapper.getTask(id);
    }

    public void createTask(TaskDto taskDto) {
        taskMapper.createTask(taskDto);
    }

    public void updateTask(TaskDto taskDto) {
        taskMapper.updateTask(taskDto);
    }
    public void deleteTask(Integer taskId) {
        taskMapper.deleteTask(taskId);
    }

    public List<TaskListOwnerVo> getOwnerTasks(Integer id, Integer lessonId) {
        return taskMapper.getOwnerTasks(id,lessonId);
    }

    public List<TaskListMemberVo> getMemberTasks(Integer id, Integer topicId) {
        return taskMapper.getMemberTasks(id,topicId);
    }
    public List<TaskListMemberVo> getMemberTasksTeam(Integer id, Integer topicId) {
        return taskMapper.getMemberTasksTeam(id,topicId);
    }

    public Boolean verifyTaskOwner(Integer taskId,Integer id){
        return taskMapper.verifyTaskOwner(taskId,id)!=0;
    }

    public void toggleTaskPublish(Integer taskId){
        taskMapper.toggleTaskPublish(taskId);
    }

    public List<QuestionDto> getTaskQuestions(Integer taskId) {
        return taskMapper.getTaskQuestions(taskId);
    }

    public void updateTaskQuestions(QuestionDto questionDto){
        taskMapper.updateTaskQuestions(questionDto);
    }

    public void createTaskQuestions(List<QuestionDto> questionList){
        if (questionList.isEmpty())return;
        taskMapper.createTaskQuestions(questionList);
    }
    public void deleteTaskQuestions(List<Integer> questionIdList){
        if (questionIdList.isEmpty())return;
        taskMapper.deleteTaskQuestions(questionIdList);
    }

    public Boolean verifyScoreRuleOwner(Integer srId,Integer id){
        return taskMapper.verifyScoreRuleOwner(srId,id)!=0;
    }

    public void createScoreRule(ScoreRuleDto scoreRuleDto) {
        taskMapper.createScoreRule(scoreRuleDto);
    }

    public void updateScoreRule(ScoreRuleDto scoreRuleDto) {
        taskMapper.updateScoreRule(scoreRuleDto);
    }

    public void deleteScoreRule(Integer srId) {
        taskMapper.deleteScoreRule(srId);
    }

    public List<ScoreRuleDto> getScoreRule(Integer taskId) {
        return taskMapper.getScoreRule(taskId);
    }

    public List<TaskQuestionAnswerList> getTaskQuestionAnswer(Integer taskId, int id) {
        return taskMapper.getQuestionAnswerList(taskId,id);
    }
    public void submitAnswer(AnswerDto answerDto){
        taskMapper.submitAnswer(answerDto);
    }

    public void updateTaskSubmitted(TaskSubmittedDto taskSubmittedDto){
        taskMapper.updateTaskSubmitted(taskSubmittedDto);
    }

    public List<TeamMemberVo> getTaskMember(Integer taskId) {
        return taskMapper.getTaskMember(taskId);
    }


    public List<TeamMemberVo> getNoTaskMember(Integer taskId) {
        return taskMapper.getNoTaskMember(taskId);
    }

    public List<ScoreEditVo> getScoreRecord(Integer taskId, Integer id) {
        return taskMapper.getScoreRecord(taskId,id);
    }

    public void submitScoreRecord(ScoreRecordDto scoreRecordDto){
        taskMapper.submitScoreRecord(scoreRecordDto);
    }
}
