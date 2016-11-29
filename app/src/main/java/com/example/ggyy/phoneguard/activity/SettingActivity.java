package com.example.ggyy.phoneguard.activity;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.ggyy.phoneguard.R;
import com.example.ggyy.phoneguard.service.AddressService;
import com.example.ggyy.phoneguard.service.CallSafeService;
import com.example.ggyy.phoneguard.utils.ServiceStatusUtils;
import com.example.ggyy.phoneguard.view.SettingClickView;
import com.example.ggyy.phoneguard.view.SettingItemView;


public class SettingActivity extends Activity {

    private static final String[] items = {"半透明", "活力橙", "卫士蓝", "金属灰", "苹果绿"};
    private SharedPreferences mPref;
    private SettingItemView sivUpdate;//设置升级选项框
    private SettingItemView sivAddress;// 设置地址归属
    private SettingClickView scvAddressStyle;
    private SettingItemView siv_callsafe;
    private SettingClickView scvAddressLocation;// 修改归属地位置

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
        siv_callsafe = (SettingItemView) findViewById(R.id.siv_callsafe);
        // 根据归属地服务是否运行来更新checkbox
        boolean serviceRunning = ServiceStatusUtils.isServiceRunning(this,
                "com.itheima52.mobilesafe.service.CallSafeService");

        if (serviceRunning) {
            siv_callsafe.setChecked(true);
        } else {
            siv_callsafe.setChecked(false);
        }

        siv_callsafe.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (siv_callsafe.isChecked()) {
                    siv_callsafe.setChecked(false);
                    stopService(new Intent(SettingActivity.this,
                            CallSafeService.class));// 停止归属地服务
                } else {
                    siv_callsafe.setChecked(true);
                    startService(new Intent(SettingActivity.this,
                            CallSafeService.class));// 开启归属地服务
                }
            }
        });

    }

    private void initAddressLocation() {
        scvAddressLocation = (SettingClickView) findViewById(R.id.scv_address_location);
        scvAddressLocation.setTitle("归属地提示框显示位置");
        scvAddressLocation.setDesc("设置归属地提示框的显示位置");

        scvAddressLocation.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this,
                        DragViewActivity.class));
            }
        });

    }

    private void initAddressStyle() {
        scvAddressStyle = (SettingClickView) findViewById(R.id.scv_address_style);

        scvAddressStyle.setTitle("归属地提示框风格");

        int style = mPref.getInt("address_style", 0);// 读取保存的style
        scvAddressStyle.setDesc(items[style]);

        scvAddressStyle.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                showSingleChooseDailog();
            }
        });

    }

    private void showSingleChooseDailog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // builder.setIcon(R.drawable.ic_launcher);
        builder.setTitle("归属地提示框风格");

        int style = mPref.getInt("address_style", 0);// 读取保存的style

        builder.setSingleChoiceItems(items, style,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPref.edit().putInt("address_style", which).commit();// 保存选择的风格
                        dialog.dismiss();// 让dialog消失

                        scvAddressStyle.setDesc(items[which]);// 更新组合控件的描述信息
                    }
                });

        builder.setNegativeButton("取消", null);
        builder.show();

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
