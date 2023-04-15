package com.cleardewy.aoki.manager.entity;

import com.cleardewy.aoki.constant.ResultStatus;
import com.cleardewy.aoki.entity.dto.LessonMemberDto;
import com.cleardewy.aoki.entity.vo.user.UserListVo;
import com.cleardewy.aoki.exception.AokiException;
import com.cleardewy.aoki.mapper.LessonMemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Component
public class LessonMemberEntityManager {
    @Autowired
    LessonMemberMapper lessonMemberMapper;
    public void addLessonMember(LessonMemberDto lessonMemberDto){
        if (lessonMemberMapper.addLessonMember(lessonMemberDto)==0){
            throw new AokiException(ResultStatus.Status.FAIL);
        }
    }

    public void verifyLessonMember(Integer lessonId,Integer memberId){
        if (lessonMemberMapper.verifyLessonMember(lessonId,memberId)==0){
            throw AokiException.forbidden();
        }
    }
    public List<UserListVo> getLessonMember(Integer id) {
        return lessonMemberMapper.getLessonMember(id);
    }
}
