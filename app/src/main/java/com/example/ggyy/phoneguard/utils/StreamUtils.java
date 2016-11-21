package com.example.ggyy.phoneguard.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 网络数据解析流
 * Created by T430 on 2016/11/21.
 */

public class StreamUtils {
    /**
     * 将输入流读取String后返回
     *
     * 建立readFromStream方法（静态 返回值String）  提供流InPutStream in
     *  1 建立ByteArrayOutputStream（） 新数组
     *  2 设置 int len = 0；
     *  3 byte[] buffer = new byte[1024];
     *  4 while ((len = in.read(buffer))!=-1){
     *      out.write(buffer,0,len)
     *  }
     *  Sring result = out.toString();
     *
     *  关闭流
     *  return result
     */
    public static String readFromStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int len = 0;
        byte[] buffer = new byte[1024];
        while ((len = inputStream.read(buffer)) != -1){
            outputStream.write(buffer,0,len);

        }
        String result = outputStream.toString();
        outputStream.close();
        inputStream.close();
        return result;
    }
}
