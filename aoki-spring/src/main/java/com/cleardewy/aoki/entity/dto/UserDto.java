package com.cleardewy.aoki.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ Author: ClearDewy
 * @ @Description:
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String email;
    private Integer majorId;
    private Integer role;
    private String avatarURL;
}
