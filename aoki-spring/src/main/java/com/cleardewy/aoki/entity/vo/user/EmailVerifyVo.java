package com.cleardewy.aoki.entity.vo.user;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Data
@AllArgsConstructor
public class EmailVerifyVo {
    @Email(message = "请输入正确的邮箱")
    private String email;

    @Length(min = 6,max = 6,message = "验证码不为6位")
    private String code;
}
