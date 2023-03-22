package com.cleardewy.aoki.manager.User;

import com.cleardewy.aoki.entity.dto.UserDto;
import com.cleardewy.aoki.entity.vo.UserVo;
import com.cleardewy.aoki.manager.entity.UserEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Component
public class UserManager {

    @Autowired
    UserEntityManager userEntityManager;
    public UserVo userDtoToUserVo(UserDto userDto){
        return new UserVo(userDto.getUsername(),"",userDto.getName(),userDto.getNumber(),userDto.getEmail(),userEntityManager.getMajorByMajorId(userDto.getMajorId()),userEntityManager.getRoleByRoleId(userDto.getRoleId()));
    }

    public UserDto userVoToUserDto(UserVo userVo){
        return new UserDto(-1,userVo.getUsername(),userVo.getPassword(),userVo.getName(),userVo.getNumber(),userVo.getEmail(),userEntityManager.getMajorIdByMajor(userVo.getMajor()),userEntityManager.getRoleIdByRole(userVo.getRole()));
    }

}
