package com.klayiu.bootdemo.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author klayiu
 * @create 2020-04-08 10:09
 * @Blog www.klayiu.com
 *
 * 封装redis 工具类
 * 注意：
 * Springboot 2.2.4 版本之后不支持jedis ,支持lutt , 现在在这里将lutt 改为redis
 *
 * 如何更改
 *
 * ip和端口号自行更改
 */
public class RedisUtil {

    private static final String  ip = "localhost"; //ip
    private static final String port = "6379" ; // 端口号, redis 默认端口为6379

    private final static Logger logger = LoggerFactory.getLogger(RedisUtil.class);




}
