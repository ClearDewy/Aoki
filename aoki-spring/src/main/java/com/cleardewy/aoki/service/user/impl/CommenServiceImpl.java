package com.cleardewy.aoki.service.user.impl;

import com.cleardewy.aoki.entity.vo.user.AccountVo;
import com.cleardewy.aoki.entity.vo.user.EmailVerifyVo;
import com.cleardewy.aoki.entity.vo.user.UserVo;
import com.cleardewy.aoki.manager.user.UserManager;
import com.cleardewy.aoki.manager.account.AccountManager;
import com.cleardewy.aoki.manager.account.EmailVerifyManager;
import com.cleardewy.aoki.manager.entity.UserEntityManager;
import com.cleardewy.aoki.service.user.CommenService;
import com.cleardewy.aoki.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ ClassName: UserServiceImpl
 * @ Author: ClearDewy
 * @ Date: 2023/3/16 21:51
 * @ Description:
 **/
@Service
public class CommenServiceImpl implements CommenService {

    @Autowired
    AccountManager accountManager;

    @Autowired
    UserManager userManager;

    @Autowired
    EmailVerifyManager emailVerifyManager;

    @Autowired
    UserEntityManager userEntityManager;

    @Override
    public Result login(AccountVo accountVo) {
        return Result.success(accountManager.login(accountVo));
    }

    @Override
    public Result logout() {
        accountManager.logout();
        return Result.success();
    }

    @Override
    public Result register(UserVo userVo,String code) {
        accountManager.register(userVo,code);
        return Result.success();
    }

    @Override
    public Result getVerifyCode(EmailVerifyVo emailVerifyVo) {
        return emailVerifyManager.sendVerifyCode(emailVerifyVo.getEmail());
    }

    @Override
    public Result verifyCode(EmailVerifyVo emailVerifyVo) {
        emailVerifyManager.verifyCode(emailVerifyVo);
        return Result.success();
    }

    @Override
    public Result getAllMajor() {
        return Result.success(userEntityManager.getAllMajor());
    }

    @Override
    public Result emailLogin(EmailVerifyVo emailVerifyVo) {
        return Result.success(accountManager.emailLogin(emailVerifyVo));
    }

}
