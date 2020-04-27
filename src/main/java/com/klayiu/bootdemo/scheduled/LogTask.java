package com.klayiu.bootdemo.scheduled;

import com.klayiu.bootdemo.service.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author 刘凯
 * @create 2020-04-23 15:11
 *
 * 为避免日志文件太大,定时删除日志信息
 *
 * 定期清理 , 可根据自行情况设置时间
 *
 */
@Component
@EnableScheduling
public class LogTask {

    private static final Logger logger = LoggerFactory.getLogger(LogTask.class);

    @Autowired
    LogService logService;

    @Scheduled(cron = "* * 1 1/3 * *")
    public void cleanLog(){
        logger.info("--------删除日志开始-------------");
        logService.deleteAll();
        logger.info("--------删除日志结束-------------");
    }

}
