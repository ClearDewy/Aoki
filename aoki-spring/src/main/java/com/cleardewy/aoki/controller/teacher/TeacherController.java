package com.cleardewy.aoki.controller.teacher;

import cn.hutool.json.JSON;
import com.cleardewy.aoki.annotation.AokiRole;
import com.cleardewy.aoki.constant.ResultStatus;
import com.cleardewy.aoki.entity.dto.*;
import com.cleardewy.aoki.entity.vo.lesson.*;
import com.cleardewy.aoki.service.teacher.TeacherService;
import com.cleardewy.aoki.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.cleardewy.aoki.constant.RoleConstans.*;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@RestController
@AokiRole({TEACHER})
@RequestMapping("/api")
@Slf4j
public class TeacherController {
    @Autowired
    TeacherService teacherService;
    @PostMapping("/create-lesson")
    Result createLesson(CreateLessonVo createLessonVo){
        return teacherService.createLesson(createLessonVo);
    }
    @PostMapping("/update-lesson")
    Result updateLesson(EditLessonVo editLessonVo){
        return teacherService.updateLesson(editLessonVo);
    }
    @PostMapping("/delete-lesson")
    Result deleteLesson(Integer lessonId,String code){
        return teacherService.deleteLesson(lessonId,code);
    }
    @PostMapping("/add-lesson-member")
    Result addLessonMember(Integer id,String username){
        return teacherService.addLessonMember(id,username);
    }

    @PostMapping("/remove-lesson-member")
    Result removeLessonMembers(Integer id, Integer[] idList){
        return teacherService.removeLessonMembers(id,idList);
    }

    @PostMapping("/add-milestones")
    Result addMilestones(MilestonesDto milestonesDto){
        return teacherService.addMilestones(milestonesDto);
    }

    @PostMapping("/delete-milestones")
    Result deleteMilestones(Integer lessonId,Integer[] idList){
        return teacherService.deleteMilestones(lessonId,idList);
    }
    @PostMapping("/update-milestones")
    Result updateMilestones(MilestonesDto milestonesDto){
        return teacherService.updateMilestones(milestonesDto);
    }

    @PostMapping("/update-topic-time")
    Result updateTopicTime(TopicTimeDto topicTimeDto){
        return teacherService.updateTopicTime(topicTimeDto);
    }

    @PostMapping("/create-topic")
    Result createTopic(EditTopicVo editTopicVo){
        return teacherService.createTopic(editTopicVo);
    }

    @PostMapping("/update-topic")
    Result updateTopic(EditTopicVo editTopicVo){
        return teacherService.updateTopic(editTopicVo);
    }

    @PostMapping("/delete-topic")
    Result deleteTopic(Integer id){
        return teacherService.deleteTopic(id);
    }
    @PostMapping("/get-my-topics")
    Result getMyTopics(Integer lessonId){
        return teacherService.getMyTopics(lessonId);
    }
    @PostMapping("/get-topic-members")
    Result getTopicMembers(Integer id){
        return teacherService.getTopicMembers(id);
    }

    @PostMapping("/get-no-topic-members")
    Result getNoTopicMembers(Integer lessonId){
        return teacherService.getNoTopicMembers(lessonId);
    }
    @PostMapping("/add-topic-member-teacher")
    Result addTopicMember(Integer topicId,Integer memberId){
        return teacherService.addTopicMember(topicId,memberId);
    }

    @PostMapping("/remove-topic-member-teacher")
    Result removeTopicMember(Integer topicId,Integer memberId){
        return teacherService.removeTopicMember(topicId,memberId);
    }

    @PostMapping("/create-task")
    Result createTask(TaskDto taskDto){
        return teacherService.createTask(taskDto);
    }
    @PostMapping("/update-task")
    Result updateTask(TaskDto taskDto){
        return teacherService.updateTask(taskDto);
    }
    @PostMapping("/delete-task")
    Result deleteTask(Integer taskId){
        return teacherService.deleteTask(taskId);
    }

    @PostMapping("/get-owner-tasks")
    Result getOwnerTasks(Integer lessonId){
        return teacherService.getOwnerTasks(lessonId);
    }
    @PostMapping("/toggle-task-publish")
    Result toggleTaskPublish(Integer taskId){
        return teacherService.toggleTaskPublish(taskId);
    }

    @PostMapping("/get-task-questions")
    Result getTaskQuestion(Integer taskId){
        return teacherService.getTaskQuestions(taskId);
    }
    @PostMapping("/edit-task-questions")
    Result editTaskQuestions(@RequestBody QuestionDto[] questions){
        return teacherService.editTaskQuestions(questions);
    }
    @PostMapping("/create-score-rule")
    Result createScoreRule(ScoreRuleDto scoreRuleDto){
        return teacherService.createScoreRule(scoreRuleDto);
    }
    @PostMapping("/update-score-rule")
    Result updateScoreRule(ScoreRuleDto scoreRuleDto){
        return teacherService.updateScoreRule(scoreRuleDto);
    }
    @PostMapping("/delete-score-rule")
    Result deleteScoreRule(Integer srId){
        return teacherService.deleteScoreRule(srId);
    }
    @PostMapping("/get-score-rule")
    Result getScoreRule(Integer taskId){
        return teacherService.getScoreRule(taskId);
    }

    @PostMapping("/get-task-member")
    Result getTaskMember(Integer taskId){
        return teacherService.getTaskMember(taskId);
    }
    @PostMapping("/get-no-task-member")
    Result getNoTaskMember(Integer taskId){
        return teacherService.getNoTaskMember(taskId);
    }

    @PostMapping("/get-task-question-answer-username")
    Result getTaskQuestionAnswerUsername(Integer taskId,String username){
        return teacherService.getTaskQuestionAnswerUsername(taskId,username);
    }
    @PostMapping("/get-task-question-answer-team")
    Result getTaskQuestionAnswerTeam(Integer taskId,Integer id){
        return teacherService.getTaskQuestionAnswerTeam(taskId,id);
    }

    @PostMapping("/get-score-record")
    Result getScoreRecord(Integer taskId,String username){
        return teacherService.getScoreRecord(taskId,username);
    }
    @PostMapping("/get-score-record-team")
    Result getScoreRecordTeam(Integer taskId,Integer id){
        return teacherService.getScoreRecordTeam(taskId,id);
    }

    @PostMapping("/submit-score-record")
    Result submitScoreRecord(@RequestBody ScoreRecordVo scoreRecordVo){
        return teacherService.submitScoreRecord(scoreRecordVo);
    }


}
