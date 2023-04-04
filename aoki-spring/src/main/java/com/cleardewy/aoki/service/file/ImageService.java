package com.cleardewy.aoki.service.file;

import com.cleardewy.aoki.utils.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
public interface ImageService {
    Result uploadAvatar(MultipartFile avatar);

}
