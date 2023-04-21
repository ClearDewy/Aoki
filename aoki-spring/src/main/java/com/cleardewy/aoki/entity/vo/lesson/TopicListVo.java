package com.cleardewy.aoki.entity.vo.lesson;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Data
@AllArgsConstructor
public class TopicListVo {
    private Integer id;
    private String name;
    private String number;
    private Integer limit;
    private Double difficult;
    private String ownerName;
}
