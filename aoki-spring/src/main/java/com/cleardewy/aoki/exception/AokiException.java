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
}
