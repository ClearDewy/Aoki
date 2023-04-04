package com.cleardewy.aoki.manager.entity;

import com.cleardewy.aoki.entity.dto.FileDto;
import com.cleardewy.aoki.entity.dto.UserDto;
import com.cleardewy.aoki.mapper.FileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Component
public class FileEntityManager {
    @Autowired
    FileMapper fileMapper;

    public boolean addFile(FileDto file){
        return fileMapper.addFile(file)==1;
    }

    public FileDto getFileByMd5(String md5){
        return fileMapper.getFileByMd5(md5);
    }

    public boolean setFileDeleted(String name){
        return fileMapper.setFileDeleted(name)==1;
    }

    public boolean deleteFile(String name){
        return fileMapper.deleteFile(name)==1;
    }
}
