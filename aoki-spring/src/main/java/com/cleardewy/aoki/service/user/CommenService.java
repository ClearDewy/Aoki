package com.cleardewy.aoki.service.user;

import com.cleardewy.aoki.entity.vo.user.AccountVo;
import com.cleardewy.aoki.entity.vo.user.EmailVerifyVo;
import com.cleardewy.aoki.entity.vo.user.UserVo;
import com.cleardewy.aoki.utils.Result;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
public interface CommenService {
    Result login(AccountVo accountVo);

    Result logout();

    Result register(UserVo userVo,String code);

    Result getVerifyCode(EmailVerifyVo emailVerifyVo);

    Result verifyCode(EmailVerifyVo emailVerifyVo);

    Result getAllMajor();

    Result emailLogin(EmailVerifyVo emailVerifyVo);
}
