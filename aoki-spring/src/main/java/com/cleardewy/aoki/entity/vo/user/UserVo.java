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

    @NotBlank(message = "学工号不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    @Length(min = 6,max = 31,message = "密码最少6位，最多31位")
    private String password;
    @NotBlank(message = "姓名不能为空")
    private String name;
    @Email(message = "邮箱不能为空")
    private String email;

    private String major;
    @NotNull(message = "角色不能为空")
    private Integer role;

    private String avatarURL;
}
