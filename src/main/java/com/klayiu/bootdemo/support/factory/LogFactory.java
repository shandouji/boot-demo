package com.klayiu.bootdemo.support.factory;

import com.klayiu.bootdemo.entity.AuthAccountLog;
import com.klayiu.bootdemo.entity.AuthOperationLog;

import java.util.Date;

/**
 * @author 刘凯
 * @create 2020-04-11 20:38
 *
 *
 * 日志对象工厂
 */
public class LogFactory {

    private LogFactory() {

    }


    /**
     * 用户操作日志
     * @param userId
     * @param logName
     * @param succeed
     * @param message
     * @return
     */
    public static AuthAccountLog createAccountLog(String userId, String logName,Short succeed, String message) {
        AuthAccountLog accountLog = new AuthAccountLog();
        accountLog.setUserId(userId);
        accountLog.setLogName(logName);
     //   accountLog.setIp(ip);
        accountLog.setSucceed(succeed);
        accountLog.setMessage(message);
        accountLog.setCreateTime(new Date());
        return accountLog;
    }


    /**
     * 业务日志
     * @param userId
     * @param logName
     * @param api
     * @param method
     * @param succeed
     * @param message
     * @return
     */
    public static AuthOperationLog createOperationLog(String userId, String logName, String api, String method, Short succeed, String message) {
        AuthOperationLog operationLog = new AuthOperationLog();
        operationLog.setUserId(userId);
        operationLog.setLogName(logName);
        operationLog.setApi(api);
        operationLog.setMethod(method);
        operationLog.setSucceed(succeed);
        operationLog.setMessage(message);
        operationLog.setCreateTime(new Date());
        return operationLog;
    }

}
