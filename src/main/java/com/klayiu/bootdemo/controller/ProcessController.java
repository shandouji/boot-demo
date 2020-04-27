package com.klayiu.bootdemo.controller;

import com.klayiu.bootdemo.annotation.Log;
import com.klayiu.bootdemo.response.ResultBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author klayiu
 * @create 2020-04-23 15:15
 * @ 博客 : klayiu.com
 *
 *  关于流程 demo ,主要演示从发布、到结束流程的一个实例
 *
 *  例子 :
 *
 *  职工请假审批
 *
 *  职工张三 申请 ————> 组长审批 ————> 部门经理审批 ————> 总经理审批 ————> 审批通过 —————> 完成
 */

@RestController
@RequestMapping("/process")
@Api(tags = "流程demo")
public class ProcessController {

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    TaskService taskService;


    @PostMapping("/startProcess")
    @ApiOperation(value = "启动一个流程实例")
    @Log
    public ResultBody startProcessInstance(){

        return new ResultBody();
    }


    @GetMapping("/taskQuery")
    @ApiOperation(value = "查询用户任务的列表")
    @Log
    public ResultBody taskQuery(){

        return new ResultBody();
    }

    @PostMapping("/task")
    @ApiOperation(value = "完成任务")
    @Log
    public ResultBody completeTask(String taskId){
        taskService.complete(taskId);
        return new ResultBody();
    }



    @GetMapping("/queryHistoryTask")
    @ApiOperation(value = "查询历史任务")
    @Log
    public ResultBody queryHistoryTask(){

        return new ResultBody();
    }
}
