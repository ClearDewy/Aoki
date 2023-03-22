package com.cleardewy.aoki.controller.commen;

import com.cleardewy.aoki.annotation.AokiRole;
import com.cleardewy.aoki.entity.vo.AccountVo;
import com.cleardewy.aoki.entity.vo.UserVo;
import com.cleardewy.aoki.service.user.UserService;
import com.cleardewy.aoki.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @ Author: ClearDewy
 * @ Description: 普通用户接口，实现注册登录等
 **/
@RestController
@RequestMapping("/api")
@Slf4j
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    Result login(@Valid AccountVo accountVo){
        return userService.login(accountVo);
    }

    @GetMapping("/logout")
    Result logout(){
        return userService.logout();
    }


    @PostMapping("/register")
    Result register(@Valid UserVo userVo){
        return userService.register(userVo);
    }
}
