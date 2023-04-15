package com.cleardewy.aoki.service.teacher.impl;

import com.cleardewy.aoki.entity.vo.lesson.CreateLessonVo;
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
    TeacherManager teacherManager;

    @Override
    public Result createLesson(CreateLessonVo createLessonVo) {
        teacherManager.createLesson(createLessonVo);
        return Result.success();
    }

    @Override
    public Result addLessonMember(Integer id, String username) {
        return Result.success(teacherManager.addLessonMember(id,username));
    }
}
