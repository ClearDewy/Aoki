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
public class TaskDto {
    private Integer id;
    private String name;
    private boolean publish;
    private Integer topicId;
    private Timestamp beginTime;
    private Timestamp endTime;
}
