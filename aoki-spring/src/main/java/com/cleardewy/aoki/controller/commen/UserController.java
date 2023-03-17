package com.cleardewy.aoki.controller.commen;

import com.cleardewy.aoki.annotation.AokiRole;
import com.cleardewy.aoki.entity.vo.AccountVo;
import com.cleardewy.aoki.service.user.UserService;
import com.cleardewy.aoki.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping("/login")
    Result login(AccountVo accountVo){
        return userService.login(accountVo);
    }
    @RequestMapping("/logout")
    Result logout(){
        return userService.logout();
    }
    @AokiRole({"teacher"})
    @RequestMapping("/teacher")
    Result teacher(){
        return Result.success("i am teacher");
    }

    @AokiRole({"root"})
    @RequestMapping("/root")
    Result root(){
        return Result.success("i am root");
    }
}
