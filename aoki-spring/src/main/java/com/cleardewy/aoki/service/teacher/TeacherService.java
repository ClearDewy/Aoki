package com.cleardewy.aoki.service.teacher;

import com.cleardewy.aoki.entity.dto.*;
import com.cleardewy.aoki.entity.vo.lesson.*;
import com.cleardewy.aoki.utils.Result;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
public interface TeacherService {
    Result createLesson(CreateLessonVo createLessonVo);
    Result updateLesson(EditLessonVo editLessonVo);

    Result deleteLesson(Integer lessonId, String code);

    Result addLessonMember(Integer id, String username);

    Result removeLessonMembers(Integer lessonId, Integer id);

    Result addMilestones(MilestonesDto milestonesDto);



    Result deleteMilestones(Integer lessonId, Integer[] idList);

    Result updateMilestones(MilestonesDto milestonesDto);

    Result updateTopicTime(TopicTimeDto topicTimeDto);

    Result createTopic(EditTopicVo editTopicVo);


    Result updateTopic(EditTopicVo editTopicVo);

    Result deleteTopic(Integer id);

    Result getMyTopics(Integer lessonId);

    Result getTopicMembers(Integer id);

    Result getNoTopicMembers(Integer lessonId);

    Result addTopicMember(Integer topicId, Integer memberId);

    Result removeTopicMember(Integer topicId, Integer memberId);


    Result createTask(TaskDto taskDto);

    Result updateTask(TaskDto taskDto);

    Result deleteTask(Integer taskId);

    Result getOwnerTasks(Integer lessonId);

    Result toggleTaskPublish(Integer taskId);

    Result editTaskQuestions(QuestionDto[] questions);

    Result getTaskQuestions(Integer taskId);

    Result createScoreRule(ScoreRuleDto scoreRuleDto);

    Result updateScoreRule(ScoreRuleDto scoreRuleDto);

    Result deleteScoreRule(Integer srId);
    Result getScoreRule(Integer taskId);

    Result getTaskMember(Integer taskId);

    Result getNoTaskMember(Integer taskId);

    Result getTaskQuestionAnswer(Integer taskId, Integer id);

    Result getScoreRecord(Integer taskId, Integer id);


    Result submitScoreRecord(ScoreRecordVo scoreRecordVo);


}
