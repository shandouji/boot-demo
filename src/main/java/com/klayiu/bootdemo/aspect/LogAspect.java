package com.klayiu.bootdemo.aspect;

import com.klayiu.bootdemo.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author 刘凯
 * @create 2020-04-21 11:40
 *
 * 用Aop 实现日志切面类
 *
 * 主要用于把相关信息打印在控制台上
 * 有单独方法对于日志信息进行存储 ,
 *
 * 参考原文：https://www.programcreek.com/java-api-examples/?api=org.aspectj.lang.JoinPoint
 *
 */

@Aspect
@Component
public class LogAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);

    private long startTime = System.currentTimeMillis();

    @Autowired
    HttpServletRequest request;




    //controller 层用于切入点
    @Pointcut("@annotation(com.klayiu.bootdemo.annotation.Log)")//@annotation用于匹配当前执行方法持有指定注解的方法；
    public void logAspect(){

    }


    /**
     *
     *  类名
     *  方法名
     *  客户端     Ip
     *  方法的访问时间(总共耗时)
     *  访问 Url
     *  操作事件 ( Log 注解中的value 值)
     *
     *
     *  当前登录人Id
     *  当前操作人姓名
     *
     *
     *
     *
     *
     * @param joinPoint
     * @param rvt
     */
    @AfterReturning(returning = "rvt", pointcut = "logAspect()")
    public void after(JoinPoint joinPoint,Object rvt){

        //用shiro 获取当前用户
        Subject subject = SecurityUtils.getSubject();
        User user =(User)subject.getSession().getAttribute("user");
        // 实现方案一
        //  ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        String targetName = joinPoint.getTarget().getClass().getName(); // 请求类名称
        LOGGER.info("类名称"+targetName);
        String methodName = joinPoint.getSignature().getName();// 获取请求方法
        LOGGER.info("类名称"+methodName);
        LOGGER.info("耗时：" + (System.currentTimeMillis() - startTime) + "毫秒");
        LOGGER.info("请求URL : " + request.getRequestURL().toString());
        LOGGER.info("HTTP 请求方法 : " + request.getMethod());
        // 0:0:0:0:0:0:0:1 出现ip 是因为 ipv6 ,localhost
        LOGGER.info("IP地址: " + request.getRemoteAddr());
        LOGGER.info("类名 + 方法 : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        LOGGER.info("参数 : " + Arrays.toString(joinPoint.getArgs()));
        // 需要判断用户是登录状态下新建的用户还是直接在页面注册用户
/*        if(!(targetName.equals("register"))){
            LOGGER.info("当前登录人:"+ user.getUserName()); //当前登录人
            LOGGER.info("当前登录人Id:"+ user.getId()); //当前登录人
        }*/

    }
}
