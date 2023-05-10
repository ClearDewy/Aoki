package com.cleardewy.aoki.controller.admin;

import com.cleardewy.aoki.annotation.AokiRole;
import com.cleardewy.aoki.entity.vo.user.UserVo;
import com.cleardewy.aoki.service.admin.AdminService;
import com.cleardewy.aoki.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.cleardewy.aoki.constant.RoleConstans.ADMIN;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@RestController
@RequestMapping("/api/admin")
@AokiRole({ADMIN})
public class AdminController {
    @Autowired
    private AdminService adminService;
    @GetMapping("/get-all-user")
    Result getAllUser(){
        return adminService.getAllUser();
    }

    @PostMapping("/add-user")
    Result addUser(@RequestBody UserVo userVo){
        return adminService.addUser(userVo);
    }

    @PostMapping("/update-user")
    Result updateUser(@RequestBody UserVo userVo){
        return adminService.updateUser(userVo);
    }

    @PostMapping("/delete-user")
    Result deleteUser(Integer id){
        return adminService.deleteUser(id);
    }

    @PostMapping("/add-major")
    Result addMajor(String major){
        return adminService.addMajor(major);
    }
    @PostMapping("/delete-major")
    Result deleteMajor(String major){
        return adminService.deleteMajor(major);
    }
}
