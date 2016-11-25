package com.example.ggyy.phoneguard.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.example.ggyy.phoneguard.R;
import com.example.ggyy.phoneguard.utils.ToastUtils;
import com.example.ggyy.phoneguard.view.SettingItemView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Setup2Activity extends BaseActivity {

    @Bind(R.id.siv_sim)
    SettingItemView sivSim;
    @Bind(R.id.imageView1)
    ImageView imageView1;
    private Activity act = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup2);
        ButterKnife.bind(this);

        String sim = mperf.getString("sim", null);
        if (!TextUtils.isEmpty(sim)) {
            sivSim.setChecked(true);
        } else {
            sivSim.setChecked(false);
        }
        sivSim.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
             /*   if (sivSim.isChecked()) {
                    sivSim.setChecked(false);
                    mperf.edit().remove("sim").commit();//删除已经绑定的SIM卡
                } else { }*/
                //  sivSim.setChecked(true);
                //保存sim信息
                if (sivSim.isChecked()) {
                    sivSim.setChecked(false);
                    mperf.edit().remove("sim").commit();

                } else {
                    sivSim.setChecked(true);
                    TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
                    System.out.println("获取telephonyManager");
                    int simState = telephonyManager.getSimState();
                    System.out.println("获取sim卡状态" + simState);
                    boolean result = true;
                    switch (simState) {
                        case TelephonyManager.SIM_STATE_ABSENT:
                            result = true; //没有SIM卡
                            System.out.println("没有SIM卡");
                            break;
                        case TelephonyManager.SIM_STATE_UNKNOWN:
                            result = false;
                            System.out.println("有");
                            break;
                        default:
                            System.out.println("未知状态");
                            result = false;
                            break;
                    }
                    if (result) {
                        String simSerialNumber = telephonyManager.getSimSerialNumber();
                        System.out.println("sim序列号" + simSerialNumber);
                        //    mperf.edit().putString("sim",simSerialNumber).commit();
                    } else {
                        ToastUtils.showToast(act, "没有SIM卡,sim卡序列号 设为 1234");
                        mperf.edit().putString("sim", "1234").commit();
                    }
                }
            }
        });
    }

    @Override
    public void showNextPage() {
        /*String sim = mperf.getString("sim", null);
        if (TextUtils.isEmpty(sim)) {
            ToastUtils.showToast(this, "必须绑定sim卡");
            return;
        }*/

        startActivity(new Intent(this, Setup3Activity.class));
        finish();
        overridePendingTransition(R.anim.tran_in, R.anim.tran_out);
    }

    @Override
    public void showPreviousPage() {
        startActivity(new Intent(this, Setup1Activity.class));
        finish();

        // 两个界面切换的动画
        overridePendingTransition(R.anim.tran_previous_in,
                R.anim.tran_previous_out);// 进入动画和退出动画

    }
}
