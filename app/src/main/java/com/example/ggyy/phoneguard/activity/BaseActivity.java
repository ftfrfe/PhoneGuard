package com.example.ggyy.phoneguard.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public abstract class BaseActivity extends Activity {

    public SharedPreferences mperf;
    private GestureDetector mDectector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mperf = getSharedPreferences("config", MODE_PRIVATE);
        mDectector = new GestureDetector(this, new SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                // 判断纵向滑动幅度是否过大, 过大的话不允许切换界面
                if (Math.abs(e2.getRawY() - e1.getRawY()) > 100) {
                    Toast.makeText(BaseActivity.this, "不能这样划哦!",
                            Toast.LENGTH_SHORT).show();
                    return true;
                }

                // 判断滑动是否过慢
                if (Math.abs(velocityX) < 100) {
                    Toast.makeText(BaseActivity.this, "滑动的太慢了!",
                            Toast.LENGTH_SHORT).show();
                    return true;
                }

                // 向右划,上一页
                if (e2.getRawX() - e1.getRawX() > 200) {
                    showPreviousPage();
                    return true;
                }

                // 向左划, 下一页
                if (e1.getRawX() - e2.getRawX() > 200) {
                    showNextPage();
                    return true;
                }


                return super.onFling(e1, e2, velocityX, velocityY);
            }
        });
        /**
         * 手势滑动时间 e1滑动起点 e2 滑动终点
         */


    }

    public abstract void showNextPage();

    public abstract void showPreviousPage();

    public void next(View view) {
        showNextPage();
    }

    public void previous(View view) {
        showPreviousPage();
    }

    public boolean onTouchEvent(MotionEvent event) {
        mDectector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }


}
