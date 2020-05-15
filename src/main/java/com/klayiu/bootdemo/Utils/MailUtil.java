package com.klayiu.bootdemo.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
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
    public void  sendSingleMail() {
        //简单邮件测试
        SimpleMailMessage mimeMessage = new SimpleMailMessage();
        mimeMessage.setFrom("2748116048@qq.com");                // 从哪里发送
        mimeMessage.setTo("liukai@propersoft.cn");  // 发送到哪里
        mimeMessage.setSubject("javaMail");       // 主题
        mimeMessage.setText("hello ,Springboot 整合JavaMail");          // 正文
        mimeMessage.setSentDate(new Date());         // 发送日期
        javaMailSender.send(mimeMessage);
    }

    /**
     * 多人邮件,可抄送多人，秘密抄送多人
     * @throws Exception
     */
    public void sendMoreMail() throws Exception{
        //多人发送
        String users = "liukai@propersoft.cn,2748116048@qq.com"; // 此处应为前台传过来数据
        MimeMessage msg = javaMailSender.createMimeMessage();
        msg.setFrom(sender);
        Address[] internetAddressTo = new InternetAddress().parse(users);
        //要被设置为 TO, CC 或者 BCC，这里 CC 代表抄送、BCC 代表秘密抄送。举例：Message.RecipientType.TO
        msg.setRecipients(MimeMessage.RecipientType.TO, internetAddressTo);
        //  msg.setContent("测试标题", "utf-8");
        msg.setSubject("javaMail");
        msg.setText("hello SpringBoot 整合JavaMail 多人发送邮件");
        msg.setSentDate(new Date());
        javaMailSender.send(msg);
    }

    /**
     * 发送带有附件的邮件
     * @throws MessagingException
     */

    public void sendSingleFileMail() throws MessagingException {
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg,true);
        helper.setSubject("javaMail(带附件)");
        helper.setText("hello SpringBoot 整合JavaMail 多人发送邮件(带附件)");
        helper.setFrom(sender);
        helper.setTo("liukai@propersoft.cn");
        helper.addAttachment("", new File("D:\\"));
        helper.setSentDate(new Date());
        javaMailSender.send(msg);
    }
}
