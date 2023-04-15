package com.cleardewy.aoki.controller.file;

import com.cleardewy.aoki.annotation.AokiRole;
import com.cleardewy.aoki.service.file.FileService;
import com.cleardewy.aoki.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.cleardewy.aoki.constant.RoleConstans.*;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@RestController
//@AokiRole({ROOT, TEACHER, STUDENT})
@RequestMapping("/api/file")
public class FileController {
    @Autowired
    FileService fileService;

    @PostMapping("upload-avatar")
    public Result uploadAvatar(MultipartFile avatar){
        return fileService.uploadAvatar(avatar);
    }

    @PostMapping("upload-image")
    public Result uploadImage(MultipartFile image){
        return fileService.uploadImage(image);
    }

    @PostMapping("upload-file")
    public Result uploadFile(MultipartFile file){
        return fileService.uploadFile(file);
    }

    @GetMapping("/download/{filename}")
    public void downloadFile(@PathVariable("filename")String filename){
        fileService.downloadFile(filename);
    }
}
