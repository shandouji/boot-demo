package com.klayiu.bootdemo.Utils;

/**
 * @author klayiu
 * @create 2020-04-08 17:23
 *
 * 封装字符串处理工具
 * v1.0 版本
 *
 * 注意: 功能不完善，暂时不强制使用
 * 同时集成apache commons langUtils
 */
public class StrUtil {

    /**
     * 判断 str 是否为空,如果为true则为空 , str 为 " "也视为空字符
     * @param str
     * @return
     */
    public static boolean isBlank (String str){
        boolean b = true;
        if (str == null) {
            b = true;
        } else {
            int strLen = str.length();
            if (strLen == 0) {
                b = true;
            }

            for (int i = 0; i < strLen; i++) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    b = false;
                    break;
                }
            }
        }
        return b;
    }

    /**
     * 判断字符串是否空,如果为true,则为空
     * @param str
     * @return
     */

    public static boolean isEmpty(String str){
        if(str == null || str.trim().length() ==0){
            return true;
        }
        return false;
    }


    /**
     * null 转换为 " "
     * @param source
     *         空字符串
     * @return 转换后的字符串
     */

    public static String nullConvertString(String source){

        return source != null ? source : "";
    }


    /**
     *
     * 大写英文字母转换为小写
     * @param str
     *        字符串
     * @return
     */

    public static String toLowerStr(String str){
        int i = 0;
        char ch;
        String strOut = new String();
        int len = str.length();
        while(i < len){
            ch = str.charAt(i);
            if (ch >= 'A' && ch <= 'Z') {
                ch = (char) (ch - 'A' + 'a');
            }
            strOut += ch;
            i++;
        }
        return strOut;
    }

    /**
     * 将小写字母全部转换为大写
     * @param str
     *        字符串
     * @return
     */

    public static String toUpperStr(String str){
        int i = 0;
        char ch ;
        String strOut = new String ();
        int len = str.length();
        while (i < len) {
            ch = str.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                ch = (char) (ch - 'a' + 'A');
            }
            strOut += ch;
            i++;
        }
        return strOut;
    }
}
