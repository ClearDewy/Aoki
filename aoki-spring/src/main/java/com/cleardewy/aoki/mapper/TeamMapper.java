package com.cleardewy.aoki.mapper;

import com.cleardewy.aoki.entity.dto.TeamDto;
import com.cleardewy.aoki.entity.dto.TeamMemberDto;
import com.cleardewy.aoki.entity.vo.lesson.NoTopicMemberList;
import com.cleardewy.aoki.entity.vo.lesson.TeamMemberVo;
import com.cleardewy.aoki.entity.vo.lesson.TeamVo;
import com.cleardewy.aoki.entity.vo.lesson.TopicListVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Mapper
public interface TeamMapper {

    Integer createTeam(TeamDto teamDto);

    Integer addTeamMember(TeamMemberDto teamMemberDto);

    List<TeamVo>getTeams(Integer lessonId);

    List<TeamMemberVo>getTeamMembers(Integer teamId);

    List<TeamVo> getNoTeamMembers(Integer lessonId);

    TeamDto getTeam(Integer id);

    Integer getTeamId(Integer lessonId,Integer id);

    Integer getTeamLessonId(Integer id);

    Integer removeTeamMember(Integer teamId,Integer memberId);

    List<TeamVo> getTopicTeams(Integer topicId);

    List<NoTopicMemberList> getNoTopicTeams(Integer lessonId);

    Integer getTeamIdByTaskId(Integer taskId,Integer id);
}
