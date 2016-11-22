package com.example.ggyy.phoneguard.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.example.ggyy.phoneguard.R;
import com.example.ggyy.phoneguard.utils.MD5Utils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeActivity extends Activity {

    @Bind(R.id.gv_home)
    GridView gvHome;
    @Bind(R.id.activity_home)
    LinearLayout activityHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        String a = "ggyy";
       System.out.println("算法加密"+ MD5Utils.encode(a));

    }
}
