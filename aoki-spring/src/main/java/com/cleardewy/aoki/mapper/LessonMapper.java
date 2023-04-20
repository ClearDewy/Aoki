package com.cleardewy.aoki.mapper;

import com.cleardewy.aoki.entity.dto.LessonDto;
import com.cleardewy.aoki.entity.dto.LessonMemberDto;
import com.cleardewy.aoki.entity.dto.TopicDto;
import com.cleardewy.aoki.entity.dto.TopicTimeDto;
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

    Integer addLessonMember(LessonMemberDto lessonMemberDto);

    Integer verifyLessonMember(Integer lessonId,Integer memberId);

    List<UserListVo> getLessonMember(Integer id);

    Integer deleteLessonMembers(Integer id,List<Integer> idList);

    Integer addTopicTime(TopicTimeDto topicTimeDto);
    Integer updateTopicTime(TopicTimeDto topicTimeDto);
    TopicTimeDto getTopicTime(Integer id);

    Integer createTopic(TopicDto topicDto);

    List<TopicDto> getTopics(Integer lessonId);

    Integer updateTopic(TopicDto topicDto);
    Integer deleteTopic(Integer id);
}
