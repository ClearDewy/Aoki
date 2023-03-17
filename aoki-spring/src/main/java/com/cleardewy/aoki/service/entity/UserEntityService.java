package com.cleardewy.aoki.service.entity;

import com.cleardewy.aoki.entity.dto.UserDto;
import com.cleardewy.aoki.entity.vo.AccountVo;
import com.cleardewy.aoki.entity.vo.UserVo;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
public interface UserEntityService {
    String getRoleById(Integer id);

    UserDto getUserByUsername(String username);

    String getRoleByRoleId(Integer roleId);

    String getMajorByMajorId(Integer majorId);

    UserVo userDtoToUserVo(UserDto userDto);
}
