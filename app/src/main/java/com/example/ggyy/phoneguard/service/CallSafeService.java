package com.example.ggyy.phoneguard.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class CallSafeService extends Service {
    public CallSafeService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("安全通话服务开启");
    }

    @Override
    public IBinder onBind(Intent intent) {
       return null;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("安全通话服务关闭");
    }
}
