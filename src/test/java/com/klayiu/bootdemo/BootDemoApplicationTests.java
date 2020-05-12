package com.klayiu.bootdemo;

import com.klayiu.bootdemo.Utils.StrUtil;
import com.klayiu.bootdemo.entity.User;
import com.klayiu.bootdemo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Date;
import java.util.List;

@SpringBootTest
class BootDemoApplicationTests {

    @Autowired
    UserService userService;

    @Autowired
    JavaMailSender javaMailSender;

    /**
     *  ��Ԫ���Ե�һЩ����
     */

    @Test
    void contextLoads() {


     /* List<User> list = userService.findAll();
        //֧��lemada ���ʽ
        list.forEach(user -> list.size());
        System.out.println(list.toString());*/

/*
        double aDouble = StrUtil.getDouble("null");
        System.out.println(aDouble);*/
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setSubject("Springboot 整合邮件发送");
        msg.setText("这是邮件内容");
        msg.setFrom("2748116048@qq.com");
        msg.setSentDate(new Date());
        msg.setTo("liukai@propersoft.cn");
        javaMailSender.send(msg);

    }

}
