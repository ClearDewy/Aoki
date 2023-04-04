package com.cleardewy.aoki.service.user;

import com.cleardewy.aoki.entity.vo.user.UpdateEmailVo;
import com.cleardewy.aoki.entity.vo.user.UpdatePasswordVo;
import com.cleardewy.aoki.utils.Result;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
public interface UserService {

    Result updatePassword(UpdatePasswordVo updatePasswordVo);

    Result updateEmail(UpdateEmailVo updateEmailVo);
}
