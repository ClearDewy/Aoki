package com.cleardewy.aoki.service.user;

import com.cleardewy.aoki.entity.vo.lesson.SubmitTaskVo;
import com.cleardewy.aoki.entity.vo.user.UpdateEmailVo;
import com.cleardewy.aoki.entity.vo.user.UpdatePasswordVo;
import com.cleardewy.aoki.utils.Result;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
public interface UserService {

    Result updatePassword(UpdatePasswordVo updatePasswordVo);

    Result updateEmail(UpdateEmailVo updateEmailVo);

    Result updateAvatar(String avatarURL);

    Result getLessonList();

    Result getLesson(Integer id);

    Result getLessonMember(Integer id);

    Result getMilestones(Integer id);

    Result createTeam(String name, Integer lessonId);

    Result getTeams(Integer lessonId);

    Result getNoTeamMembers(Integer lessonId);

    Result addTeamMember(String username, Integer teamId);

    Result removeTeamMember(Integer teamId, String username);

    Result getTopicTime(Integer lessonId);
    Result getTopics(Integer lessonId);

    Result getMyTopic(Integer lessonId);

    Result addTopicMember(Integer topicId);

    Result removeTopicMember(Integer topicId);

    Result getMemberTasks(Integer topicId);


    Result getTaskQuestionAnswer(Integer taskId);

    Result submitTask(SubmitTaskVo submitTaskVo, Boolean submitted);
}
