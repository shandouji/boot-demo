package com.klayiu.bootdemo.support.manager;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @author 刘凯
 * @create 2020-04-11 20:38
 *
 *  日志操作任务运行管理器 单例
 */
public class LogExeManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogExeManager.class);

    private static final int LOG_DELAY_TIME = 10;


    private ThreadFactory threadFactory = new ThreadFactoryBuilder()
            .setNameFormat("log-pool-%d").build();

    private ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(20, threadFactory);

    private static LogExeManager logExeManager = new LogExeManager();

    private LogExeManager() {

    }

    public static LogExeManager getInstance() {

        return logExeManager;
    }

    public void executeLogTask(TimerTask timerTask) {
        LOGGER.info("进入线程池工作");
        executor.schedule(timerTask, LOG_DELAY_TIME, TimeUnit.MICROSECONDS);
    }
}
