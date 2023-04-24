package com.cleardewy.aoki.service.user.impl;

import com.cleardewy.aoki.entity.vo.user.UpdateEmailVo;
import com.cleardewy.aoki.entity.vo.user.UpdatePasswordVo;
import com.cleardewy.aoki.manager.user.UserManager;
import com.cleardewy.aoki.service.user.UserService;
import com.cleardewy.aoki.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserManager userManager;

    @Override
    public Result updatePassword(UpdatePasswordVo updatePasswordVo) {
        userManager.updatePassword(updatePasswordVo);
        return Result.success();
    }

    @Override
    public Result updateEmail(UpdateEmailVo updateEmailVo) {
        userManager.updateEmail(updateEmailVo);
        return Result.success();
    }

    @Override
    public Result updateAvatar(String avatarURL) {
        userManager.updateAvatar(avatarURL);
        return Result.success();
    }

    @Override
    public Result getLessonList() {
        return Result.success(userManager.getLessonList());
    }

    @Override
    public Result getLesson(Integer id) {
        return Result.success(userManager.getLesson(id));
    }

    @Override
    public Result getLessonMember(Integer id) {
        return Result.success(userManager.getLessonMember(id));
    }

    @Override
    public Result getMilestones(Integer id) {
        return Result.success(userManager.getMilestones(id));
    }

    @Override
    public Result createTeam(String name, Integer lessonId) {
        userManager.createTeam(name,lessonId);
        return Result.success();
    }

    @Override
    public Result getTeams(Integer lessonId) {
        return Result.success(userManager.getTeams(lessonId));
    }

    @Override
    public Result getNoTeamMembers(Integer lessonId) {
        return Result.success(userManager.getNoTeamMembers(lessonId));
    }

    @Override
    public Result addTeamMember(String username, Integer teamId) {
        userManager.addTeamMember(teamId,username);
        return Result.success();
    }

    @Override
    public Result removeTeamMember(Integer teamId, String username) {
        userManager.removeTeamMember(teamId,username);
        return Result.success();
    }

    @Override
    public Result getTopicTime(Integer lessonId) {
        return Result.success(userManager.getTopicTime(lessonId));
    }
    @Override
    public Result getTopics(Integer lessonId) {
        return Result.success(userManager.getTopics(lessonId));
    }

    @Override
    public Result getMyTopic(Integer lessonId) {
        return Result.success(userManager.getMyTopic(lessonId));
    }

    @Override
    public Result addTopicMember(Integer topicId) {
        userManager.addTopicMember(topicId);
        return Result.success();
    }
    @Override
    public Result removeTopicMember(Integer topicId) {
        userManager.removeTopicMember(topicId);
        return Result.success();
    }

    @Override
    public Result getMemberTasks(Integer topicId) {
        return Result.success(userManager.getMemberTasks(topicId));
    }


}
