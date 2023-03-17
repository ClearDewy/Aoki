package com.cleardewy.aoki.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ClassName: CommonResult
 * @Author: ClearDewy
 * @Date: 2023/3/16 16:41
 * @@Description:
 **/
@Data
@AllArgsConstructor
public class Result<T> {
    private final Integer code;   // 状态码
    private final String message;    // 自定义信息
    private final T data;   // 返回的数据

    public Result(Integer code,String msg){
        this.code=code;
        this.message=msg;
        data=null;
    }

}
