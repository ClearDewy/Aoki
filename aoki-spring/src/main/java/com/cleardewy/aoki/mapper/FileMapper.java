package com.cleardewy.aoki.mapper;

import com.cleardewy.aoki.entity.dto.FileDto;
import com.cleardewy.aoki.entity.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Mapper
public interface FileMapper {
    Integer addFile(FileDto file);

    FileDto getFileByMd5(String md5);

    Integer setFileDeleted(String name);

    Integer deleteFile(String name);
}
