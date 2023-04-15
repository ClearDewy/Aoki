package com.cleardewy.aoki.entity.vo.lesson;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Data
@AllArgsConstructor
public class LessonVo {
    private Integer id;
    private String name;
    private String introduction;
    private Integer teamMemberLimit;
    private Integer topicMod;
    private String avatarURL;
}
