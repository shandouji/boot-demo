package com.klayiu.bootdemo.Utils;

import com.klayiu.bootdemo.entity.User;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * @author klayiu
 * @create 2020-04-08 10:38
 *
 *
 * Md5Util 加密
 */
public class Md5Util {
    private static final Logger LOGGER = LoggerFactory.getLogger(Md5Util.class);

    public static String md5(String content) {
        // 用于加密的字符
        char[] md5String = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        try {
            // 使用平台默认的字符集将md5String编码为byte序列,并将结果存储到一个新的byte数组中
            byte[] byteInput = content.getBytes(StandardCharsets.UTF_8);

            // 信息摘要是安全的单向哈希函数,它接收任意大小的数据,并输出固定长度的哈希值
            MessageDigest mdInst = MessageDigest.getInstance("MD5");

            // MessageDigest对象通过使用update方法处理数据,使用指定的byte数组更新摘要
            mdInst.update(byteInput);

            //摘要更新后通过调用digest() 执行哈希计算,获得密文
            byte[] md = mdInst.digest();

            //把密文转换成16进制的字符串形式
            int j = md.length;
            char[] str = new char[j*2];
            int k = 0;
            for (int i=0; i<j; i++) {
                byte byte0 = md[i];
                str[k++] = md5String[byte0 >>> 4 & 0xf];
                str[k++] = md5String[byte0 & 0xf];
            }
            // 返回加密后的字符串
            return new String(str);
        }catch (Exception e) {
            LOGGER.warn(e.getMessage(),e);
            return null;
        }

    }

    /**
     * 对密码进行盐值加密
     *
     * @param userName
     *              用户名
     * @param passWord
     *              密码
     * @return
     */
    public static String Md5Pwd(String userName,String passWord){
        System.out.println("原始密码值"+passWord);
        String hashAlgorithmName = "MD5";//加密方式
        Object crdentials = passWord;//密码原值
       // ByteSource salt = ByteSource.Util.bytes(userName);//以账号作为盐值
        Object salt = ByteSource.Util.bytes(userName);
        int hashIterations = 1024;//加密次数
        Object result = new SimpleHash(hashAlgorithmName, crdentials, salt, hashIterations);
        return result.toString();
    }
}
