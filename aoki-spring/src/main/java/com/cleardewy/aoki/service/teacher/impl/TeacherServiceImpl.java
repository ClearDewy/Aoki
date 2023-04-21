package com.cleardewy.aoki.service.teacher.impl;

import com.cleardewy.aoki.entity.dto.MilestonesDto;
import com.cleardewy.aoki.entity.vo.lesson.CreateLessonVo;
import com.cleardewy.aoki.entity.vo.lesson.EditTopicVo;
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
    public Result createTopic(EditTopicVo editTopicVo) {
        teacherManager.createTopic(editTopicVo);
        return Result.success();
    }

    @Override
    public Result updateTopic(EditTopicVo editTopicVo) {
        teacherManager.updateTopic(editTopicVo);
        return Result.success();
    }

    @Override
    public Result deleteTopic(Integer id) {
        teacherManager.deleteTopic(id);
        return Result.success();
    }

    @Override
    public Result getMyTopics(Integer lessonId) {
        return Result.success(teacherManager.getMyTopics(lessonId));
    }

    @Override
    public Result getTopicMembers(Integer id) {
        return Result.success(teacherManager.getTopicMembers(id));
    }

    @Override
    public Result getNoTopicMembers(Integer lessonId) {
        return Result.success(teacherManager.getNoTopicMembers(lessonId));
    }

    @Override
    public Result addTopicMember(Integer topicId, Integer memberId) {
        teacherManager.addTopicMember(topicId,memberId);
        return Result.success();
    }

    @Override
    public Result removeTopicMember(Integer topicId, Integer memberId) {
        teacherManager.removeTopicMember(topicId,memberId);
        return Result.success();
    }
}
