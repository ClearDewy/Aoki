package com.cleardewy.aoki.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Data
@AllArgsConstructor
public class ScoreRecordDto {
    private Integer id;
    private Integer scoreRuleId;
    private Integer answerId;
    private Double score;
}
