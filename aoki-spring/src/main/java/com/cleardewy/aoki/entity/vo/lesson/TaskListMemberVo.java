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
public class TaskListMemberVo {
    private Integer id;
    private String name;
    private String beginTime;
    private String endTime;
    private Boolean submitted;
}
