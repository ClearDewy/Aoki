package com.cleardewy.aoki.manager.entity;

import com.cleardewy.aoki.constant.ResultStatus;
import com.cleardewy.aoki.entity.dto.*;
import com.cleardewy.aoki.entity.vo.lesson.*;
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
    public void updateLesson(EditLessonVo editLessonVo) {
        if (lessonMapper.updateLesson(editLessonVo)==0){
            throw new AokiException(ResultStatus.Status.FAIL);
        }
    }
    public void deleteLesson(Integer lessonId) {
        if (lessonMapper.deleteLesson(lessonId)==0)
            throw AokiException.notFound();
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
            throw AokiException.notFound();
    }

    public void updateTopicTime(TopicTimeDto topicTimeDto){
        if (lessonMapper.updateTopicTime(topicTimeDto)==0)
            throw AokiException.notFound();
    }

    public TopicTimeVo getTopicTime(Integer lessonId){
        return lessonMapper.getTopicTime(lessonId);
    }

    public void createTopic(TopicDto topicDto){
        if (lessonMapper.createTopic(topicDto)==0)
            throw AokiException.notFound();
    }

    public List<TopicListVo> getTopics(Integer lessonId){
        return lessonMapper.getTopics(lessonId);
    }

    public void updateTopic(TopicDto topicDto){
        if (lessonMapper.updateTopic(topicDto)==0)
            throw AokiException.notFound();
    }
    public void deleteTopic(Integer id){
        if (lessonMapper.deleteTopic(id)==0)
            throw AokiException.notFound();
    }

    public TopicListVo getMyTopic(Integer lessonId, Integer id) {
        return lessonMapper.getMyTopic(lessonId,id);
    }
    public List<TopicListVo> getMyTopics(Integer lessonId, Integer id) {
        return lessonMapper.getMyTopics(lessonId,id);
    }
    public Boolean verifyTopicOwner(Integer topicId,Integer id){
        return lessonMapper.verifyTopicOwner(topicId,id)!=0;
    }

    public LessonDto getTopicLesson(Integer id){
        return lessonMapper.getTopicLesson(id);
    }

    public void addTopicMember(TopicMemberDto topicMemberDto){
        if (lessonMapper.addTopicMember(topicMemberDto)==0)
            throw AokiException.notFound();
    }

    public void removeTopicMember(TopicMemberDto topicMemberDto){
        if (lessonMapper.removeTopicMember(topicMemberDto)==0)
            throw AokiException.notFound();
    }


    public List<TeamMemberVo> getTopicMembers(Integer id) {
        return lessonMapper.getTopicMembers(id);
    }

    public List<NoTopicMemberList> getNoTopicMembers(Integer lessonId) {
        return lessonMapper.getNoTopicMembers(lessonId);
    }

}
