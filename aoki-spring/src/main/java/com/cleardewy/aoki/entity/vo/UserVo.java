package com.cleardewy.aoki.entity.vo;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
/**
 * @ Author: ClearDewy
 * @ @Description: 前后端交互的用户信息类
 **/
@Data
@AllArgsConstructor
public class UserVo {

    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
    @NotBlank(message = "姓名不能为空")
    private String name;
    @NotBlank(message = "学工号不能为空")
    private String number;
    @Email(message = "邮箱不能为空")
    private String email;

    private String major;
    @NotBlank(message = "角色不能为空")
    private String role;
}
