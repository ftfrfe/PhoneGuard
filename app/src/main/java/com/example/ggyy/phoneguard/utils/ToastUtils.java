package com.example.ggyy.phoneguard.utils;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by T430 on 2016/11/21.
 */

public class ToastUtils {
    public static void showToast(final Activity activity, final String msg){
        if("main".equals(Thread.currentThread().getName())){
            Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
        }else{
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}