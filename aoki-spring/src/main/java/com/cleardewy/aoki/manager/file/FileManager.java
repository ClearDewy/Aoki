package com.cleardewy.aoki.manager.file;

import cn.hutool.core.io.FileUtil;
import com.cleardewy.aoki.constant.FileConstants;
import com.cleardewy.aoki.constant.ResultStatus;
import com.cleardewy.aoki.entity.dto.FileDto;
import com.cleardewy.aoki.exception.AokiException;
import com.cleardewy.aoki.manager.entity.UserEntityManager;
import com.cleardewy.aoki.utils.FileUtils;
import com.cleardewy.aoki.utils.ThreadLocalUtils;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


/**
 * @ Author: ClearDewy
 * @ Description: 处理图片上传
 **/
@Component
@Slf4j
public class FileManager {
    static final String []IMG_SUFFIX=new String[]{
            "jpg","jpeg","gif","png","webp"
    };

    @Autowired
    ThreadLocalUtils threadLocalUtils;

    @Autowired
    UserEntityManager userEntityManager;

    @Autowired
    FileUtils fileUtils;

    public FileDto uploadAvatar(MultipartFile avatar) {
        if (avatar.isEmpty())
            throw AokiException.fail(ResultStatus.Message.AVATAR_IS_EMPTY);
        if (avatar.getSize()>1024*1024*2)
            throw AokiException.fail(ResultStatus.Message.AVATAR_SIZE_LIMIT);
        String suffix=avatar.getOriginalFilename().substring(avatar.getOriginalFilename().lastIndexOf('.')+1).toLowerCase();
        if (!ArrayUtils.contains(IMG_SUFFIX,suffix))
            throw AokiException.fail(ResultStatus.Message.IMG_SUFFIX_ERROR);

        try {
            return fileUtils.addFile(avatar, FileConstants.AVATAR_TYPE);
        } catch (IOException e) {
            throw AokiException.systemError(ResultStatus.Message.IMG_UPLOAD_FAIL);
        }
    }

    public FileDto uploadImage(MultipartFile image) {
        if (image.isEmpty())
            throw AokiException.fail(ResultStatus.Message.IMG_IS_EMPTY);
        if (image.getSize()>1024*1024*2)
            throw AokiException.fail(ResultStatus.Message.IMG_SIZE_LIMIT);
        String suffix=image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf('.')+1).toLowerCase();
        if (!ArrayUtils.contains(IMG_SUFFIX,suffix))
            throw AokiException.fail(ResultStatus.Message.IMG_SUFFIX_ERROR);

        try {
            return fileUtils.addFile(image, FileConstants.IMG_TYPE);
        } catch (IOException e) {
            throw AokiException.systemError(ResultStatus.Message.IMG_UPLOAD_FAIL);
        }
    }

    public FileDto uploadFile(MultipartFile file) {
        if (file.isEmpty())
            throw AokiException.fail(ResultStatus.Message.FILE_IS_EMPTY);
        if (file.getSize()>1024*1024*1024)
            throw AokiException.fail(ResultStatus.Message.FILE_SIZE_LIMIT);
        String suffix=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.')+1).toLowerCase();
        if (suffix.isEmpty())
            throw AokiException.fail(ResultStatus.Message.FILE_SUFFIX_ERROR);

        try {
            return fileUtils.addFile(file, FileConstants.FILE_TYPE);
        } catch (IOException e) {
            throw AokiException.systemError(ResultStatus.Message.FILE_UPLOAD_FAIL);
        }
    }

    public void downloadFile(String filename) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = servletRequestAttributes.getResponse();      // 获取响应

        String path=FileConstants.FILE_PATH+File.separator+filename;
        try {
            // 读到流中
            InputStream inputStream = new FileInputStream(FileUtil.getAbsolutePath(path));// 文件的存放路径
            response.setContentType("application/octet-stream");
            response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, StandardCharsets.UTF_8));
            ServletOutputStream outputStream = response.getOutputStream();
            byte[] b = new byte[1024];
            //从输入流中读取一定数量的字节，并将其存储在缓冲区字节数组中，读到末尾返回-1
            for (int len;(len = inputStream.read(b)) > 0;) {
                outputStream.write(b, 0, len);
            }
            inputStream.close();
        } catch (IOException e) {
            throw AokiException.notFound(e.getMessage());
        }
    }
}
