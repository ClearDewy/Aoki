package com.cleardewy.aoki.entity.vo.lesson;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Data
@AllArgsConstructor
public class EditTopicVo {
    private Integer id;
    private String name;
    private Integer limit;
    private Double difficult;
    private Integer lessonId;
}
