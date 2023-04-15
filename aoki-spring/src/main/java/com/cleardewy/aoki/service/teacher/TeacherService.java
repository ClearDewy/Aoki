package com.cleardewy.aoki.service.teacher;

import com.cleardewy.aoki.entity.vo.lesson.CreateLessonVo;
import com.cleardewy.aoki.utils.Result;
import org.springframework.stereotype.Service;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
public interface TeacherService {
    Result createLesson(CreateLessonVo createLessonVo);

    Result addLessonMember(Integer id, String username);
}
