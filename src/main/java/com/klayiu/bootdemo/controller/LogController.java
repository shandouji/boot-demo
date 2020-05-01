package com.klayiu.bootdemo.controller;

import com.klayiu.bootdemo.annotation.Log;
import com.klayiu.bootdemo.entity.AuthAccountLog;
import com.klayiu.bootdemo.response.ResultBody;
import com.klayiu.bootdemo.service.LogService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 刘凯
 * @create 2020-04-28 16:07
 */
@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    LogService logService;

    @ApiOperation(value = "根据Id 查询日志")
    @Log
    @GetMapping("/logById")
    public ResultBody getLogById(Integer id){
        AuthAccountLog log = logService.findById(id);
        return new ResultBody(log);
    }
}
