package com.cleardewy.aoki.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Data
@AllArgsConstructor
public class TopicDto {
    private Integer id;
    private String name;
    private Integer limit;
    private Integer ownerId;
}
