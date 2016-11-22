package com.example.ggyy.phoneguard.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.example.ggyy.phoneguard.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeActivity extends Activity {

    @Bind(R.id.gv_home)
    GridView gvHome;
    @Bind(R.id.activity_home)
    LinearLayout activityHome;
    private SharedPreferences sharedPreferences;

    /**
     * 1.获取数据共享对象SharePreferences  mPerf （getSharedPreferences("congif",MODE_PREIVATE)）
     * 2.gvHome 设置Adapter （HomeAdapter） 设置监听
     * 2.1
     * 3.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        sharedPreferences = getSharedPreferences("config", MODE_PRIVATE);
        gvHome.setAdapter(new HomeAdapter());

        //设置点击
        gvHome.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                switch (i){
                    case 0://手机防盗
                        showPasswordDialog();
                        break;

                    case  8://设置中心
                        startActivity(new Intent(HomeActivity.this,SettingActivity.class));

                        break;
                    default:
                        break;

                }
            }
        });


    }

    protected void showPasswordDialog() {
        String savePassword = sharedPreferences.getString("password", null);
            if(!TextUtils.isEmpty(savePassword)){//判断密码是否设置了
                showPasswordInputDialog(); //进入输入密码框
            }
        else {
                showPasswordSetDialog();//进入设置密码框
            }
    }

    private void showPasswordInputDialog() {
    }

    private void showPasswordSetDialog() {


    }

    class HomeAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            return null;
        }
    }
}
