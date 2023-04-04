package com.cleardewy.aoki.service.file.impl;

import com.cleardewy.aoki.manager.file.ImageManager;
import com.cleardewy.aoki.service.file.ImageService;
import com.cleardewy.aoki.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    ImageManager imageManager;
    @Override
    public Result uploadAvatar(MultipartFile avatar) {
        return Result.success(imageManager.uploadAvatar(avatar));
    }
}
