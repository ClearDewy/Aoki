package com.cleardewy.aoki.service.admin;

import com.cleardewy.aoki.entity.vo.user.UserVo;
import com.cleardewy.aoki.utils.Result;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
public interface AdminService {
    Result getAllUser();

    Result addUser(UserVo userVo);

    Result updateUser(UserVo userVo);

    Result deleteUser(Integer id);

    Result addMajor(String major);

    Result deleteMajor(String major);
}
