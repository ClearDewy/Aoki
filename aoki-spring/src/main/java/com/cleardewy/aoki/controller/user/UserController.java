package com.cleardewy.aoki.controller.user;

import com.cleardewy.aoki.annotation.AokiRole;
import com.cleardewy.aoki.constant.RoleConstans;
import com.cleardewy.aoki.entity.vo.user.UpdateEmailVo;
import com.cleardewy.aoki.entity.vo.user.UpdatePasswordVo;
import com.cleardewy.aoki.service.user.UserService;
import com.cleardewy.aoki.utils.Result;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.cleardewy.aoki.constant.RoleConstans.*;

/**
 * @ Author: ClearDewy
 * @ Description: 用于用户修改信息等公共接口
 **/
@RestController
@RequestMapping("/api")
@AokiRole({ROOT, TEACHER, STUDENT})
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/update-password")
    Result updatePassword(@Valid UpdatePasswordVo updatePasswordVo){
        return userService.updatePassword(updatePasswordVo);
    }

    @PostMapping("/update-email")
    Result updateEmail(@Valid UpdateEmailVo updateEmailVo){
        return userService.updateEmail(updateEmailVo);
    }


}
