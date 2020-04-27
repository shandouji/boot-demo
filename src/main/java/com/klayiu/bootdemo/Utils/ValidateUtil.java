package com.klayiu.bootdemo.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author klayiu
 * @create 2020-04-09 13:03
 *
 *
 *
 * 验证工具类(根据正则表达式)
 */
public class ValidateUtil {

    public static final Pattern POSTCODE_PATTERN = Pattern.compile("^\\d{6}$");
    public static final String PHONE_REGEXP = "^(?:0[0-9]{2,3}[-//s]{1}|//(0[0-9]{2,4}//))[0-9]{6,8}$|^[1-9]{1}[0-9]{5,7}$|^[1-9]{1}[0-9]{10}$";


    /**
     * 校验手机号
     * @param str
     * @return
     */

    public static boolean validateMobile(String str){
        if(str == null || str.trim().length() == 0 ){
            return false;
        }
        Pattern pattern = Pattern.compile("^(13|14|15|17|18)[0-9]{9}$");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }


    /**
     * 验证邮政编码是不是有效
     * @param postCode
     * @return
     */

    public static boolean validatePostCode(String postCode){
        if(StrUtil.isEmpty(postCode)){
            return false;
        }
        Matcher m = POSTCODE_PATTERN.matcher(postCode);
        return m.matches();
    }
}
