package com.cleardewy.aoki.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Data
@AllArgsConstructor
public class LessonMemberDto {
    private Integer id;
    private Integer memberId;
    private Integer lessonId;
}
