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
    Integer id;
    String name;
    String ownerName;
    List<TeamMemberVo> memberList;

    TeamVo(Integer id,String name,String ownerName){
        this.id=id;this.name=name;this.ownerName=ownerName;
    }
}
