package com.cleardewy.aoki.mapper;

import com.cleardewy.aoki.entity.dto.*;
import com.cleardewy.aoki.entity.vo.lesson.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Mapper
public interface TaskMapper {
    TaskDto getTask(Integer id);
    Integer createTask(TaskDto taskDto);
    Integer updateTask(TaskDto taskDto);
    Integer deleteTask(Integer id);

    List<TaskListOwnerVo> getOwnerTasks(Integer id, Integer lessonId);

    List<TaskListMemberVo> getMemberTasks(Integer id, Integer topicId);
    List<TaskListMemberVo> getMemberTasksTeam(Integer id, Integer topicId);

    Integer verifyTaskOwner(Integer taskId,Integer id);
    Integer toggleTaskPublish(Integer taskId);

    List<QuestionDto> getTaskQuestions(Integer taskId);

    Integer updateTaskQuestions(QuestionDto questionDto);
    Integer createTaskQuestions(List<QuestionDto> questionList);
    Integer deleteTaskQuestions(List<Integer> questionIdList);

    Integer verifyScoreRuleOwner(Integer srId,Integer id);

    Integer createScoreRule(ScoreRuleDto scoreRuleDto);
    Integer updateScoreRule(ScoreRuleDto scoreRuleDto);
    Integer deleteScoreRule(Integer srId);

    List<ScoreRuleDto> getScoreRule(Integer taskId);

    Integer submitAnswer(AnswerDto answerDto);
    List<TaskQuestionAnswerList>getQuestionAnswerList(Integer taskId,Integer id);
    Integer updateTaskSubmitted(TaskSubmittedDto taskSubmittedDto);

    List<TeamMemberVo> getTaskMember(Integer taskId);

    List<TeamMemberVo> getNoTaskMember(Integer taskId);

    List<ScoreEditVo> getScoreRecord(Integer taskId, Integer id);

    Integer submitScoreRecord(ScoreRecordDto scoreRuleDto);
}
