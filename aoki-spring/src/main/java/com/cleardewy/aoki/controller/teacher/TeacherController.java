package com.cleardewy.aoki.controller.teacher;

import com.cleardewy.aoki.annotation.AokiRole;
import com.cleardewy.aoki.entity.dto.MilestonesDto;
import com.cleardewy.aoki.entity.vo.lesson.CreateLessonVo;
import com.cleardewy.aoki.entity.vo.lesson.EditLessonVo;
import com.cleardewy.aoki.entity.vo.lesson.EditTopicVo;
import com.cleardewy.aoki.entity.vo.lesson.TopicTimeVo;
import com.cleardewy.aoki.service.teacher.TeacherService;
import com.cleardewy.aoki.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    Result updateTopicTime(TopicTimeVo topicTimeVo){
        return teacherService.updateTopicTime(topicTimeVo);
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
}
