package com.cleardewy.aoki.entity.vo.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @ Author: ClearDewy
 * @ Description: 修改邮箱的视图类
 **/
@Data
@AllArgsConstructor
public class UpdateEmailVo {
    @NotBlank(message = "密码不能为空")
    @Length(min = 6,max = 20,message = "密码最少6位，最多20位")
    String password;
    @Email
    String email;
    @Length(min = 6,max = 6,message = "验证码不为6位")
    String code;
}
