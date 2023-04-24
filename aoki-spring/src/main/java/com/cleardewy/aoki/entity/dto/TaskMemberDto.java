package com.cleardewy.aoki.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Data
@AllArgsConstructor
public class TaskMemberDto {
    Integer id;
    Integer taskId;
    Integer memberId;
    Boolean saved;
    Boolean submitted;
}
