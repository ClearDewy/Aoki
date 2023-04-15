package com.cleardewy.aoki.entity.vo.lesson;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Data
@AllArgsConstructor
public class LessonListVo {
    Integer id;
    String name;
    String ownerName;
    String avatarURL;
}
