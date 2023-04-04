package com.cleardewy.aoki.entity.dto;

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
    private Integer size;
    private String path;
    private String url;
    private boolean isDelete;

    public String getAllPath(){
        return path+ File.separator+name+"."+suffix;
    }

    public String getAllUrl(){
        return url+name+"."+suffix;
    }
}
