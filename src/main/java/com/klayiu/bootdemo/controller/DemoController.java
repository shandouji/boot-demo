package com.klayiu.bootdemo.controller;

import com.klayiu.bootdemo.config.DruidConfig;
import com.klayiu.bootdemo.entity.User;
import com.klayiu.bootdemo.response.ResultBody;
import com.klayiu.bootdemo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 刘凯
 * @create 2020-04-11 9:02
 *
 * example demo
 */

@RestController
@RequestMapping("/test")
@Api(tags = "Demo 测试类")
public class DemoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    UserService userService;

    @ApiOperation(value = "根据Id查询用户列表")
    @GetMapping("/user")
    public User getUserbyId(String id){
        User user = userService.getUserById(id);
        // 输出内容在控制台上
        LOGGER.info("输出User"+user.toString());
        return user;
    }

    /**
     * 定时导出日志
     *
     * 写个小例子 , 真实项目中未必能用到 ,
     * 可直接去关系型数据库或者非关系型数据库进行查询
     * @return
     */

    @GetMapping("/")
    @ApiOperation(value = "定时导出日志demo")
    public ResultBody exportLog(){

        return new ResultBody();
    }



}
