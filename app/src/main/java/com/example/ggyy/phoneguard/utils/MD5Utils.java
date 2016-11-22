package com.example.ggyy.phoneguard.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 盐加密
 * Created by T430 on 2016/11/22.
 */

public class MD5Utils {
    /**
     * 使用encode方法进行密码加密
     * 1.获取instance对象 由MessageDiest.getInstance 获取MD5算法对象
     * 2.利用获取的对象调取digest方法，将password的字节码数组传入（.getBytes()）
     * 3.建立一个StringBuffer（）数组对象。
     * 4.进行for循环 参数byte b：digest
     * 5.获取字节的第八位有效值 int i = b&0xff
     * 6.将整数转化为16进制 字符串 String hexString = Integer.toHexString
     * 7.判断 如果hexString对象的长度 是一位 则补0（.lenth < 2） 则hexString = "0" + hexString
     * 8.StringBuffer对象调用append方法
     * 9.返回Stringbuffer的toString()方法.
     * 10
     *
     */
    public static String encode(String password){
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            byte[] digest = instance.digest(password.getBytes());
            StringBuffer sb = new StringBuffer();
            for (byte b:digest){
                int i = b&0xff;
                String hexString = Integer.toHexString(i);
                if (hexString.length()<2){
                    hexString = "0" + hexString;

                }
                sb.append(hexString);

            }
            return sb.toString();


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return "";

    }

}
