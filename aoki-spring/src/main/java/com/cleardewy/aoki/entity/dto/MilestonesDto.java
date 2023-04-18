package com.cleardewy.aoki.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Data
@AllArgsConstructor
public class MilestonesDto {
    private Integer id;
    private String name;
    private Date beginTime;
    private Date endTime;
    private String introduction;
    private Integer lessonId;
}
