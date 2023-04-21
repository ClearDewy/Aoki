package com.cleardewy.aoki.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Data
@AllArgsConstructor
public class TopicDto {
    private Integer id;
    private String name;
    private Integer number;
    private Integer limit;
    private Double difficult;
    private Integer ownerId;
    private Integer lessonId;
}
