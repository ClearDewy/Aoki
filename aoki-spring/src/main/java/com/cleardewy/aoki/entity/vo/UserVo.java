package com.cleardewy.aoki.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ Author: ClearDewy
 * @ @Description:
 **/
@Data
@AllArgsConstructor
public class UserVo {
    private String username;
    private String name;
    private String number;
    private String email;
    private String major;
    private String role;
}
