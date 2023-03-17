package com.cleardewy.aoki.service.user.Impl;

import com.cleardewy.aoki.mapper.UserMapper;
import com.cleardewy.aoki.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ ClassName: UserServiceImpl
 * @ Author: ClearDewy
 * @ Date: 2023/3/16 21:51
 * @ Description:
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public String getRoleById(Integer id) {
        return userMapper.getRoleById(id);
    }
}
