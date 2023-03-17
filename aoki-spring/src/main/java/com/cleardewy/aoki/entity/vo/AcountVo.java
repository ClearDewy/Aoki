package com.cleardewy.aoki.entity.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ClassName: AcountVo
 * @Author: ClearDewy
 * @Date: 2023/3/16 14:00
 * @@Description:
 **/
@Data
@AllArgsConstructor
public class AcountVo {
    @NotBlank(message = "用户名不能为空")
    String username;
    @NotBlank(message = "密码不能为空")
    String password;
}
