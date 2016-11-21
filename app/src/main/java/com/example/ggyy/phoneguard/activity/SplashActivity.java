package com.example.ggyy.phoneguard.activity;


import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ggyy.phoneguard.R;
import com.example.ggyy.phoneguard.utils.StreamUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    private static final int CODE_UPDATE_DIALOG = 0;
    private static final int CODE_ENTER_HOME = 1;
    private static final int CODE_WRONG_URL = 2;
    private static final int CODE_NET_ERROR = 3;
    private static final int CODE_JSON_ERROR = 4;
    @Bind(R.id.tv_version)
    TextView tvVersion;
    @Bind(R.id.tv_progress)
    TextView tvProgress;
    @Bind(R.id.rl_root)
    RelativeLayout rlRoot;
    private int versionCode;
    private String mversionName;
    private int mversionCode;
    private String desc;
    private String versionName;
    private Activity act = this;
    private Handler mhandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case CODE_UPDATE_DIALOG:
                    showUpDateDilog();
                case CODE_ENTER_HOME:
                    enterHome();
                case CODE_NET_ERROR:




            }


            super.handleMessage(msg);
        }
    };

    private void enterHome() {
    }

    private void showUpDateDilog() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        tvVersion.setText("版本名"+getVersionName());
        versionCode = getVersionCode();
        CheckVersion();


    }


    private void CheckVersion() {
        //记录系统启动时间
        final long timeStart = System.currentTimeMillis();
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
                        //建立输入流解析 使用工具xUtils  建立StreamUtils
                        InputStream inputStream = conn.getInputStream(); //获取流
                        String result = StreamUtils.readFromStream(inputStream); // 获取字符串
                        JSONObject json = new JSONObject(result); //将字符串结果转化为json对象
                        mversionName = json.getString("versionName");
                        mversionCode = json.getInt("versionCode");
                        desc = json.getString("description");
                        if (mversionCode>versionCode){
                            
                            message.what = CODE_UPDATE_DIALOG;
                        }else
                        {
                            message.what = CODE_ENTER_HOME;
                        }


                    }


                } catch (MalformedURLException e) {
                    //URL 错误异常
                    message.what = CODE_WRONG_URL;
                    e.printStackTrace();
                } catch (IOException e) {
                    message.what = CODE_NET_ERROR;
                    e.printStackTrace();
                } catch (JSONException e) {
                    message.what = CODE_JSON_ERROR;
                    e.printStackTrace();
                }
                finally {
                    //获取当前时间 等待机制
                  long timeEnd =  System.currentTimeMillis();
                    if (timeEnd - timeStart < 2000){
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }



                super.run();
            }
        }.start();

    }

    private String getVersionName() {

        PackageManager packageManager =  getPackageManager();


        try {

           PackageInfo info =  packageManager.getPackageInfo(getPackageName(),0);
           versionName =  info.versionName;
            versionCode = info.versionCode;
            System.out.println("aaaaaaaaaaaaaaaaaa"+ versionName+versionCode);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    public int getVersionCode() {
        try {
            versionCode = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }
}
