package com.klayiu.bootdemo.Utils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * @author klayiu
 * @create 2020-04-21 11:52
 *
 *
 *
 *  异常工具类
 *
 */
public class ExceptionUtil {

    /**
     * 将异常数组转成字符串
     * @return
     */
    public static String arrayConvertExceptionString(Exception e){
        StackTraceElement[] stackTraceArray = e.getStackTrace();
        StringBuilder detail = new StringBuilder();
        for (int i = 0; i < stackTraceArray.length; i++) {
            //255位，此处是考虑数据库相应字段的大小限制
           if((detail.toString()+stackTraceArray[i]).length() > 255){
                return detail.toString();
            }
            detail.append(stackTraceArray[i] + "\r\n");
        }
        return detail.toString();
    }


    /**
     * 在项目部署之后禁止使用 在控制台中打印 e.getStackTrace () , 会导致内存被填满，导致卡住
     * 个人建议用流将 e.getStackTrace() 打印在log 日志里。
     *
     * @param e
     * @return
     */

    public static String getStackTrace(Exception e){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        e.printStackTrace(new PrintStream(baos));
        String exception = baos.toString();
        //System.out.println("baos:" + exception);
        return exception;
    }
}
