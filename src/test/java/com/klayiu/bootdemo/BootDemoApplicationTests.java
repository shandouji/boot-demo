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
     *  单元测试的一些方法
     */

    @Test
    void contextLoads() {


     /* List<User> list = userService.findAll();
        //支持lemada 表达式
        list.forEach(user -> list.size());
        System.out.println(list.toString());*/


        double aDouble = StrUtil.getDouble("null");
        System.out.println(aDouble);

    }

}
