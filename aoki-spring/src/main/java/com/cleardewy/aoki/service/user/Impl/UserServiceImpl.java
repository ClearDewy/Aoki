package com.cleardewy.aoki.service.user.Impl;

import com.cleardewy.aoki.constant.ResultStatus;
import com.cleardewy.aoki.entity.dto.UserDto;
import com.cleardewy.aoki.entity.vo.AccountVo;
import com.cleardewy.aoki.entity.vo.UserVo;
import com.cleardewy.aoki.exception.AokiException;
import com.cleardewy.aoki.manager.User.UserManager;
import com.cleardewy.aoki.manager.account.AccountManager;
import com.cleardewy.aoki.mapper.UserMapper;
import com.cleardewy.aoki.service.user.UserService;
import com.cleardewy.aoki.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ ClassName: UserServiceImpl
 * @ Author: ClearDewy
 * @ Date: 2023/3/16 21:51
 * @ Description:
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    AccountManager accountManager;

    @Autowired
    UserManager userManager;

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
    public Result register(UserVo userVo) {
        accountManager.register(userVo);
        return Result.success();
    }

}
