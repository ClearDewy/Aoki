package com.cleardewy.aoki.controller.file;

import com.cleardewy.aoki.annotation.AokiRole;
import com.cleardewy.aoki.service.file.ImageService;
import com.cleardewy.aoki.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static com.cleardewy.aoki.constant.RoleConstans.*;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@RestController
@AokiRole({ROOT, TEACHER, STUDENT})
@RequestMapping("/api/file")
public class ImageController {
    @Autowired
    ImageService imageService;

    @PostMapping("upload-avatar")
    public Result uploadAvatar(MultipartFile avatar){
        return imageService.uploadAvatar(avatar);
    }
}
