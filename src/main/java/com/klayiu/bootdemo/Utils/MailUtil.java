package com.klayiu.bootdemo.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author klay
 * @create 2020-05-08 10:30
 *
 *
 * 发送邮件实例
 *
 * @Component 交由Spring管理
 */

@Component
public class MailUtil {

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * 两种方式
     * @1  读取 yml 文件中配置项 ,使用SpringEl 表达式
     * @2  传具体参数
     */
    @Value("${spring.mail.username}")
    private String sender;


    /**
     * 发送邮件简单测试方法
     */
    public void sendMail() {
        SimpleMailMessage mimeMessage = new SimpleMailMessage();
        mimeMessage.setFrom(sender);                // 从哪里发送
        mimeMessage.setTo(sender);                  // 发送到哪里
        mimeMessage.setSubject("这是一个主题");       // 主题
        mimeMessage.setText("这是正文内容");          // 正文
        mimeMessage.setSentDate(new Date());         // 发送日期
        javaMailSender.send(mimeMessage);
    }
}
