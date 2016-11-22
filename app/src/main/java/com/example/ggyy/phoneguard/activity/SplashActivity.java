package com.example.ggyy.phoneguard.activity;


import android.app.Activity;
import android.content.Intent;
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
import com.example.ggyy.phoneguard.utils.ToastUtils;

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
    private Handler mhandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case CODE_UPDATE_DIALOG:
                    showUpDateDilog();
                    break;
                case CODE_ENTER_HOME:
                    enterHome();
                    break;
                case CODE_NET_ERROR:
                    ToastUtils.showToast(act, "网络异常");
                    enterHome();
                    break;
                case CODE_JSON_ERROR:
                    ToastUtils.showToast(act, "数据解析异常");
                    enterHome();
                    break;
                case CODE_WRONG_URL:
                    ToastUtils.showToast(act, "URL错误");
                    enterHome();
                    break;
                default:
                    break;

            }

        }
    };

    private void enterHome() {
         Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
        act.finish();


    }

    private void showUpDateDilog() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        tvVersion.setText("版本名" + getVersionName());
        versionCode = getVersionCode();
        // ToastUtils.showToast(act,"aaaa");
        CheckVersion();


    }


    private void CheckVersion() {
        //记录系统启动时间
        final long timeStart = System.currentTimeMillis();
        //建立子线程 异步传送信息
        System.out.println("建立子线程");

        new Thread() {

            private HttpURLConnection conn = null;
            Message message = Message.obtain();
            @Override
            public void run() {

                //获取URL

                try {
                    System.out.println("获取网址前");
                    URL url = new URL(getString(R.string.URL));
                    //建立HTTP连接
                    System.out.println("获取网址后" + url);
                    conn = (HttpURLConnection) url.openConnection();
                    //conn 设置链接方式 设置 链接超时 超时读取 获取链接码
                    System.out.println("设置conn");
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(5000);
                    conn.setReadTimeout(5000);
                    conn.connect();
                    System.out.println("设置conn后");
                    int responseCode = conn.getResponseCode();
                    System.out.println("建立链接，返回码");
                    if (responseCode == 200) {
                        //建立输入流解析 使用工具xUtils  建立StreamUtils
                        System.out.println("联网成功");
                        InputStream inputStream = conn.getInputStream(); //获取流
                        String result = StreamUtils.readFromStream(inputStream); // 获取字符串
                        JSONObject json = new JSONObject(result); //将字符串结果转化为json对象
                        mversionName = json.getString("versionName");
                        mversionCode = json.getInt("versionCode");
                        desc = json.getString("description");
                        if (mversionCode > versionCode) {

                            message.what = CODE_UPDATE_DIALOG;
                        } else {
                            message.what = CODE_ENTER_HOME;
                        }


                    }
                    else {
                        System.out.println("没联网的情况下进入主机面");
                        message.what = CODE_ENTER_HOME;
                    }


                } catch (MalformedURLException e) {
                    //URL 错误异常
                    message.what = CODE_WRONG_URL;
                    System.out.println("URL 错误异常");
                    e.printStackTrace();
                } catch (IOException e) {
                    message.what = CODE_NET_ERROR;
                    System.out.println("网络异常");
                    e.printStackTrace();
                } catch (JSONException e) {
                    message.what = CODE_JSON_ERROR;
                    System.out.println("json 解析异常");
                    e.printStackTrace();
                } finally {
                    //获取当前时间 等待机制
                    System.out.println("进入finally等待1S");
                    long timeEnd = System.currentTimeMillis();
                    if (timeEnd - timeStart < 1000) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        mhandler.sendMessage(message);
                        if (conn != null) {
                            conn.disconnect();
                        }
                    }

                }


            }
        }.start();

    }

    private String getVersionName() {

        PackageManager packageManager = getPackageManager();


        try {

            PackageInfo info = packageManager.getPackageInfo(getPackageName(), 0);
            versionName = info.versionName;
            versionCode = info.versionCode;
            System.out.println("aaaaaaaaaaaaaaaaaa" + versionName + versionCode);

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
