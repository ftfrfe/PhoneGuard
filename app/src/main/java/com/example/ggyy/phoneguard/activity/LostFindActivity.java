package com.example.ggyy.phoneguard.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ggyy.phoneguard.R;

public class LostFindActivity extends Activity {

    private SharedPreferences sharedPreferences;
    private TextView tvSavePhone;
    private ImageView ivProtect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences("config", MODE_PRIVATE);
        boolean configed = sharedPreferences.getBoolean("configed", false);

        if (configed) {
            setContentView(R.layout.activity_lost_find);
            tvSavePhone = (TextView) findViewById(R.id.tv_safe_phone);
            String phone = sharedPreferences.getString("safe_phone", "");
            tvSavePhone.setText(phone);
            ivProtect = (ImageView) findViewById(R.id.iv_protect);
            boolean protect = sharedPreferences.getBoolean("protect", false);
            if (protect) {
                ivProtect.setImageResource(R.drawable.lock);
            } else {
                ivProtect.setImageResource(R.drawable.unlock);
            }
        } else {
            //跳转设置向导页
            startActivity(new Intent(this, Setup1Activity.class));
            finish();
        }

    }
    public  void reEnter(View view){
        startActivity(new Intent(this,Setup1Activity.class));
    }
}
