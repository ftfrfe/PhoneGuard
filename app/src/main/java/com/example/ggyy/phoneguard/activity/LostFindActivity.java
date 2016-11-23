package com.example.ggyy.phoneguard.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.ggyy.phoneguard.R;

public class LostFindActivity extends Activity {

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences("config", MODE_PRIVATE);
        boolean configed = sharedPreferences.getBoolean("configed", false);

        if (configed){
            setContentView(R.layout.activity_lost_find);
        }
        else {
            //跳转设置向导页
            startActivity(new Intent(this,Setup1Activity.class));
            finish();
        }

    }
}
