package com.klayiu.bootdemo.exception;


import com.klayiu.bootdemo.response.ResultBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 刘凯
 * @create 2020-04-11 9:23
 *
 *
 * 全局异常处理
 * 主要用到 这两个注解
 * @ControllerAdvice
 * @ExceptionHandler
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class); //实例化日志


    /**
     * 业务类异常
     * @return
     */
    @ExceptionHandler(BaseErrorException.class)
    public ResultBody bussinessException(BaseErrorException ex, HttpServletRequest request){
        ErrorResponse errorCode = new ErrorResponse(ex,request.getRequestURI());
        logger.info("异常日志"+errorCode);
        return new ResultBody();
    }


    /**
     * 处理其它类异常
     * @param e
     * @param request
     * @return
     *        应返回错误状态码
     */
/*    @ExceptionHandler(Exception.class)
    public ResultBody otherException(Exception e,HttpServletRequest request){
        logger.info("未知异常:原因是"+e + request.getRequestURI());
        System.out.println(e.getStackTrace().toString());
        System.out.println(e.getCause().toString());
        return new ResultBody<>();
    }*/

}
