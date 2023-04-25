package com.cleardewy.aoki.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Data
@AllArgsConstructor
public class TaskSubmittedDto {
    private Integer id;
    private Integer taskId;
    private Integer memberId;
    private Boolean submitted;
}
