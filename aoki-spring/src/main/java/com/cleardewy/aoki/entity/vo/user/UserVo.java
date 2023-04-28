package com.cleardewy.aoki.entity.vo.user;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @ Author: ClearDewy
 * @ @Description: 前后端交互的用户信息类
 **/
@Data
@AllArgsConstructor
public class UserVo {

    private Integer id;
    private String username;
    private String password;
    private String name;
    private String email;
    private String major;
    private Integer role;

    private String avatarURL;
}
