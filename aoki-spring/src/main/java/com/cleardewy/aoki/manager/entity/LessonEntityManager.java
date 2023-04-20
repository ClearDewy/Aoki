package com.cleardewy.aoki.manager.entity;

import com.cleardewy.aoki.constant.ResultStatus;
import com.cleardewy.aoki.entity.dto.LessonDto;
import com.cleardewy.aoki.entity.dto.LessonMemberDto;
import com.cleardewy.aoki.entity.dto.TopicDto;
import com.cleardewy.aoki.entity.dto.TopicTimeDto;
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

    public boolean verifyLessonOwner(Integer ownerId,Integer id){
        return lessonMapper.verifyLessonOwner(ownerId,id)!=0;
    }

    public void addLessonMember(LessonMemberDto lessonMemberDto){
        if (lessonMapper.addLessonMember(lessonMemberDto)==0){
            throw new AokiException(ResultStatus.Status.FAIL);
        }
    }

    public boolean verifyLessonMember(Integer lessonId,Integer memberId){
        return lessonMapper.verifyLessonMember(lessonId,memberId)!=0;
    }
    public List<UserListVo> getLessonMember(Integer id) {
        return lessonMapper.getLessonMember(id);
    }

    public void deleteLessonMembers(Integer id, List<Integer> idList){
        lessonMapper.deleteLessonMembers(id,idList);
    }

    public void addTopicTime(TopicTimeDto topicTimeDto){
        if (lessonMapper.addTopicTime(topicTimeDto)==0)
            throw AokiException.fail();
    }

    public void updateTopicTime(TopicTimeDto topicTimeDto){
        if (lessonMapper.updateTopicTime(topicTimeDto)==0)
            throw AokiException.fail();
    }

    public TopicTimeDto getTopicTime(Integer id){
        return lessonMapper.getTopicTime(id);
    }

    public void createTopic(TopicDto topicDto){
        if (lessonMapper.createTopic(topicDto)==0)
            throw AokiException.fail();
    }

    public List<TopicDto> getTopics(Integer lessonId){
        return lessonMapper.getTopics(lessonId);
    }

    public void updateTopic(TopicDto topicDto){
        if (lessonMapper.updateTopic(topicDto)==0)
            throw AokiException.fail();
    }
    public void deleteTopic(Integer id){
        if (lessonMapper.deleteTopic(id)==0)
            throw AokiException.fail();
    }
}
