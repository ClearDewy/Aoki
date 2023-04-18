package com.cleardewy.aoki.controller.teacher;

import com.cleardewy.aoki.annotation.AokiRole;
import com.cleardewy.aoki.entity.dto.MilestonesDto;
import com.cleardewy.aoki.entity.vo.lesson.CreateLessonVo;
import com.cleardewy.aoki.service.teacher.TeacherService;
import com.cleardewy.aoki.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static com.cleardewy.aoki.constant.RoleConstans.*;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@RestController
@AokiRole({TEACHER})
@RequestMapping("/api")
public class TeacherController {
    @Autowired
    TeacherService teacherService;
    @PostMapping("/create-lesson")
    Result createLesson(CreateLessonVo createLessonVo){
        return teacherService.createLesson(createLessonVo);
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
    @PostMapping("/get-milestones")
    Result addMilestones(Integer id){
        return teacherService.getMilestones(id);
    }
    @PostMapping("/delete-milestones")
    Result deleteMilestones(Integer lessonId,Integer[] idList){
        return teacherService.deleteMilestones(lessonId,idList);
    }
    @PostMapping("/update-milestones")
    Result updateMilestones(MilestonesDto milestonesDto){
        return teacherService.updateMilestones(milestonesDto);
    }
}
