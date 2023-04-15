package com.cleardewy.aoki.mapper;

import com.cleardewy.aoki.entity.dto.LessonDto;
import com.cleardewy.aoki.entity.vo.lesson.LessonListVo;
import com.cleardewy.aoki.entity.vo.user.UserListVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Mapper
public interface LessonMapper {
    Integer addLesson(LessonDto lessonDto);

    List<LessonListVo> getLessonList(Integer id);

    LessonDto getLesson(Integer id);

    Integer verifyLessonOwner(Integer ownerId,Integer id);
}
