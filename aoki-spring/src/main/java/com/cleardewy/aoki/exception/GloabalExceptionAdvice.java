package com.cleardewy.aoki.exception;

import com.cleardewy.aoki.constant.ResultStatus;
import com.cleardewy.aoki.utils.Result;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;


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
    /**
     * @ Author: ClearDewy
     * @ Description: 捕获数据库异常，主要处理选课人数上限问题
     **/
    @ExceptionHandler(SQLException.class)
    public Result handleValidException(SQLException e){
        return switch (e.getErrorCode()){
            case 1642->Result.error(ResultStatus.Status.FAIL,ResultStatus.Message.TOPIC_MEMBER_FULL);
            default -> Result.error(ResultStatus.Status.SYSTEM_ERROR);
        };
    }
}
