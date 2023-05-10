package com.cleardewy.aoki.service.admin.impl;

import com.cleardewy.aoki.entity.vo.user.UserVo;
import com.cleardewy.aoki.manager.admin.AdminManager;
import com.cleardewy.aoki.service.admin.AdminService;
import com.cleardewy.aoki.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminManager adminManager;
    @Override
    public Result getAllUser() {
        return Result.success(adminManager.getAllUser());
    }

    @Override
    public Result addUser(UserVo userVo) {
        return Result.success(adminManager.addUser(userVo));
    }

    @Override
    public Result updateUser(UserVo userVo) {
        adminManager.updateUser(userVo);
        return Result.success();
    }

    @Override
    public Result deleteUser(Integer id) {
        adminManager.deleteUser(id);
        return Result.success();
    }

    @Override
    public Result addMajor(String major) {
        adminManager.addMajor(major);
        return Result.success();
    }

    @Override
    public Result deleteMajor(String major) {
        adminManager.deleteMajor(major);
        return Result.success();
    }
}
