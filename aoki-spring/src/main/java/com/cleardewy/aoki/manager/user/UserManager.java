package com.cleardewy.aoki.manager.user;

import com.cleardewy.aoki.constant.ResultStatus;
import com.cleardewy.aoki.entity.dto.*;
import com.cleardewy.aoki.entity.vo.lesson.LessonListVo;
import com.cleardewy.aoki.entity.vo.lesson.TeamVo;
import com.cleardewy.aoki.entity.vo.lesson.TopicTimeVo;
import com.cleardewy.aoki.entity.vo.user.*;
import com.cleardewy.aoki.exception.AokiException;
import com.cleardewy.aoki.manager.account.EmailVerifyManager;
import com.cleardewy.aoki.manager.entity.LessonEntityManager;
import com.cleardewy.aoki.manager.entity.MilestonesEntityManager;
import com.cleardewy.aoki.manager.entity.TeamEntityManager;
import com.cleardewy.aoki.manager.entity.UserEntityManager;
import com.cleardewy.aoki.manager.file.FileManager;
import com.cleardewy.aoki.utils.RedisUtils;
import com.cleardewy.aoki.utils.ThreadLocalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Component
public class UserManager {
    @Autowired
    UserEntityManager userEntityManager;

    @Autowired
    ThreadLocalUtils threadLocalUtils;

    @Autowired
    EmailVerifyManager emailVerifyManager;

    @Autowired
    LessonEntityManager lessonEntityManager;
    @Autowired
    TeamEntityManager teamEntityManager;

    @Autowired
    FileManager fileManager;
    @Autowired
    RedisUtils redisUtils;
    @Autowired
    MilestonesEntityManager milestonesEntityManager;


    public UserVo userDtoToUserVo(UserDto userDto){
        return new UserVo(userDto.getUsername(),"",userDto.getName(),userDto.getEmail(),userEntityManager.getMajorByMajorId(userDto.getMajorId()),userDto.getRole(),userDto.getAvatarURL());
    }

    public UserDto userVoToUserDto(UserVo userVo){
        return new UserDto(null,userVo.getUsername(),userVo.getPassword(),userVo.getName(),userVo.getEmail(),userEntityManager.getMajorIdByMajor(userVo.getMajor()),userVo.getRole(),userVo.getAvatarURL());
    }

    public void updatePassword(UpdatePasswordVo updatePasswordVo) {
        UserDto userDto=threadLocalUtils.getCurrentUser();
        if (!userDto.getPassword().equals(updatePasswordVo.getOldPassword()))
            throw new AokiException(ResultStatus.Status.FAIL, ResultStatus.Message.OLD_PASSWORD_WRONG);
        userDto.setPassword(updatePasswordVo.getNewPassword());
        userEntityManager.updateUserById(userDto);
    }

    public void updateEmail(UpdateEmailVo updateEmailVo) {
        UserDto userDto=threadLocalUtils.getCurrentUser();
        if (!userDto.getPassword().equals(updateEmailVo.getPassword()))
            throw new AokiException(ResultStatus.Status.FAIL, ResultStatus.Message.OLD_PASSWORD_WRONG);
        emailVerifyManager.verifyCode(new EmailVerifyVo(updateEmailVo.getEmail(), updateEmailVo.getCode()));
        redisUtils.del("email_verify_code:"+updateEmailVo.getEmail());
        userDto.setEmail(updateEmailVo.getEmail());
        userEntityManager.updateUserById(userDto);
    }

    public void updateAvatar(String avatarURL){
        UserDto user=threadLocalUtils.getCurrentUser();
        user.setAvatarURL(avatarURL);
        userEntityManager.updateUserById(user);
    }

    public List<LessonListVo> getLessonList() {
        return lessonEntityManager.getLessonList(threadLocalUtils.getCurrentUser().getId());
    }

    public LessonDto getLesson(Integer id) {
        if (!lessonEntityManager.verifyLessonMember(id,threadLocalUtils.getCurrentUser().getId())){
            throw AokiException.forbidden();
        }
        return lessonEntityManager.getLesson(id);
    }

    public List<UserListVo> getLessonMember(Integer id) {
        if (!lessonEntityManager.verifyLessonMember(id,threadLocalUtils.getCurrentUser().getId())){
            throw AokiException.forbidden();
        }
        return lessonEntityManager.getLessonMember(id);
    }

    public List<MilestonesDto> getMilestones(Integer id) {
        if (!lessonEntityManager.verifyLessonMember(id,threadLocalUtils.getCurrentUser().getId())){
            throw AokiException.forbidden();
        }
        return milestonesEntityManager.gerMilestones(id);
    }

    public void createTeam(String name, Integer lessonId) {
        Integer id=threadLocalUtils.getCurrentUser().getId();
//        lessonEntityManager.verifyLessonMember(lessonId,id);
        if (teamEntityManager.getTeamId(lessonId,id)!=null){
            throw AokiException.fail(ResultStatus.Message.HAVE_TEAM);
        }
        TeamDto teamDto=new TeamDto(null,name,lessonId,id);
        teamEntityManager.createTeam(teamDto);
        teamEntityManager.addTeamMember(new TeamMemberDto(null,teamDto.getId(),id));
    }

    public List<TeamVo> getTeams(Integer lessonId) {
        if (!lessonEntityManager.verifyLessonMember(lessonId,threadLocalUtils.getCurrentUser().getId())){
            throw AokiException.forbidden();
        }
        List<TeamVo> teamList=teamEntityManager.getTeams(lessonId);
        teamList.forEach((team -> {
            team.setMemberList(teamEntityManager.getTeamMembers(team.getId()));
        }));
        return teamList;
    }

    public List<TeamVo> getNoTeamMembers(Integer lessonId) {
        return teamEntityManager.getNoTeamMembers(lessonId);
    }

    public void addTeamMember(Integer id,String username) {
        teamEntityManager.verifyTeamOwner(threadLocalUtils.getCurrentUser().getId(),id);
        Integer memberId=userEntityManager.getUserByUsername(username).getId();
        if (!lessonEntityManager.verifyLessonMember(teamEntityManager.getTeamLessonId(id),memberId)){
            throw AokiException.forbidden();
        }
        teamEntityManager.addTeamMember(new TeamMemberDto(null,id,memberId));
    }

    public void removeTeamMember(Integer teamId, String username) {
        teamEntityManager.verifyTeamOwner(threadLocalUtils.getCurrentUser().getId(),teamId);
        Integer memberId=userEntityManager.getUserByUsername(username).getId();
        teamEntityManager.removeTeamMember(teamId,memberId);
    }

    public TopicTimeVo getTopicTime(Integer id) {
        TopicTimeDto topicTimeDto=lessonEntityManager.getTopicTime(id);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return new TopicTimeVo(
                topicTimeDto.getBeginTime()==null?"":sdf.format(topicTimeDto.getBeginTime()),
                topicTimeDto.getEndTime()==null?"":sdf.format(topicTimeDto.getEndTime()),
                topicTimeDto.getLessonId()
        );
    }

    public List<TopicDto> getTopics(Integer lessonId) {
        return lessonEntityManager.getTopics(lessonId);
    }
}
