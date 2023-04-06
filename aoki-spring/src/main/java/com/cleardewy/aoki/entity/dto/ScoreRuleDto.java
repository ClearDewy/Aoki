package com.cleardewy.aoki.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Data
@AllArgsConstructor
public class ScoreRuleDto {
    private Integer id;
    private String name;
    private String content;
    private Integer taskId;
    private Double limit;
}
