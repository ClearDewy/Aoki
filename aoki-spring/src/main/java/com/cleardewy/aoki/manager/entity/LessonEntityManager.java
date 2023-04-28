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
        lessonMapper.addLesson(lessonDto);
    }
    public void updateLesson(EditLessonVo editLessonVo) {
        lessonMapper.updateLesson(editLessonVo);
    }
    public void deleteLesson(Integer lessonId) {
        lessonMapper.deleteLesson(lessonId);
    }

    public List<LessonListVo> getLessonList(Integer id) {
        return lessonMapper.getLessonList(id);
    }

    public LessonDto getLesson(Integer id){
        return lessonMapper.getLesson(id);
    }

    public LessonDto getLessonByTopic(Integer topicId){
        return lessonMapper.getLessonByTopic(topicId);
    }

    public boolean verifyLessonOwner(Integer ownerId,Integer id){
        return lessonMapper.verifyLessonOwner(ownerId,id)!=0;
    }

    public void addLessonMember(LessonMemberDto lessonMemberDto){
        lessonMapper.addLessonMember(lessonMemberDto);
    }

    public boolean verifyLessonMember(Integer lessonId,Integer memberId){
        return lessonMapper.verifyLessonMember(lessonId,memberId)!=0;
    }
    public List<UserListVo> getLessonMember(Integer id) {
        return lessonMapper.getLessonMember(id);
    }

    public void deleteLessonMembers(Integer lessonId,Integer id){
        lessonMapper.deleteLessonMembers(lessonId,id);
    }

    public void addTopicTime(TopicTimeDto topicTimeDto){
        lessonMapper.addTopicTime(topicTimeDto);
    }

    public void updateTopicTime(TopicTimeDto topicTimeDto){
        lessonMapper.updateTopicTime(topicTimeDto);
    }

    public TopicTimeVo getTopicTime(Integer lessonId){
        return lessonMapper.getTopicTime(lessonId);
    }

    public void createTopic(TopicDto topicDto){
        lessonMapper.createTopic(topicDto);
    }

    public List<TopicListVo> getTopics(Integer lessonId){
        return lessonMapper.getTopics(lessonId);
    }

    public void updateTopic(TopicDto topicDto){
        lessonMapper.updateTopic(topicDto);
    }
    public void deleteTopic(Integer id){
        lessonMapper.deleteTopic(id);
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

    public Boolean verifyNoTopic(Integer lessonId,Integer memberId){
        return lessonMapper.verifyNoTopic(lessonId,memberId)==0;
    }
    public void addTopicMember(TopicMemberDto topicMemberDto){
        lessonMapper.addTopicMember(topicMemberDto);
    }

    public void removeTopicMember(TopicMemberDto topicMemberDto){
        lessonMapper.removeTopicMember(topicMemberDto);
    }


    public List<TeamMemberVo> getTopicMembers(Integer id) {
        return lessonMapper.getTopicMembers(id);
    }

    public List<NoTopicMemberList> getNoTopicMembers(Integer lessonId) {
        return lessonMapper.getNoTopicMembers(lessonId);
    }

    public LessonDto getLessonByTaskId(Integer taskId){
        return lessonMapper.getLessonByTaskId(taskId);
    }
}
