package com.cleardewy.aoki.service.entity.Impl;

import com.cleardewy.aoki.constant.ResultStatus;
import com.cleardewy.aoki.entity.dto.UserDto;
import com.cleardewy.aoki.entity.vo.AccountVo;
import com.cleardewy.aoki.entity.vo.UserVo;
import com.cleardewy.aoki.exception.AokiException;
import com.cleardewy.aoki.mapper.UserMapper;
import com.cleardewy.aoki.service.entity.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Service
public class UserEntityServiceImpl implements UserEntityService {
    @Autowired
    UserMapper userMapper;

    @Override
    public String getRoleById(Integer id) {
        String role = userMapper.getRoleById(id);
        if (role.isEmpty())throw new AokiException(ResultStatus.FAIL);
        return role;
    }

    @Override
    public UserDto getUserByUsername(String username) {
        List<UserDto> userDtoList = userMapper.getUserByUsername(username);
        if (userDtoList.isEmpty())throw new AokiException(ResultStatus.FAIL);
        return userDtoList.get(0);
    }
    @Override
    public String getRoleByRoleId(Integer roleId){
        String role = userMapper.getRoleByRoleId(roleId);
        if (role.isEmpty())throw new AokiException(ResultStatus.FAIL);
        return role;
    }
    @Override
    public String getMajorByMajorId(Integer majorId){
        String major = userMapper.getMajorByMajorId(majorId);
        if (major.isEmpty())throw new AokiException(ResultStatus.FAIL);
        return major;
    }
    @Override
    public UserVo userDtoToUserVo(UserDto userDto){
        return new UserVo(userDto.getUsername(),userDto.getName(),userDto.getNumber(),userDto.getEmail(),getMajorByMajorId(userDto.getMajorId()),getRoleByRoleId(userDto.getRoleId()));
    }
}
