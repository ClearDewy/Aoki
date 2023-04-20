package com.cleardewy.aoki.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Data
@AllArgsConstructor
public class TopicTimeDto {
    private Integer id;
    private Timestamp beginTime;
    private Timestamp endTime;
    private Integer lessonId;
}
