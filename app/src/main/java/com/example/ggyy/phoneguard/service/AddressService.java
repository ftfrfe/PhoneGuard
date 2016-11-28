package com.example.ggyy.phoneguard.service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.WindowManager;

public class AddressService extends Service {
    private TelephonyManager tm;
    private MyListener listener;
    private OutCallReceiver receiver;
    private WindowManager mWM;
    private View view;
    private SharedPreferences mPref;

    private int startX;
    private int startY;
    private WindowManager.LayoutParams params;
    private int winWidth;
    private int winHeight;

    @SuppressLint("ServiceCast")
    @Override
    public void onCreate() {
        super.onCreate();
        mPref = getSharedPreferences("config", MODE_PRIVATE);
       tm = (TelephonyManager) getSystemService(TELECOM_SERVICE);
        System.out.println("号码获取服务开启了");
    }

    public AddressService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    class MyListener {
    }

    private class OutCallReceiver {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("号码获取服务关闭了");
    }
}
