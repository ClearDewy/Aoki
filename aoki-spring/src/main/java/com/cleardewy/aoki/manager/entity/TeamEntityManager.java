package com.cleardewy.aoki.manager.entity;

import com.cleardewy.aoki.entity.dto.TeamDto;
import com.cleardewy.aoki.entity.dto.TeamMemberDto;
import com.cleardewy.aoki.entity.vo.lesson.NoTopicMemberList;
import com.cleardewy.aoki.entity.vo.lesson.TeamMemberVo;
import com.cleardewy.aoki.entity.vo.lesson.TeamVo;
import com.cleardewy.aoki.mapper.TeamMapper;
import com.cleardewy.aoki.utils.ThreadLocalUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.cleardewy.aoki.constant.RoleConstans.ADMIN;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Slf4j
@Component
public class TeamEntityManager {
    @Autowired
    TeamMapper teamMapper;

    @Autowired
    ThreadLocalUtils threadLocalUtils;


    public void createTeam(TeamDto teamDto) {
        teamMapper.createTeam(teamDto);
    }
    public void deleteTeam(Integer id){
        teamMapper.deleteTeam(id);
    }

    public void addTeamMember(TeamMemberDto teamMemberDto) {
        teamMapper.addTeamMember(teamMemberDto);
    }

    public List<TeamVo> getTeams(Integer lessonId){
        return teamMapper.getTeams(lessonId);
    }

    public List<TeamMemberVo>getTeamMembers(Integer teamId){
        return teamMapper.getTeamMembers(teamId);
    }

    public List<TeamVo> getNoTeamMembers(Integer lessonId) {
        return teamMapper.getNoTeamMembers(lessonId);
    }

    public TeamDto getTeam(Integer id){
        return teamMapper.getTeam(id);
    }

    public Integer getTeamId(Integer lessonId,Integer id){
        return teamMapper.getTeamId(lessonId,id);
    }

    public Boolean verifyTeamOwner(Integer id,Integer teamId){
        return threadLocalUtils.getCurrentUser().getRole().equals(ADMIN)||getTeam(teamId).getOwnerId().equals(id);
    }

    public Integer getTeamLessonId(Integer id){
        return teamMapper.getTeamLessonId(id);
    }

    public void removeTeamMember(Integer teamId,Integer memberId){
        teamMapper.removeTeamMember(teamId,memberId);
    }

    public List<TeamVo> getTopicTeams(Integer topicId){
        return teamMapper.getTopicTeams(topicId);
    }

    public List<NoTopicMemberList> getNoTopicTeams(Integer lessonId) {
        return teamMapper.getNoTopicTeams(lessonId);
    }

    public Integer getTeamIdByTaskId(Integer taskId,Integer id){
        return teamMapper.getTeamIdByTaskId(taskId,id);
    }


    public List<TeamVo> getTaskMemberTeam(Integer taskId) {
        return teamMapper.getTaskMemberTeam(taskId);
    }

    public List<TeamVo> getNoTaskMemberTeam(Integer taskId) {
        return teamMapper.getNoTaskMemberTeam(taskId);
    }
}
