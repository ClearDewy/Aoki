package com.cleardewy.aoki.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Data
@AllArgsConstructor
public class AnswerDto {
    private Integer id;
    private Integer questionId;
    private String content;
    private Integer ownerId;
}
