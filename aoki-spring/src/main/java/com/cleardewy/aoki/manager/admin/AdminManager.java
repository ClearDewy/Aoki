package com.cleardewy.aoki.manager.admin;

import com.cleardewy.aoki.entity.dto.TopicMemberDto;
import com.cleardewy.aoki.entity.dto.UserDto;
import com.cleardewy.aoki.entity.vo.lesson.LessonListVo;
import com.cleardewy.aoki.entity.vo.lesson.TopicListVo;
import com.cleardewy.aoki.entity.vo.user.UserVo;
import com.cleardewy.aoki.exception.AokiException;
import com.cleardewy.aoki.manager.entity.LessonEntityManager;
import com.cleardewy.aoki.manager.entity.TeamEntityManager;
import com.cleardewy.aoki.manager.entity.UserEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Component
public class AdminManager {
    @Autowired
    private UserEntityManager userEntityManager;
    @Autowired
    private LessonEntityManager lessonEntityManager;
    @Autowired
    private TeamEntityManager teamEntityManager;
    public List<UserVo> getAllUser() {
        return userEntityManager.getAllUser();
    }

    public UserVo addUser(UserVo userVo) {
        UserDto userDto = new UserDto(
                null,
                userVo.getUsername()
                , userVo.getPassword(),
                userVo.getName(),
                userVo.getEmail(),
                userEntityManager.getMajorIdByMajor(userVo.getMajor()),
                userVo.getRole()
                , userVo.getAvatarURL());
        userEntityManager.addUser(userDto);
        userVo.setId(userDto.getId());
        return userVo;
    }

    public void updateUser(UserVo userVo) {
        userEntityManager.updateUserById(new UserDto(
                userVo.getId(),
                userVo.getUsername()
                , userVo.getPassword(),
                userVo.getName(),
                userVo.getEmail(),
                userEntityManager.getMajorIdByMajor(userVo.getMajor()),
                userVo.getRole()
                , userVo.getAvatarURL()));
    }

    public void deleteUser(Integer id) {

        List<LessonListVo> lessonList = lessonEntityManager.getLessonList(id);

        lessonList.forEach(lesson->{
            lessonEntityManager.deleteLessonMembers(lesson.getId(), id);
            TopicListVo myTopic = lessonEntityManager.getMyTopic(lesson.getId(), id);
            if (myTopic!=null)
                lessonEntityManager.removeTopicMember(new TopicMemberDto(null, myTopic.getId(), id));
            Integer teamId = teamEntityManager.getTeamId(lesson.getId(), id);
            if (teamId!=null)
                teamEntityManager.removeTeamMember(teamId,id);
        });

        userEntityManager.deleteUserById(id);
    }

    public void addMajor(String major) {
        try{
            userEntityManager.addMajor(major);
        }catch (Exception e){
            throw AokiException.dataExited();
        }
    }

    public void deleteMajor(String major) {
        userEntityManager.deleteMajorByName(major);
    }
}
