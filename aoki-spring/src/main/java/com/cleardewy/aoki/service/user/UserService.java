package com.cleardewy.aoki.service.user;

import com.cleardewy.aoki.entity.dto.UserDto;
import com.cleardewy.aoki.entity.vo.AccountVo;
import com.cleardewy.aoki.entity.vo.UserVo;
import com.cleardewy.aoki.utils.Result;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
public interface UserService {
    Result login(AccountVo accountVo);

    Result logout();

    Result register(UserVo userVo);
}
