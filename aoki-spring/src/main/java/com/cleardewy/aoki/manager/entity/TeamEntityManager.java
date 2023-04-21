package com.cleardewy.aoki.manager.entity;

import com.cleardewy.aoki.entity.dto.TeamDto;
import com.cleardewy.aoki.entity.dto.TeamMemberDto;
import com.cleardewy.aoki.entity.dto.TopicTimeDto;
import com.cleardewy.aoki.entity.vo.lesson.NoTopicMemberList;
import com.cleardewy.aoki.entity.vo.lesson.TeamMemberVo;
import com.cleardewy.aoki.entity.vo.lesson.TeamVo;
import com.cleardewy.aoki.exception.AokiException;
import com.cleardewy.aoki.mapper.TeamMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Slf4j
@Component
public class TeamEntityManager {
    @Autowired
    TeamMapper teamMapper;


    public void createTeam(TeamDto teamDto) {
        if (teamMapper.createTeam(teamDto)==0)
            throw AokiException.notFound();
    }

    public void addTeamMember(TeamMemberDto teamMemberDto) {
        if (teamMapper.addTeamMember(teamMemberDto)==0)
            throw AokiException.notFound();
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
        TeamDto teamDto=teamMapper.getTeam(id);
        if (teamDto==null)
            throw AokiException.notFound();
        return teamDto;
    }

    public Integer getTeamId(Integer lessonId,Integer id){
        return teamMapper.getTeamId(lessonId,id);
    }

    public void verifyTeamOwner(Integer id,Integer teamId){
        if (!getTeam(teamId).getOwnerId().equals(id))
            throw AokiException.forbidden();
    }

    public Integer getTeamLessonId(Integer id){
        Integer lessonId=teamMapper.getTeamLessonId(id);
        if (lessonId==null)
            throw AokiException.notFound();
        return lessonId;
    }

    public void removeTeamMember(Integer teamId,Integer memberId){
        if (teamMapper.removeTeamMember(teamId,memberId)==0)
            throw AokiException.notFound();
    }

    public List<TeamVo> getTopicTeams(Integer topicId){
        return teamMapper.getTopicTeams(topicId);
    }

    public List<NoTopicMemberList> getNoTopicTeams(Integer lessonId) {
        return teamMapper.getNoTopicTeams(lessonId);
    }
}