package com.cleardewy.aoki.manager.account;

import com.cleardewy.aoki.constant.ResultStatus;
import com.cleardewy.aoki.entity.vo.user.EmailVerifyVo;
import com.cleardewy.aoki.exception.AokiException;
import com.cleardewy.aoki.utils.MailUtils;
import com.cleardewy.aoki.utils.RedisUtils;
import com.cleardewy.aoki.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Component
public class EmailVerifyManager {
    @Autowired
    RedisUtils redisUtils;

    @Autowired
    MailUtils mailUtils;

    public Result sendVerifyCode(String email) {
        String code=getVerifyCode(email);
        redisUtils.set("email_verify_code:"+email,code,600);
        Date date=new Date(redisUtils.getExpire("email_verify_code_time:"+email)+600*1000);

        mailUtils.sendRegisterCode(email,code,date);
        return Result.success();
    }

    public String getVerifyCode(String email){
        if (redisUtils.hasKey("email_verify_code_time:"+email))throw new AokiException(ResultStatus.Status.FAIL, ResultStatus.Message.VERIFY_CODE_FREQUENT);
        redisUtils.set("email_verify_code_time:"+email,1,60);
        Random random=new Random();
        return String.format("%06d",random.nextInt(1000000));
    }

    public void verifyCode(EmailVerifyVo emailVerifyVo){
        if (!redisUtils.hasKey("email_verify_code:"+emailVerifyVo.getEmail())||!redisUtils.get("email_verify_code:"+emailVerifyVo.getEmail()).equals(emailVerifyVo.getCode()))
            throw new AokiException(ResultStatus.Status.FAIL,ResultStatus.Message.VERIFY_CODE_WRONG);
    }

}
