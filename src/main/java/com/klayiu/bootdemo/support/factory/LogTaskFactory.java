package com.klayiu.bootdemo.support.factory;

import com.klayiu.bootdemo.entity.AuthAccountLog;
import com.klayiu.bootdemo.entity.AuthOperationLog;
import com.klayiu.bootdemo.mapper.AccountLogMapper;
import com.klayiu.bootdemo.mapper.OperationLogMapper;
import com.klayiu.bootdemo.support.SpringContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.TimerTask;

/**
 * @author 刘凯
 * @create 2020-04-11 20:38
 *
 *
 * 日志操作任务工厂
 */
public class LogTaskFactory {

    @Autowired


    private static final Logger LOGGER = LoggerFactory.getLogger(LogTaskFactory.class);
//    private static OperationLogMapper operationLogMapper = SpringContextHolder.getBean(OperationLogMapper.class);
    private static AccountLogMapper accountLogMapper = SpringContextHolder.getBean(AccountLogMapper.class);

    private LogTaskFactory() {

    }

/*
    public static TimerTask loginLog(String userId, String ip, Short succeed, String message) {
        return new TimerTask() {
            @Override
            public void run() {
                try {
                    AuthAccountLog accountLog = LogFactory.createAccountLog(userId, "用户登录日志", ip, succeed, message);
                    //accountLogMapper.insertSelective(accountLog);
                } catch (Exception e) {
                    LOGGER.error("写入用户登录日志异常", e.getCause().getMessage());
                }
            }
        };
    }

    public static TimerTask exitLog(String userId, String ip, Short succeed, String message) {
        return new TimerTask() {
            @Override
            public void run() {
                try {
                    AuthAccountLog accountLog = LogFactory.createAccountLog(userId, "用户退出日志", ip, succeed, message);
                 //   accountLogMapper.insertSelective(accountLog);
                } catch (Exception e) {
                    LOGGER.error("写入用户退出日志异常", e.getCause().getMessage());
                }
            }
        };
    }
*/

    public static TimerTask registerLog(String userId, Short succeed, String message) {
        LOGGER.info("进入 registerLog 方法");
        return new TimerTask() {
            @Override
            public void run() {
                try {
                    AuthAccountLog accountLog = LogFactory.createAccountLog(userId, "用户注册日志", succeed, message);
                    accountLogMapper.insertSelective(accountLog);
                } catch (Exception e) {
                    LOGGER.error("写入用户注册日志异常", e.getCause().getMessage());
                }
            }
        };
    }

  /*  public static TimerTask bussinssLog(String userId, String api, String method, Short succeed, String message) {
        return new TimerTask() {
            @Override
            public void run() {
                try {
                    AuthOperationLog operationLog = LogFactory.createOperationLog(userId, "业务操作日志", api, method, succeed, message);
                  //  operationLogMapper.insertSelective(operationLog);
                } catch (Exception e) {
                    LOGGER.error("写入业务操作日志异常", e.getCause().getMessage());
                }
            }
        };
    }

    public static TimerTask exceptionLog(String userId, String api, String method, Short succeed, String message) {
        return new TimerTask() {
            @Override
            public void run() {
                try {
                    AuthOperationLog exceptionLog = LogFactory.createOperationLog(userId, "业务异常日志", api, method, succeed, message);
                 //   operationLogMapper.insertSelective(exceptionLog);
                } catch (Exception e) {
                    LOGGER.error("写入业务异常日志异常", e.getCause().getMessage());
                }
            }
        };
    }*/

}
