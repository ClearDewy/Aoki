package com.cleardewy.aoki.entity.vo.lesson;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @ Author: ClearDewy
 * @ Description: 用户提交作业
 **/
@Data
@AllArgsConstructor
public class SubmitTaskVo {
    private Integer taskId;
    private List<SubmitAnswerVo> answerList;

    @Data
    @AllArgsConstructor
    public static class SubmitAnswerVo{
        public Integer questionId;
        public String answerContent;
    }

}
