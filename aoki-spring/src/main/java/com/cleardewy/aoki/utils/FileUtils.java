package com.cleardewy.aoki.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.MD5;
import com.cleardewy.aoki.entity.dto.FileDto;
import com.cleardewy.aoki.exception.AokiException;
import com.cleardewy.aoki.manager.entity.FileEntityManager;
import com.cleardewy.aoki.mapper.FileMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.cli.Digest;
import org.apache.tomcat.util.digester.Digester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @ Author: ClearDewy
 * @ Description: 文件工具类
 **/
@Component
@Slf4j
public class FileUtils {
    @Autowired
    FileEntityManager fileEntityManager;

    public FileDto addFile(MultipartFile multipartFile,String type) throws IOException {
        String md5;
        md5 = DigestUtil.md5Hex(multipartFile.getInputStream());
        FileDto fileDto = fileEntityManager.getFileByMd5(md5);
        if (fileDto==null){
            fileDto=new FileDto(null,
                    IdUtil.fastSimpleUUID(),
                    multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf('.')+1).toLowerCase(),
                    md5,
                    multipartFile.getSize(),
                    type,
                    false);
            FileUtil.mkdir(fileDto.getAllPath());
            multipartFile.transferTo(FileUtil.file(fileDto.getAllPath()));
            fileEntityManager.addFile(fileDto);
        }

        return fileDto;
    }

}
