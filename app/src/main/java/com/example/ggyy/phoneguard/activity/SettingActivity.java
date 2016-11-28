package com.example.ggyy.phoneguard.activity;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.ggyy.phoneguard.R;
import com.example.ggyy.phoneguard.service.AddressService;
import com.example.ggyy.phoneguard.utils.ServiceStatusUtils;
import com.example.ggyy.phoneguard.view.SettingItemView;


public class SettingActivity extends Activity {

    private static final String[] items = {"半透明", "活力橙", "卫士蓝", "金属灰", "苹果绿"};
    private SharedPreferences mPref;
    private SettingItemView sivUpdate;//设置升级选项框
    private SettingItemView sivAddress;// 设置地址归属

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_setting);
        setContentView(R.layout.activity_setting);
        mPref = getSharedPreferences("config", MODE_PRIVATE);
        initUpdateView();
        initAddressView();
        initAddressStyle();
        initAddressLocation();
        initBlackView();

    }

    private void initBlackView() {

    }

    private void initAddressLocation() {

    }

    private void initAddressStyle() {

    }

    /**
     * 初始化归属地开关
     */
    private void initAddressView() {
        sivAddress = (SettingItemView) findViewById(R.id.siv_address);
        boolean serviceRunning = ServiceStatusUtils.isServiceRunning(this, "com.example.ggyy.phoneguard.service.AddressService");
        if (serviceRunning) {
            sivAddress.setChecked(true);
        } else {
            sivAddress.setChecked(false);
        }

        sivAddress.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sivAddress.isChecked()) {
                    sivAddress.setChecked(false);
                    stopService(new Intent(SettingActivity.this, AddressService.class));

                } else {
                    sivAddress.setChecked(true);
                    startService(new Intent(SettingActivity.this, AddressService.class));
                }
            }
        });

    }

    private void initUpdateView() {
        sivUpdate = (SettingItemView) findViewById(R.id.siv_update);
        boolean autoUpdate = mPref.getBoolean("auto_update", true);

        if (autoUpdate) {
            sivUpdate.setChecked(true);
        } else {
            sivUpdate.setChecked(false);
        }
        sivUpdate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sivUpdate.isChecked()) {
                    sivUpdate.setChecked(false);
                    mPref.edit().putBoolean("auto_update", false).commit();
                } else {
                    sivUpdate.setChecked(true);
                    mPref.edit().putBoolean("auto_update", true).commit();
                }
            }
        });
    }

}
