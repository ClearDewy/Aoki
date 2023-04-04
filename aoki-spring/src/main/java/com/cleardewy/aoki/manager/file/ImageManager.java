package com.cleardewy.aoki.manager.file;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import com.cleardewy.aoki.constant.Constants;
import com.cleardewy.aoki.constant.ResultStatus;
import com.cleardewy.aoki.entity.dto.UserDto;
import com.cleardewy.aoki.exception.AokiException;
import com.cleardewy.aoki.manager.entity.UserEntityManager;
import com.cleardewy.aoki.utils.FileUtils;
import com.cleardewy.aoki.utils.ThreadLocalUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.util.ProcessIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


/**
 * @ Author: ClearDewy
 * @ Description: 处理图片上传
 **/
@Component
@Slf4j
public class ImageManager {
    static final String []IMG_SUFFIX=new String[]{
            "jpg","jpeg","gif","png","webp"
    };

    @Autowired
    ThreadLocalUtils threadLocalUtils;

    @Autowired
    UserEntityManager userEntityManager;

    public UserDto uploadAvatar(MultipartFile avatar) {
        if (avatar.isEmpty())
            throw AokiException.fail(ResultStatus.Message.AVATAR_IS_EMPTY);
        if (avatar.getSize()>1024*1024*2)
            throw AokiException.fail(ResultStatus.Message.AVATAR_SIZE_LIMIT);
        String suffix=avatar.getOriginalFilename().substring(avatar.getOriginalFilename().lastIndexOf('.')+1).toLowerCase();
        if (!ArrayUtils.contains(IMG_SUFFIX,suffix))
            throw AokiException.fail(ResultStatus.Message.IMG_SUFFIX_ERROR);
        FileUtil.mkdir(Constants.FileConstants.AVATAR_PATH);

        String fileName= IdUtil.fastSimpleUUID()+"."+suffix;
        try {
            avatar.transferTo(FileUtil.file(Constants.FileConstants.AVATAR_PATH+File.separator+fileName));
        } catch (IOException e) {
            throw AokiException.systemError(ResultStatus.Message.IMG_UPLOAD_FAIL);
        }

        UserDto user=threadLocalUtils.getCurrentUser();
        user.setAvatarURL(Constants.FileConstants.AVATAR_API+fileName);

        userEntityManager.updateUserById(user);

        return user;
    }
}
