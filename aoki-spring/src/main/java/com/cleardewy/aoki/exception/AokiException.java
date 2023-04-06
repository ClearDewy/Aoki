package com.cleardewy.aoki.exception;

import com.cleardewy.aoki.constant.ResultStatus;
import lombok.Getter;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Getter
public class AokiException extends RuntimeException{
    private Integer code;
    public AokiException(Integer code, String msg){
        super(msg);
        this.code=code;
    }

    public AokiException(ResultStatus.Status status){
        super(status.getMessage());
        this.code=status.getStatus();
    }

    public AokiException(ResultStatus.Status status,ResultStatus.Message message){
        super(message.getMessage());
        this.code=status.getStatus();
    }


    public static AokiException fail(){
        return new AokiException(ResultStatus.Status.FAIL);
    }

    public static AokiException fail(String message){
        return new AokiException(ResultStatus.Status.FAIL.getStatus(),message);
    }

    public static AokiException fail(ResultStatus.Message message){
        return new AokiException(ResultStatus.Status.FAIL,message);
    }

    public static AokiException accessDenied(){
        return new AokiException(ResultStatus.Status.ACCESS_DENIED);
    }

    public static AokiException accessDenied(String message){
        return new AokiException(ResultStatus.Status.ACCESS_DENIED.getStatus(),message);
    }
    public static AokiException accessDenied(ResultStatus.Message message){
        return new AokiException(ResultStatus.Status.ACCESS_DENIED,message);
    }

    public static AokiException overDue(){
        return new AokiException(ResultStatus.Status.OVERDUE);
    }

    public static AokiException overDue(String message){
        return new AokiException(ResultStatus.Status.OVERDUE.getStatus(),message);
    }
    public static AokiException overDue(ResultStatus.Message message){
        return new AokiException(ResultStatus.Status.OVERDUE,message);
    }

    public static AokiException forbidden(){
        return new AokiException(ResultStatus.Status.FORBIDDEN);
    }

    public static AokiException forbidden(String message){
        return new AokiException(ResultStatus.Status.FORBIDDEN.getStatus(),message);
    }
    public static AokiException forbidden(ResultStatus.Message message){
        return new AokiException(ResultStatus.Status.FORBIDDEN,message);
    }

    public static AokiException notFound(){
        return new AokiException(ResultStatus.Status.NOT_FOUND);
    }

    public static AokiException notFound(String message){
        return new AokiException(ResultStatus.Status.NOT_FOUND.getStatus(),message);
    }
    public static AokiException notFound(ResultStatus.Message message){
        return new AokiException(ResultStatus.Status.NOT_FOUND,message);
    }

    public static AokiException systemError(){
        return new AokiException(ResultStatus.Status.SYSTEM_ERROR);
    }

    public static AokiException systemError(String message){
        return new AokiException(ResultStatus.Status.SYSTEM_ERROR.getStatus(),message);
    }
    public static AokiException systemError(ResultStatus.Message message){
        return new AokiException(ResultStatus.Status.SYSTEM_ERROR,message);
    }
}
