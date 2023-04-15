package com.cleardewy.aoki.service.file;

import com.cleardewy.aoki.utils.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
public interface FileService {
    Result uploadAvatar(MultipartFile avatar);

    Result uploadImage(MultipartFile image);

    Result uploadFile(MultipartFile file);

    void downloadFile(String filename);
}
