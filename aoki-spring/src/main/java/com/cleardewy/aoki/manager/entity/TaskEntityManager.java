package com.cleardewy.aoki.manager.entity;

import com.cleardewy.aoki.entity.dto.QuestionDto;
import com.cleardewy.aoki.entity.dto.ScoreRuleDto;
import com.cleardewy.aoki.entity.dto.TaskDto;
import com.cleardewy.aoki.entity.vo.lesson.TaskListMemberVo;
import com.cleardewy.aoki.entity.vo.lesson.TaskListOwnerVo;
import com.cleardewy.aoki.exception.AokiException;
import com.cleardewy.aoki.mapper.TaskMapper;
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


    public void createTask(TaskDto taskDto) {
        if (taskMapper.createTask(taskDto)==0)
            throw AokiException.fail();
    }

    public void updateTask(TaskDto taskDto) {
        if (taskMapper.updateTask(taskDto)==0)
            throw AokiException.fail();
    }
    public void deleteTask(Integer taskId) {
        if (taskMapper.deleteTask(taskId)==0)
            throw AokiException.fail();
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
        if (taskMapper.toggleTaskPublish(taskId)==0)
            throw AokiException.notFound();
    }

    public List<QuestionDto> getTaskQuestions(Integer taskId) {
        return taskMapper.getTaskQuestions(taskId);
    }

    public void updateTaskQuestions(QuestionDto questionDto){
        if (taskMapper.updateTaskQuestions(questionDto)==0)
            throw AokiException.notFound();
    }

    public void createTaskQuestions(List<QuestionDto> questionList){
        if (questionList.isEmpty())return;
        if (taskMapper.createTaskQuestions(questionList)==0)
            throw AokiException.fail();
    }
    public void deleteTaskQuestions(List<Integer> questionIdList){
        if (questionIdList.isEmpty())return;
        if (taskMapper.deleteTaskQuestions(questionIdList)==0)
            throw AokiException.notFound();
    }

    public Boolean verifyScoreRuleOwner(Integer srId,Integer id){
        return taskMapper.verifyScoreRuleOwner(srId,id)!=0;
    }

    public void createScoreRule(ScoreRuleDto scoreRuleDto) {
        if (taskMapper.createScoreRule(scoreRuleDto)==0)
            throw AokiException.fail();
    }

    public void updateScoreRule(ScoreRuleDto scoreRuleDto) {
        if (taskMapper.updateScoreRule(scoreRuleDto)==0)
            throw AokiException.notFound();
    }

    public void deleteScoreRule(Integer srId) {
        if (taskMapper.deleteScoreRule(srId)==0)
            throw AokiException.notFound();
    }

    public List<ScoreRuleDto> getScoreRule(Integer taskId) {
        return taskMapper.getScoreRule(taskId);
    }
}
