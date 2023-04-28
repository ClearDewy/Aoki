package com.cleardewy.aoki.entity.vo.lesson;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Data
@AllArgsConstructor
public class TeamVo {
    private Integer id;
    private String name;
    private Integer ownerId;
    private String ownerName;
    private List<TeamMemberVo> memberList;

    TeamVo(Integer id,String name,Integer ownerId,String ownerName){
        this.id=id;this.name=name;this.ownerId=ownerId;this.ownerName=ownerName;
    }
}
