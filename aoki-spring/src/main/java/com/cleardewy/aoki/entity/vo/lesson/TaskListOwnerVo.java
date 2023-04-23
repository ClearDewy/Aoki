package com.cleardewy.aoki.entity.vo.lesson;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Data
@AllArgsConstructor
public class TaskListOwnerVo {
    private Integer id;
    private String name;
    private boolean publish;
    private Integer topicId;
    private String topicName;
    private String beginTime;
    private String endTime;
}
