package com.cleardewy.aoki.entity.dto;

import com.cleardewy.aoki.constant.Constants;
import com.cleardewy.aoki.constant.FileConstants;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.File;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Data
@AllArgsConstructor
public class FileDto {
    private Integer id;
    private  String name;
    private String suffix;
    private String md5;
    private long size;
    private String type;
    private boolean isDelete;

    public String getAllPath(){
        return switch (type){
            case FileConstants.AVATAR_TYPE -> FileConstants.AVATAR_PATH;
            case FileConstants.IMG_TYPE -> FileConstants.IMG_PATH;
            case FileConstants.FILE_TYPE -> FileConstants.FILE_PATH;
            default -> "";
        }+File.separator+name+"."+suffix;
    }

    public String getAllUrl(){
        return switch (type){
            case FileConstants.AVATAR_TYPE -> FileConstants.AVATAR_API;
            case FileConstants.IMG_TYPE -> FileConstants.IMG_API;
            case FileConstants.FILE_TYPE -> FileConstants.FILE_API;
            default -> "";
        }+name+"."+suffix;
    }
}
