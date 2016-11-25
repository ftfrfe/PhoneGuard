package com.example.ggyy.phoneguard.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ggyy.phoneguard.R;

public class Setup4Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup4);

    }

    @Override
    public void showNextPage() {
        startActivity(new Intent(this, LostFindActivity.class));
        finish();

        // 两个界面切换的动画
        overridePendingTransition(R.anim.tran_in, R.anim.tran_out);// 进入动画和退出动画

        mperf.edit().putBoolean("configed", true).commit();// 更新sp,表示已经展示过设置向导了,下次进来就不展示啦
    }

    @Override
    public void showPreviousPage() {
        startActivity(new Intent(this, Setup3Activity.class));
        finish();

        // 两个界面切换的动画
        overridePendingTransition(R.anim.tran_previous_in,
                R.anim.tran_previous_out);// 进入动画和退出动画
    }

}
