package com.cleardewy.aoki.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Data
@AllArgsConstructor
public class TopicMemberDto {
    private Integer id;
    private Integer topicId;
    private Integer memberId;
}
