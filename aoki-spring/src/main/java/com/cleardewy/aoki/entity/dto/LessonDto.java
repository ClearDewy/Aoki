package com.cleardewy.aoki.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LessonDto {
    private Integer id;
    private String name;
    private String introduction;
    private Integer ownerId;
    private Integer teamMemberLimit;
    private boolean topicMod;
    private String avatarURL;
}
