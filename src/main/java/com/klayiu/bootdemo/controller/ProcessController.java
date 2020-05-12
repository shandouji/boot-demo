package com.klayiu.bootdemo.controller;

import com.klayiu.bootdemo.annotation.Log;
import com.klayiu.bootdemo.response.ResultBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

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
 *  职工张三 申请 ————> 部门经理审批 ————> 总经理审批 —————> 完成
 */

@RestController
@RequestMapping("/process")
@Api(tags = "流程demo")
public class ProcessController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessController.class);

   // @Autowired
   // ActivitiConfig activitiConfig;

    private ProcessEngine processEngine= ProcessEngines.getDefaultProcessEngine();


    @GetMapping("/deploy")
    @ApiOperation(value = "流程部署根目录(classpath)方式")
    public ResultBody deployWithClassPath(){
       /* InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("");
        ZipInputStream zip = new ZipInputStream(inputStream);*/
        Deployment deployment = processEngine.getRepositoryService() //部署相关Service
            .createDeployment() // 创建部署
            .addClasspathResource("") // 加载资源文件
            .addClasspathResource("") //加载资源文件
            .name("职工请假流程") // 流程名称
            .deploy(); // 部署
        LOGGER.info("流程部署Id"+deployment.getId());
        LOGGER.info("流程不是Name"+deployment.getName());
        return new ResultBody();
    }


/*    @PostMapping("/zip")
    @ApiOperation(value = "流程部署ZIP 部署方式")
    @Log
    public ResultBody deployWithZip(){
        InputStream inputStream = this.getClass() //取得当前的class对象
                .getClassLoader() //获取类加载器
                .getResourceAsStream(""); //获取指定文件资源流
        ZipInputStream zipInputStream=new ZipInputStream(inputStream); // 实例化zip输入流
        Deployment deployment=processEngine.getRepositoryService() // 获取部署相关Service
                .createDeployment() // 创建部署
                .addZipInputStream(zipInputStream) // 添加zip输入流
                .name("职工请假流程") // 流程名称
                .deploy(); // 部署
        LOGGER.info("流程部署ID:"+deployment.getId());
        LOGGER.info("流程部署Name:"+deployment.getName());
        return new ResultBody();
    }*/


    @PostMapping("/startProcess")
    @ApiOperation(value = "启动一个流程实例")
    @Log
    public ResultBody startProcessInstance(){
        ProcessInstance pi = processEngine.getRuntimeService() // 运行时service
                .startProcessInstanceByKey(""); //根据Key值开启一个流程
        LOGGER.info("流程实例Id" + pi.getId());
        LOGGER.info("流程定义Id" +pi.getProcessDefinitionId());
        return new ResultBody();
    }


    @GetMapping("/taskQuery")
    @ApiOperation(value = "查询用户任务的列表")
    @Log
    public ResultBody taskQuery(){
        List<Task> list = processEngine.getTaskService() //任务相关service
                .createTaskQuery() // 创建查询任务
                .taskAssignee("") // 根据某个人来查询任务
                .list();
        for (Task task : list) {
            LOGGER.info("任务Id"+task.getId());
            LOGGER.info("任务名称"+task.getName());
            LOGGER.info("任务委派人"+task.getAssignee());
            LOGGER.info("任务创建时间"+task.getCreateTime());
            LOGGER.info("流程实例ID"+task.getProcessInstanceId());
        }
        return new ResultBody();
    }




    @PostMapping("/task")
    @ApiOperation(value = "完成任务")
    @Log
    public ResultBody completeTask(String taskId){
        processEngine.getTaskService()
                .complete(taskId);
        return new ResultBody();
    }



    @GetMapping("/queryHistoryTask")
    @ApiOperation(value = "查询历史任务")
    @Log
    public ResultBody queryHistoryTask(String processInstanceId){
        List<HistoricTaskInstance> list=processEngine.getHistoryService() // 历史相关Service
                .createHistoricTaskInstanceQuery() // 创建历史任务实例查询
                .processInstanceId(processInstanceId) // 用流程实例id查询
                .finished() // 查询已经完成的任务
                .list();
        for(HistoricTaskInstance hti:list){
            LOGGER.info("任务ID:"+hti.getId());
            LOGGER.info("流程实例ID:"+hti.getProcessInstanceId());
            LOGGER.info("任务名称："+hti.getName());
            LOGGER.info("办理人："+hti.getAssignee());
            LOGGER.info("开始时间："+hti.getStartTime());
            LOGGER.info("结束时间："+hti.getEndTime());
        }
        return new ResultBody();
    }


    @GetMapping("/queryHistoryAct")
    @ApiOperation(value = "查询历史活动")
    @Log
    public ResultBody queryActInstanceList(){
        List<HistoricActivityInstance> list = processEngine.getHistoryService() //历史相关service
                .createHistoricActivityInstanceQuery()
                .processInstanceId("")
                .finished()
                .list();
        for (HistoricActivityInstance history : list) {
            LOGGER.info("任务ID:"+history.getId());
            LOGGER.info("流程实例ID:"+history.getProcessInstanceId());
            LOGGER.info("任务名称："+history.getActivityName());
            LOGGER.info("办理人："+history.getAssignee());
            LOGGER.info("开始时间："+history.getStartTime());
            LOGGER.info("结束时间："+history.getEndTime());
        }
        return  new ResultBody();
    }



    @GetMapping("/queryProcessStatus")
    @ApiOperation(value = "查询流程状态")
    @Log
    public ResultBody queryStatus(){
        ProcessInstance processInstance = processEngine.getRuntimeService()
                .createProcessInstanceQuery()
                .processInstanceId("")
                .singleResult();
        if(processInstance != null ){
            LOGGER.info("流程进行中");
        }else{
            LOGGER.info("流程已经结束");
        }
        return new ResultBody();
    }
}
