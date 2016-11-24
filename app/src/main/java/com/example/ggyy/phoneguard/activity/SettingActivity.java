package com.example.ggyy.phoneguard.activity;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.ggyy.phoneguard.R;
import com.example.ggyy.phoneguard.view.SettingItemView;


public class SettingActivity extends Activity {

    private SharedPreferences mPref;
    private SettingItemView sivUpdate;

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
                    mPref.edit().putBoolean("auto_update",false).commit();
                } else {
                    sivUpdate.setChecked(true);
                    mPref.edit().putBoolean("auto_update",true).commit();
                }
            }
        });
    }

}
