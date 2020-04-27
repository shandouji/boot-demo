package com.klayiu.bootdemo.Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author klayiu
 * @create 2020-04-09 10:15
 * @Blog www.klayiu.com
 *
 *
 * Date工具类
 */
public class DateUtil {

    /**
     * 日期格式 yyyy-MM-dd
     */
    private static final String pattern_date = "yyyy-MM-dd";
    /**
     * 日期格式 yyyy-MM-dd HH:mm:ss
     */
    public static final String pattern_time = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期格式化
     * @param date
     *          日期
     * @return
     *          输出格式为
     */

    public static String formatDate(Date date){
        return formatDate(date,pattern_time);
    }


    /**
     * 日期格式化
     * @param date
     *        日期
     * @param pattern
     *        格式化类型
     * @return
     */

    public static String formatDate (Date date,String pattern){
        SimpleDateFormat sdf =new SimpleDateFormat(pattern);
        return  sdf.format(date);
    }


    /**
     * 根据日期返回中文星期几
     * @param date
     *        日期
     * @return
     *        例 ：2020/4/9 return 星期四
     */

    public static String getWeekDay(Date date){
        String weekday = "";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int week = calendar.get(7);
        --week;
        switch (week) {
            case 0:
                weekday = "星期日";
                break;
            case 1:
                weekday = "星期一";
                break;
            case 2:
                weekday = "星期二";
                break;
            case 3:
                weekday = "星期三";
                break;
            case 4:
                weekday = "星期四";
                break;
            case 5:
                weekday = "星期五";
                break;
            case 6:
                weekday = "星期六";
        }
        return weekday;
    }


    /**
     * 返回当前第几周
     * @return
     */
    public static int getCurrentWeek(){
        Calendar calendar = Calendar.getInstance();
        int week = calendar.get(7);
        --week;
        if (week == 0) {
            week = 7;
        }
        return week;
    }
}
