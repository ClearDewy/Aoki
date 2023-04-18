package com.cleardewy.aoki.service.teacher;

import com.cleardewy.aoki.entity.dto.MilestonesDto;
import com.cleardewy.aoki.entity.vo.lesson.CreateLessonVo;
import com.cleardewy.aoki.utils.Result;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
public interface TeacherService {
    Result createLesson(CreateLessonVo createLessonVo);

    Result addLessonMember(Integer id, String username);

    Result removeLessonMembers(Integer id, Integer[] idList);

    Result addMilestones(MilestonesDto milestonesDto);

    Result getMilestones(Integer id);

    Result deleteMilestones(Integer lessonId, Integer[] idList);

    Result updateMilestones(MilestonesDto milestonesDto);
}
