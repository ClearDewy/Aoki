package com.cleardewy.aoki.manager.admin;

import com.cleardewy.aoki.entity.vo.lesson.LessonListVo;
import com.cleardewy.aoki.manager.entity.LessonEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Component
public class AdminLessonManager {
    @Autowired
    LessonEntityManager lessonEntityManager;

    public List<LessonListVo> getAllLesson() {
        return lessonEntityManager.getAllLessonList();
    }
}
