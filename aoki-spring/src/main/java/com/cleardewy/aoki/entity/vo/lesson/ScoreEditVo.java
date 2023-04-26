package com.cleardewy.aoki.entity.vo.lesson;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Data
@AllArgsConstructor
public class ScoreEditVo {
    public Integer scoreRuleId;
    private String name;
    private Double limit;
    public Double score;
}
