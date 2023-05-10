package com.cleardewy.aoki.mapper;

import com.cleardewy.aoki.entity.dto.*;
import com.cleardewy.aoki.entity.vo.lesson.*;
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
    Integer updateLesson(EditLessonVo editLessonVo);
    Integer deleteLesson(Integer id);

    List<LessonListVo> getLessonList(Integer id);

    LessonDto getLesson(Integer id);

    LessonDto getLessonByTopic(Integer topicId);

    Integer verifyLessonOwner(Integer ownerId,Integer id);

    Integer addLessonMember(LessonMemberDto lessonMemberDto);
    Integer addLessonMembers(Integer lessonId,List<Integer>idList);

    Integer verifyLessonMember(Integer lessonId,Integer memberId);

    List<UserListVo> getLessonMember(Integer id);

    Integer deleteLessonMembers(Integer lessonId,Integer id);

    Integer addTopicTime(TopicTimeDto topicTimeDto);
    Integer updateTopicTime(TopicTimeDto topicTimeDto);
    TopicTimeVo getTopicTime(Integer lessonId);

    Integer createTopic(TopicDto topicDto);

    List<TopicListVo> getTopics(Integer lessonId);

    Integer updateTopic(TopicDto topicDto);
    Integer deleteTopic(Integer id);

    TopicListVo getMyTopic(Integer lessonId, Integer id);

    LessonDto getTopicLesson(Integer id);

    Integer verifyNoTopic(Integer lessonId,Integer memberId);

    Integer addTopicMember(TopicMemberDto topicMemberDto);

    Integer removeTopicMember(TopicMemberDto topicMemberDto);

    List<TopicListVo> getMyTopics(Integer lessonId, Integer id);

    Integer verifyTopicOwner(Integer topicId, Integer id);

    List<TeamMemberVo> getTopicMembers(Integer id);

    List<NoTopicMemberList> getNoTopicMembers(Integer lessonId);


    LessonDto getLessonByTaskId(Integer taskId);

}
