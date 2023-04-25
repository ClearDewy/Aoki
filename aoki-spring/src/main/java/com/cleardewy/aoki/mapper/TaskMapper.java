package com.cleardewy.aoki.mapper;

import com.cleardewy.aoki.entity.dto.*;
import com.cleardewy.aoki.entity.vo.lesson.TaskListMemberVo;
import com.cleardewy.aoki.entity.vo.lesson.TaskListOwnerVo;
import com.cleardewy.aoki.entity.vo.lesson.TaskQuestionAnswerList;
import io.lettuce.core.dynamic.batch.BatchSize;
import jakarta.validation.constraints.Size;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.List;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Mapper
public interface TaskMapper {
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
}
