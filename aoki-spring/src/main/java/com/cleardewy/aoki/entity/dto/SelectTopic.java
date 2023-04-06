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
public class SelectTopic {
    private Integer id;
    private Integer lessonId;
    private Timestamp beginTime;
    private Timestamp endTime;
}
