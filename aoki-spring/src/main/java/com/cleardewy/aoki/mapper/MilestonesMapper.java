package com.cleardewy.aoki.mapper;

import com.cleardewy.aoki.entity.dto.MilestonesDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Mapper
public interface MilestonesMapper {
    Integer addMilestones(MilestonesDto milestonesDto);

    List<MilestonesDto> gerMilestones(Integer id);

    Integer deleteMilestones(Integer lessonId,List<Integer> idList);

    Integer updateMilestones(MilestonesDto milestonesDto);
}
