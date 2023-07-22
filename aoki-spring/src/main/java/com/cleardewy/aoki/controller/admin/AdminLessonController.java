package com.cleardewy.aoki.controller.admin;

import com.cleardewy.aoki.annotation.AokiRole;
import com.cleardewy.aoki.service.admin.AdminLessonService;
import com.cleardewy.aoki.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.cleardewy.aoki.constant.RoleConstans.ADMIN;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@RestController
@RequestMapping("/api")
@AokiRole(ADMIN)
public class AdminLessonController {

    @Autowired
    private AdminLessonService adminLessonService;

    @GetMapping("/get-all-lesson")
    Result getAllLesson(){
        return adminLessonService.getAllLesson();
    }
}
