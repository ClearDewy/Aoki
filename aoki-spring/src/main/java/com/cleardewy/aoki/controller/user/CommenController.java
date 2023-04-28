package com.cleardewy.aoki.controller.user;

import com.cleardewy.aoki.entity.vo.user.AccountVo;
import com.cleardewy.aoki.entity.vo.user.EmailVerifyVo;
import com.cleardewy.aoki.entity.vo.user.UserRegisterVo;
import com.cleardewy.aoki.entity.vo.user.UserVo;
import com.cleardewy.aoki.service.user.CommenService;
import com.cleardewy.aoki.utils.Result;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ Author: ClearDewy
 * @ Description: 普通用户接口，实现注册登录等
 **/
@RestController
@RequestMapping("/api")
@Slf4j
public class CommenController {
    @Autowired
    CommenService commenService;

    @PostMapping("/login")
    Result login(@Valid AccountVo accountVo){
        return commenService.login(accountVo);
    }

    @GetMapping("/logout")
    Result logout(){
        return commenService.logout();
    }


    @PostMapping("/register")
    Result register(@Valid UserRegisterVo userRegisterVo, String code){
        return commenService.register(userRegisterVo,code);
    }

    @PostMapping("/get-verify-code")
    Result getVerifyCode(@Valid EmailVerifyVo emailVerifyVo){
        return commenService.getVerifyCode(emailVerifyVo);
    }

    @PostMapping("/verify-code")
    Result verifyCode(@Valid EmailVerifyVo emailVerifyVo){
        return commenService.verifyCode(emailVerifyVo);
    }

    @GetMapping("/get-all-major")
    Result getAllMajor(){
        return commenService.getAllMajor();
    }

    @PostMapping("/email-login")
    Result emailLogin(@Valid EmailVerifyVo emailVerifyVo){
        return commenService.emailLogin(emailVerifyVo);
    }

}
