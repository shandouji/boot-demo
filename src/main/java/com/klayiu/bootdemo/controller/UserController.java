package com.klayiu.bootdemo.controller;

import com.klayiu.bootdemo.Utils.*;
import com.klayiu.bootdemo.annotation.Log;
import com.klayiu.bootdemo.entity.User;
import com.klayiu.bootdemo.response.ResultBody;
import com.klayiu.bootdemo.service.UserService;
import com.klayiu.bootdemo.support.factory.LogTaskFactory;
import com.klayiu.bootdemo.support.manager.LogExeManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author klayiu
 * @create 2020-04-13 16:02
 *
 *
 * 用户操作
 *
 * MVC ,业务代码写在ServiceImpl (service 实现类里)
 */

@RestController
@RequestMapping("/user")
@Api(tags = "用户管理")
public class UserController {


    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @Autowired
    ExcelUtil excelUtil;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    MailUtil mailUtil;


    /**
     * 例子说明：
     *
     *  密码使用 shiro 进行加密
     *
     *
     *          * 在realm 里取出数据库密码之后，就应该是用shiro MD5 加密之后的，
     *          * 所以在注册用户的时候就应该对密码进行加密。
     *          *
     *          * 前台注册时候就应该是 加密之后传到后台
     * @param user
     * @return
     */

    @PostMapping("/register")
    @ApiOperation(value = "用户注册")
    @Log
    public ResultBody add(User user) throws Exception {
        // step 1 需要校验手机号码是不是符合规则
        // step 2 验证邮箱是不是符合规则, 后面需要根据邮箱进行密码找回功能。
        user.setPassWord(Md5Util.Md5Pwd(user.getUserName(),user.getPassWord()));
        boolean b = ValidateUtil.validateMobile(user.getTelePhone());
        if(!b){
            return new ResultBody("请填写正确的电话号码");
        }
        if(userService.insert(user)){
            long time = System.currentTimeMillis();
            System.out.println(time);
            mailUtil.sendSingleFileMail();
          //  redisUtil.set("name","klayiu");
            System.out.println(System.currentTimeMillis()-time);
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.registerLog(String.valueOf(user.getId()),(short) 0, "用户添加成功"));
        }else{
            LogExeManager.getInstance().executeLogTask(LogTaskFactory.registerLog(String.valueOf(user.getId()), (short) 0, "用户添加失败"));
        }
        return new ResultBody(user);
    }

    /**
     *
     * 登录情况简单说明
     *
     * @param userName
     * @param passWord
     * @return
     */

    @GetMapping("/login")
    @ApiOperation(value = "用户登录")
    @Log
    public ResultBody login(String userName,String passWord){
        Subject subject = SecurityUtils.getSubject();
        //subject.isPermitted()
        String pwd = Md5Util.Md5Pwd(userName, passWord);
        LOGGER.info("前台密码加密之后"+pwd);
        UsernamePasswordToken token = new UsernamePasswordToken(userName,passWord);
        token.setRememberMe(true);
            try {
                subject.login(token);
                LOGGER.info("身份认证成功");
            } catch (AuthenticationException e) {
                LOGGER.info("身份认证失败"+e.getCause() + e.getMessage()+ExceptionUtil.getStackTrace(e));
            }
        return new ResultBody();
    }


    @GetMapping("/logOut")
    @ApiOperation(value = "用户登出")
    @Log
    public ResultBody logOut(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new ResultBody();
    }

    @GetMapping("/backPassWord")
    public ResultBody  backPassWord(){
        LOGGER.info("-----------通过邮件找回密码 ----------");
       // Object o = redisUtil.get("name");

        redisUtil.removeKey("name");
       // System.out.println("redis 数据" +o.toString());
        return new ResultBody();
    }



    /**
     * @PathVariable Spirng3.0 新增 url 占位符 , 增强对resful 风格的友好支持
     * @PathVariable Integer start,@PathVariable Integer limit
     * @param
     * @param
     * @return
     */

    @GetMapping("/findAll/{start}/{limit}")
    @ApiOperation(value = "查询用户列表",notes = "Get获取用户注册过的信息列表")
    public ResultBody findAll(){
        List<User> list = userService.findAll();
        //支持lemada 表达式
        list.forEach(user -> user.setPassWord(null));
        //取出第几个数据
        System.out.println(list.toString());
        return new ResultBody(list);
    }


    @GetMapping("/findById")
    @ApiOperation(value = "根据Id查询用户")
    public ResultBody findByUserId(String id){
        User user = userService.getUserById(id);
        return new ResultBody(user);
    }



    @DeleteMapping("/delete")
    @ApiOperation(value = "根据Id删除用户")
    public ResultBody deleteById(@RequestParam(value = "id",required = true) String id){
        userService.deleteById(id);
        return new ResultBody();
    }


    @PutMapping("/update")
    @ApiOperation(value = "用户修改")
    public ResultBody update(@RequestParam(value = "id",required = true) String id){

        return new ResultBody();
    }




    /**
     * 导出excel 例子
     *
     * @return
     */
    @GetMapping("/excel")
    @ApiOperation(value = "导出Excel")
    public void exporeExcel(HttpServletResponse response){
        // 查询所有默认导出
        List<User> list = userService.findAll();
        //支持lemada 表达式
      //  list.forEach(user -> user.setPassWord(null));
      //  System.out.println(list.toString());
        try {
            excelUtil.writeExcel(response,list,"字段"+System.currentTimeMillis(),"sheet1", User.class);
        }catch (Exception e){
            ExceptionUtil.getStackTrace(e);
            //e.printStackTrace();
        }
    }
}
