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
    private Integer topicId;
    private Integer ownerId;
    private Timestamp beginTime;
    private Timestamp endTime;
}
