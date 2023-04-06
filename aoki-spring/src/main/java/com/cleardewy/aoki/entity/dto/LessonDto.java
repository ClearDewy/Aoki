package com.cleardewy.aoki.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Data
@AllArgsConstructor
public class LessonDto {
    private Integer id;
    private String name;
    private String introduction;
    private Integer ownerId;
    private boolean isTeamWork;
    private Integer topicMod;
}
