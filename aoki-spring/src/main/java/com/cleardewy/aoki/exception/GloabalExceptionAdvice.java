package com.cleardewy.aoki.exception;

import com.cleardewy.aoki.constant.ResultStatus;
import com.cleardewy.aoki.utils.Result;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
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
    public Result handleAokiException(AokiException e){
        return Result.error(e.getCode(),e.getMessage());
    }


    /**
     * @ Author: ClearDewy
     * @ Description: 捕获参数异常
     **/
    @ExceptionHandler(BindException.class)
    public Result handleValidException(BindException e){
        String errorMessage = e.getBindingResult().getFieldError().getDefaultMessage();
        return Result.error(ResultStatus.Status.FAIL, errorMessage);
    }
}
