package com.cleardewy.aoki.exception;

import com.cleardewy.aoki.utils.Result;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@ControllerAdvice
@ResponseBody
public class GloabalExceptionAdvice {

    @ExceptionHandler(AokiException.class)
    public Result handleException(AokiException e){
        return Result.error(e.getCode(),e.getMessage());
    }
}
