package com.cleardewy.aoki.service.file.impl;

import com.cleardewy.aoki.manager.file.FileManager;
import com.cleardewy.aoki.service.file.FileService;
import com.cleardewy.aoki.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileManager fileManager;
    @Override
    public Result uploadAvatar(MultipartFile avatar) {
        return Result.success(fileManager.uploadAvatar(avatar).getAllUrl());
    }

    @Override
    public Result uploadImage(MultipartFile image) {
        return Result.success(fileManager.uploadImage(image).getAllUrl());
    }

    @Override
    public Result uploadFile(MultipartFile file) {
        return Result.success(fileManager.uploadFile(file).getAllUrl());
    }

    @Override
    public void downloadFile(String filename) {
        fileManager.downloadFile(filename);
    }
}
