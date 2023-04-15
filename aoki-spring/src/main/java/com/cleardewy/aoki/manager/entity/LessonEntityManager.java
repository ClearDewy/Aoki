package com.cleardewy.aoki.manager.entity;

import com.cleardewy.aoki.constant.ResultStatus;
import com.cleardewy.aoki.entity.dto.LessonDto;
import com.cleardewy.aoki.entity.vo.lesson.LessonListVo;
import com.cleardewy.aoki.entity.vo.user.UserListVo;
import com.cleardewy.aoki.exception.AokiException;
import com.cleardewy.aoki.mapper.LessonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Component
public class LessonEntityManager {
    @Autowired
    LessonMapper lessonMapper;

    public void addLesson(LessonDto lessonDto){
        if (lessonMapper.addLesson(lessonDto)==0){
            throw new AokiException(ResultStatus.Status.FAIL);
        }

    }

    public List<LessonListVo> getLessonList(Integer id) {
        return lessonMapper.getLessonList(id);
    }

    public LessonDto getLesson(Integer id){
        LessonDto lesson = lessonMapper.getLesson(id);
        if (lesson==null)
            throw AokiException.notFound();
        return lesson;
    }

    public void verifyLessonOwner(Integer ownerId,Integer id){
        if (lessonMapper.verifyLessonOwner(ownerId,id)==0){
            throw AokiException.forbidden();
        }
    }
}
