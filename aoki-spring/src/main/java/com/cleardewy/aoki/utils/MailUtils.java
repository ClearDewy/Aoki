package com.cleardewy.aoki.utils;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * @ Author: ClearDewy
 * @ Description: 发送邮件的工具类
 **/
@Component
public class MailUtils {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    /**
     * @ Author: ClearDewy
     * @ Param: []
     * @ Return: void
     * @ Description: 解析文件模板
     **/
    public void sendHtmlMail()throws Exception{
        Context context = new Context();
        context.setVariable("id","001"); //为html页面的id赋值
        String emailContent = templateEngine.process("emailTemplate",context);
        sendHtmlMail("xxx@qq.com","这是第一封模板邮件",emailContent);
    }

    /**
     * @ Author: ClearDewy
     * @ Param: [java.lang.String, java.lang.String, java.lang.String]
     * @ Return: void
     * @ Description: 发送邮件
     **/
    public void sendHtmlMail(String to, String subject, String content) throws Exception {
        //创建MIME样式的电子邮件对象
        MimeMessage message = mailSender.createMimeMessage();

        //用于填充MimeMessage的帮助类
        //参数1填入MimeMessage对象
        //参数2表示是否创建支持替代文本、内联元素和附件的多部分消息
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        //参数2表示是否使用默认内容类型为HTML邮件应用内容类型"text/html"
        helper.setText(content, true);
        mailSender.send(message);
    }
}
