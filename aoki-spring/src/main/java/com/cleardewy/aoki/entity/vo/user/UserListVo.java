package com.cleardewy.aoki.entity.vo.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Data
@AllArgsConstructor
public class UserListVo {

    private Integer id;
    private String username;
    private String name;
    private String email;
    private String major;
    private Integer role;
}
