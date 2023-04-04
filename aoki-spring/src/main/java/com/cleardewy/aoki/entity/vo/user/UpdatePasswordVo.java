package com.cleardewy.aoki.entity.vo.user;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @ Author: ClearDewy
 * @ Description: 更改密码的视图层类
 **/
@Data
@AllArgsConstructor
public class UpdatePasswordVo {
    @NotBlank(message = "当前密码不能为空")
    @Length(min = 6,max = 20,message = "当前密码最少6位，最多20位")
    String oldPassword;

    @NotBlank(message = "新密码不能为空")
    @Length(min = 6,max = 20,message = "新密码最少6位，最多20位")
    String newPassword;
}
