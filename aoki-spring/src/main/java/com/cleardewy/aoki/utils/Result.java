package com.cleardewy.aoki.utils;

import com.cleardewy.aoki.constant.ResultStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Data
@AllArgsConstructor
public class Result {
    private final Integer status;   // 状态码
    private final String message;    // 自定义信息
    private final Object data;   // 返回的数据

    public Result(Integer status,String msg){
        this.status=status;
        this.message=msg;
        data=null;
    }

    public static Result success(){
        return new Result(ResultStatus.Status.SUCCESS.getStatus(),null,null);
    }
    public static Result success(Object data){
        return new Result(ResultStatus.Status.SUCCESS.getStatus(),null,data);
    }

    public static Result error(ResultStatus.Status status){
        return new Result(status.getStatus(),status.getMessage());
    }
    public static Result error(ResultStatus.Status status,String message){
        return new Result(status.getStatus(),message);
    }

    public static Result error(ResultStatus.Status status,ResultStatus.Message message){
        return new Result(status.getStatus(),message.getMessage());
    }

    public static Result error(Integer status,String msg){
        return new Result(status,msg);
    }



}
