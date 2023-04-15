package com.cleardewy.aoki.mapper;

import com.cleardewy.aoki.entity.dto.LessonMemberDto;
import com.cleardewy.aoki.entity.vo.user.UserListVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Mapper
public interface LessonMemberMapper {
    Integer addLessonMember(LessonMemberDto lessonMemberDto);

    Integer verifyLessonMember(Integer lessonId,Integer memberId);

    List<UserListVo> getLessonMember(Integer id);

    Integer deleteLessonMembers(Integer id,List<Integer> idList);
}
