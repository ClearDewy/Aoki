package com.cleardewy.aoki.service.user.impl;

import com.cleardewy.aoki.entity.vo.user.UpdateEmailVo;
import com.cleardewy.aoki.entity.vo.user.UpdatePasswordVo;
import com.cleardewy.aoki.manager.user.UserManager;
import com.cleardewy.aoki.service.user.UserService;
import com.cleardewy.aoki.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserManager userManager;
    @Override
    public Result updatePassword(UpdatePasswordVo updatePasswordVo) {
        userManager.updatePassword(updatePasswordVo);
        return Result.success();
    }

    @Override
    public Result updateEmail(UpdateEmailVo updateEmailVo) {
        userManager.updateEmail(updateEmailVo);
        return Result.success();
    }
}
