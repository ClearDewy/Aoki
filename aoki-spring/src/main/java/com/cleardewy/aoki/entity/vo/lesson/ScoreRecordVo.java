package com.cleardewy.aoki.entity.vo.lesson;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Data
@AllArgsConstructor
public class ScoreRecordVo {
    private Integer taskId;
    private Integer memberId;
    List<Score> scoreList;
    @Data
    @AllArgsConstructor
    public static class Score{
        public Integer scoreRuleId;
        public Double score;
    }
}
