package com.cleardewy.aoki.controller.teacher;

import com.cleardewy.aoki.annotation.AokiRole;
import com.cleardewy.aoki.entity.vo.lesson.CreateLessonVo;
import com.cleardewy.aoki.service.teacher.TeacherService;
import com.cleardewy.aoki.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
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
public class TeacherController {
    @Autowired
    TeacherService teacherService;
    @RequestMapping("/create-lesson")
    Result createLesson(CreateLessonVo createLessonVo){
        return teacherService.createLesson(createLessonVo);
    }
    @RequestMapping("/add-lesson-member")
    Result addLessonMember(Integer id,String username){
        return teacherService.addLessonMember(id,username);
    }
}
