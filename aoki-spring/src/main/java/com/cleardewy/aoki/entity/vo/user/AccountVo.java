package com.cleardewy.aoki.entity.vo.user;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * @ Author: ClearDewy
 * @ @Description:
 **/
@Data
@AllArgsConstructor
public class AccountVo {

    @NotBlank(message = "学工号不能为空")
    String username;


    @NotBlank(message = "密码不能为空")
    String password;
}
