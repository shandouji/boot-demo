package com.klayiu.bootdemo;

import com.klayiu.bootdemo.Utils.StrUtil;
import com.klayiu.bootdemo.entity.User;
import com.klayiu.bootdemo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BootDemoApplicationTests {

    @Autowired
    UserService userService;

    /**
     *  ��Ԫ���Ե�һЩ����
     */

    @Test
    void contextLoads() {


     /* List<User> list = userService.findAll();
        //֧��lemada ���ʽ
        list.forEach(user -> list.size());
        System.out.println(list.toString());*/


        double aDouble = StrUtil.getDouble("null");
        System.out.println(aDouble);

    }

}
