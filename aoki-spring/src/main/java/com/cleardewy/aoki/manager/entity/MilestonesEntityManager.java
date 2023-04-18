package com.cleardewy.aoki.manager.entity;

import com.cleardewy.aoki.entity.dto.MilestonesDto;
import com.cleardewy.aoki.exception.AokiException;
import com.cleardewy.aoki.mapper.MilestonesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Component
public class MilestonesEntityManager {
    @Autowired
    MilestonesMapper milestonesMapper;

    public void addMilestones(MilestonesDto milestonesDto){
        if (milestonesMapper.addMilestones(milestonesDto)==0)
            throw AokiException.fail();
    }

    public List<MilestonesDto> gerMilestones(Integer id){
        return milestonesMapper.gerMilestones(id);
    }

    public void deleteMilestones(Integer lessonId, List<Integer> idList){
        milestonesMapper.deleteMilestones(lessonId,idList);
    }

    public void updateMilestones(MilestonesDto milestonesDto){
        if (milestonesMapper.updateMilestones(milestonesDto)==0)
            throw AokiException.fail();
    }
}
