package com.cleardewy.aoki.service.teacher.impl;

import com.cleardewy.aoki.entity.dto.MilestonesDto;
import com.cleardewy.aoki.entity.dto.TopicDto;
import com.cleardewy.aoki.entity.vo.lesson.CreateLessonVo;
import com.cleardewy.aoki.entity.vo.lesson.TopicTimeVo;
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

    @Override
    public Result removeLessonMembers(Integer id, Integer[] idList) {
        teacherManager.removeLessonMembers(id,idList);
        return Result.success();
    }

    @Override
    public Result addMilestones(MilestonesDto milestonesDto) {
        teacherManager.addMilestones(milestonesDto);
        return Result.success();
    }



    @Override
    public Result deleteMilestones(Integer lessonId, Integer[] idList) {
        teacherManager.deleteMilestones(lessonId,idList);
        return Result.success();
    }

    @Override
    public Result updateMilestones(MilestonesDto milestonesDto) {
        teacherManager.updateMilestones(milestonesDto);
        return Result.success();
    }

    @Override
    public Result updateTopicTime(TopicTimeVo topicTimeVo) {
        teacherManager.updateTopicTime(topicTimeVo);
        return Result.success();
    }

    @Override
    public Result createTopic(TopicDto topicDto) {
        teacherManager.createTopic(topicDto);
        return Result.success();
    }

    @Override
    public Result updateTopic(TopicDto topicDto) {
        teacherManager.updateTopic(topicDto);
        return Result.success();
    }

    @Override
    public Result deleteTopic(Integer id) {
        teacherManager.deleteTopic(id);
        return Result.success();
    }


}
