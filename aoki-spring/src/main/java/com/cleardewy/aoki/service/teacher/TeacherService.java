package com.cleardewy.aoki.service.teacher;

import com.cleardewy.aoki.entity.dto.MilestonesDto;
import com.cleardewy.aoki.entity.vo.lesson.CreateLessonVo;
import com.cleardewy.aoki.entity.vo.lesson.EditLessonVo;
import com.cleardewy.aoki.entity.vo.lesson.EditTopicVo;
import com.cleardewy.aoki.entity.vo.lesson.TopicTimeVo;
import com.cleardewy.aoki.utils.Result;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
public interface TeacherService {
    Result createLesson(CreateLessonVo createLessonVo);
    Result updateLesson(EditLessonVo editLessonVo);

    Result deleteLesson(Integer lessonId, String code);

    Result addLessonMember(Integer id, String username);

    Result removeLessonMembers(Integer id, Integer[] idList);

    Result addMilestones(MilestonesDto milestonesDto);



    Result deleteMilestones(Integer lessonId, Integer[] idList);

    Result updateMilestones(MilestonesDto milestonesDto);

    Result updateTopicTime(TopicTimeVo topicTimeVo);

    Result createTopic(EditTopicVo editTopicVo);


    Result updateTopic(EditTopicVo editTopicVo);

    Result deleteTopic(Integer id);

    Result getMyTopics(Integer lessonId);

    Result getTopicMembers(Integer id);

    Result getNoTopicMembers(Integer lessonId);

    Result addTopicMember(Integer topicId, Integer memberId);

    Result removeTopicMember(Integer topicId, Integer memberId);


}
