package com.cleardewy.aoki.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: UserDto
 * @Author: ClearDewy
 * @Date: 2023/3/16 13:47
 * @@Description:
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String number;
    private String email;
    private Integer majorId;
    private Integer roleId;
}
