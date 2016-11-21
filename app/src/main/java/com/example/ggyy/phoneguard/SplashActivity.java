package com.example.ggyy.phoneguard;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    @Bind(R.id.tv_version)
    TextView tvVersion;
    @Bind(R.id.tv_progress)
    TextView tvProgress;
    @Bind(R.id.rl_root)
    RelativeLayout rlRoot;
    private int versionCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        tvVersion.setText("版本名"+getVersionName());
        CheckVersion();


    }

    private void CheckVersion() {
        //记录系统启动时间
        long timeStart = System.currentTimeMillis();
        //建立子线程 异步传送信息

        new Thread(){

            private HttpURLConnection conn = null;

            @Override
            public void run() {
                Message message = Message.obtain();
                //获取URL

                try {
                    URL url = new URL(getString(R.string.URL));
                    //建立HTTP连接
                    conn = (HttpURLConnection) url.getContent();
                    //conn 设置链接方式 设置 链接超时 超时读取 获取链接码
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(5000);
                    conn.setReadTimeout(5000);
                    int responseCode = conn.getResponseCode();
                    if (responseCode ==200){


                    }else {

                    }


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                super.run();
            }
        }.start();

    }

    private String getVersionName() {
        String versionName = null;
        PackageManager packageManager =  getPackageManager();

        try {

           PackageInfo info =  packageManager.getPackageInfo(getPackageName(),0);
           versionName =  info.versionName;
            versionCode = info.versionCode;
            System.out.println("aaaaaaaaaaaaaaaaaa"+versionName);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }
}
