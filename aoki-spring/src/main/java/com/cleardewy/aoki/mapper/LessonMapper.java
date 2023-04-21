package com.cleardewy.aoki.mapper;

import com.cleardewy.aoki.entity.dto.*;
import com.cleardewy.aoki.entity.vo.lesson.LessonListVo;
import com.cleardewy.aoki.entity.vo.lesson.NoTopicMemberList;
import com.cleardewy.aoki.entity.vo.lesson.TeamMemberVo;
import com.cleardewy.aoki.entity.vo.lesson.TopicListVo;
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

    Integer addLessonMember(LessonMemberDto lessonMemberDto);

    Integer verifyLessonMember(Integer lessonId,Integer memberId);

    List<UserListVo> getLessonMember(Integer id);

    Integer deleteLessonMembers(Integer id,List<Integer> idList);

    Integer addTopicTime(TopicTimeDto topicTimeDto);
    Integer updateTopicTime(TopicTimeDto topicTimeDto);
    TopicTimeDto getTopicTime(Integer lessonId);

    Integer createTopic(TopicDto topicDto);

    List<TopicListVo> getTopics(Integer lessonId);

    Integer updateTopic(TopicDto topicDto);
    Integer deleteTopic(Integer id);

    TopicListVo getMyTopic(Integer lessonId, Integer id);

    LessonDto getTopicLesson(Integer id);

    Integer addTopicMember(TopicMemberDto topicMemberDto);

    Integer removeTopicMember(TopicMemberDto topicMemberDto);

    List<TopicListVo> getMyTopics(Integer lessonId, Integer id);

    Integer verifyTopicOwner(Integer topicId, Integer id);

    List<TeamMemberVo> getTopicMembers(Integer id);

    List<NoTopicMemberList> getNoTopicMembers(Integer lessonId);
}
