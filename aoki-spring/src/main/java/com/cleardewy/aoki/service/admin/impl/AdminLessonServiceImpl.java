package com.cleardewy.aoki.service.admin.impl;

import com.cleardewy.aoki.manager.admin.AdminLessonManager;
import com.cleardewy.aoki.service.admin.AdminLessonService;
import com.cleardewy.aoki.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Service
public class AdminLessonServiceImpl implements AdminLessonService {
    @Autowired
    private AdminLessonManager adminLessonManager;
    @Override
    public Result getAllLesson() {
        return Result.success(adminLessonManager.getAllLesson());
    }
}
