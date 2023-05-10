package com.cleardewy.aoki.utils;

import com.cleardewy.aoki.config.AokiConfigProperties;
import com.cleardewy.aoki.constant.ResultStatus;
import com.cleardewy.aoki.exception.AokiException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Date;

/**
 * @ Author: ClearDewy
 * @ Description: 发送邮件的工具类
 **/
@Component
@Slf4j
public class MailUtils {

    @Autowired
    AokiConfigProperties aokiConfigProperties;

    @Autowired
    private TemplateEngine templateEngine;

    /**
     * @ Author: ClearDewy
     * @ Param: []
     * @ Return: void
     * @ Description: 解析文件模板
     **/
//    public void sendHtmlMail()throws Exception{
//        Context context = new Context();
//        context.setVariable("AOKI_NAME","Aoki"); //为html页面的id赋值
//        String emailContent = templateEngine.process("emailTemplate_registerCode",context);
//        sendHtmlMail("xxx@qq.com","这是第一封模板邮件",emailContent);
//    }

    public void sendRegisterCode(String mail, String code, Date date){
        Context context = new Context();
        context.setVariable("AOKI_NAME","Aoki"); //为html页面的id赋值
        context.setVariable("CODE",code);
        context.setVariable("EXPIRE_TIME",date);
        String emailContent = templateEngine.process("emailTemplate",context);
        try {
            sendHtmlMail(mail,"来自Aoki的邮件",emailContent);
        }catch (Exception e){
            log.error(e.getLocalizedMessage());
            throw new AokiException(ResultStatus.Status.FAIL, ResultStatus.Message.EMAIL_SEND_FAIL);
        }
    }

    /**
     * @ Author: ClearDewy
     * @ Description: 发送邮件
     **/
    public void sendHtmlMail(String to, String subject, String content) throws Exception {
        //实例化
        JavaMailSenderImpl mailSender = getJavaMailSender();
        //创建MIME样式的电子邮件对象
        MimeMessage message = mailSender.createMimeMessage();
        //用于填充MimeMessage的帮助类
        //参数1填入MimeMessage对象
        //参数2表示是否创建支持替代文本、内联元素和附件的多部分消息
        MimeMessageHelper helper = new MimeMessageHelper(message, true,"UTF-8");
        helper.setFrom(aokiConfigProperties.getSpring().getMail().getUsername());
        helper.setTo(to);
        helper.setSubject(subject);
        //参数2表示是否使用默认内容类型为HTML邮件应用内容类型"text/html"
        helper.setText(content, true);
        mailSender.send(message);
    }

    public JavaMailSenderImpl getJavaMailSender(){
        JavaMailSenderImpl mailSender=new JavaMailSenderImpl();
        mailSender.setUsername(aokiConfigProperties.getSpring().getMail().getUsername());
        mailSender.setPassword(aokiConfigProperties.getSpring().getMail().getPassword());
        mailSender.setHost(aokiConfigProperties.getSpring().getMail().getHost());
        mailSender.setPort(Integer.parseInt(aokiConfigProperties.getSpring().getMail().getPort()));
        mailSender.setProtocol(aokiConfigProperties.getSpring().getMail().getProtocol());
        mailSender.setDefaultEncoding("UTF-8");
        return mailSender;
    }
}
