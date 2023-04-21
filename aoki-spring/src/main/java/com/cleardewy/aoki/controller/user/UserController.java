package com.cleardewy.aoki.controller.user;

import com.cleardewy.aoki.annotation.AokiRole;
import com.cleardewy.aoki.constant.RoleConstans;
import com.cleardewy.aoki.entity.vo.user.UpdateEmailVo;
import com.cleardewy.aoki.entity.vo.user.UpdatePasswordVo;
import com.cleardewy.aoki.service.user.UserService;
import com.cleardewy.aoki.utils.Result;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.cleardewy.aoki.constant.RoleConstans.*;

/**
 * @ Author: ClearDewy
 * @ Description: 用于用户修改信息等公共接口
 **/
@RestController
@RequestMapping("/api")
@AokiRole({ROOT, TEACHER, STUDENT})
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/update-password")
    Result updatePassword(@Valid UpdatePasswordVo updatePasswordVo){
        return userService.updatePassword(updatePasswordVo);
    }

    @PostMapping("/update-email")
    Result updateEmail(@Valid UpdateEmailVo updateEmailVo){
        return userService.updateEmail(updateEmailVo);
    }

    @PostMapping("/update-avatar")
    Result updateAvatar(String avatarURL){
        return userService.updateAvatar(avatarURL);
    }

    @GetMapping("/get-lesson-list")
    Result getLessonList(){
        return userService.getLessonList();
    }

    @PostMapping("/get-lesson")
    Result getLesson(Integer id){
        return userService.getLesson(id);
    }
    @PostMapping("/get-lesson-member")
    Result getLessonMember(Integer id){
        return userService.getLessonMember(id);
    }

    @PostMapping("/get-milestones")
    Result getMilestones(Integer id){
        return userService.getMilestones(id);
    }
    @PostMapping("/create-team")
    Result createTeam(String name,Integer lessonId){
        return userService.createTeam(name,lessonId);
    }

    @PostMapping("/get-teams")
    Result getTeams(Integer lessonId){
        return userService.getTeams(lessonId);
    }
    @PostMapping("/get-no-team-members")
    Result getNoTeamMembers(Integer lessonId){
        return userService.getNoTeamMembers(lessonId);
    }
    @PostMapping("/add-team-member")
    Result addTeamMember(String username,Integer teamId){
        return userService.addTeamMember(username,teamId);
    }
    @PostMapping("/remove-team-member")
    Result removeTeamMember(Integer teamId,String username){
        return userService.removeTeamMember(teamId,username);
    }
    @PostMapping("/get-topic-time")
    Result getTopicTime(Integer lessonId){
        return userService.getTopicTime(lessonId);
    }
    @PostMapping("/get-topics")
    Result getTopics(Integer lessonId){
        return userService.getTopics(lessonId);
    }
    @PostMapping("/get-my-topic")
    Result getMyTopic(Integer lessonId){
        return userService.getMyTopic(lessonId);
    }

    @PostMapping("/add-topic-member")
    Result addTopicMember(Integer topicId){
        return userService.addTopicMember(topicId);
    }
    @PostMapping("/remove-topic-member")
    Result removeTopicMember(Integer topicId){
        return userService.removeTopicMember(topicId);
    }
}
