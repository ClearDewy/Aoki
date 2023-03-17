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

    public AokiException(ResultStatus resultStatus){
        super(resultStatus.getMessage());
        this.code=resultStatus.getStatus();
    }
}
