package com.example.ggyy.phoneguard.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ggyy.phoneguard.R;
import com.example.ggyy.phoneguard.utils.MD5Utils;
import com.example.ggyy.phoneguard.utils.ToastUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeActivity extends Activity {


    @Bind(R.id.gv_home)
    GridView gvHome;
    private SharedPreferences sharedPreferences;
    private String[] mItems = new String[]{"手机防盗", "通讯卫士", "软件管理", "进程管理",
            "流量统计", "手机杀毒", "缓存清理", "高级工具", "设置中心"};

    private int[] mPics = new int[]{R.drawable.home_safe,
            R.drawable.home_callmsgsafe, R.drawable.home_apps,
            R.drawable.home_taskmanager, R.drawable.home_netmanager,
            R.drawable.home_trojan, R.drawable.home_sysoptimize,
            R.drawable.home_tools, R.drawable.home_settings};
    private Activity act = this;

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

        System.out.println("进入主页面 bind之前");

        System.out.println("进入主页面后 bind之后");
        sharedPreferences = getSharedPreferences("config", MODE_PRIVATE);
        System.out.println("创建sp");
        ButterKnife.bind(this);
        gvHome.setAdapter(new HomeAdapter());

        //设置点击
        gvHome.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                switch (i) {
                    case 0://手机防盗
                        showPasswordDialog();
                        break;
                    case 1://通讯卫士
                        startActivity(new Intent(act,CallMsmSafeActivity.class));
                        break;
                    case 8://设置中心
                        startActivity(new Intent(HomeActivity.this, SettingActivity.class));

                        break;
                    default:
                        break;

                }
            }
        });


    }

    protected void showPasswordDialog() {
        String savePassword = sharedPreferences.getString("password", null);
        if (!TextUtils.isEmpty(savePassword)) {//判断密码是否设置了
            showPasswordInputDialog(); //进入输入密码框
        } else {
            showPasswordSetDialog();//进入设置密码框
        }
    }

    private void showPasswordInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();
        View view = View.inflate(this, R.layout.dailog_input_password, null);
        dialog.setView(view, 0, 0, 0, 0);
        final EditText etPassword = (EditText) view.findViewById(R.id.et_password);
        Button btnOK = (Button) view.findViewById(R.id.btn_ok);
        Button btnCancel = (Button) view.findViewById(R.id.btn_cancel);

        btnOK.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = etPassword.getText().toString();
                if (!TextUtils.isEmpty(password)) {
                    String savedPassword = sharedPreferences.getString("password", null);

                    if (MD5Utils.encode(password).equals(savedPassword)) {
                        dialog.dismiss();
                        startActivity(new Intent(HomeActivity.this,
                                LostFindActivity.class));
                    } else {
                        ToastUtils.showToast(act, "密码输出错误");
                    }
                } else {
                    ToastUtils.showToast(act, "密码输入不能为空");
                }
            }
        });
        btnCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();// 隐藏dialog
            }
        });
        dialog.show();
    }

    private void showPasswordSetDialog() {
        /**
         * 1.Alertdialog.builder对象，将activity穿进去
         * 2.builder.creat()   创建dialog对象
         * 3.View.inflate（this,R.layout.dialog_set_password,null） 返回view
         * 4.dialog.setView(view,0,0,0,0) 设置边距为0
         * 5.
         */
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();
        View view = View.inflate(this, R.layout.dailog_set_password, null);
        dialog.setView(view, 0, 0, 0, 0);
        final EditText etPassword = (EditText) view.findViewById(R.id.et_password);
        final EditText etPassworConfirm = (EditText) view.findViewById(R.id.et_password_confirm);

        Button btnOk = (Button) view.findViewById(R.id.btn_ok);
        Button btnCancel = (Button) view.findViewById(R.id.btn_cancel);

        btnOk.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = etPassword.getText().toString();
                String passwordConfirm = etPassworConfirm.getText().toString();

                if (!TextUtils.isEmpty(password) && !passwordConfirm.isEmpty()) {
                    if (password.equals(passwordConfirm)) {
                        sharedPreferences.edit().putString("password", MD5Utils.encode(password)).commit();
                        ToastUtils.showToast(act, "密码提交成功");
                        dialog.dismiss();
                        startActivity(new Intent(HomeActivity.this, LostFindActivity.class));
                        System.out.println("跳转到方丢失页面");
                    } else {
                        ToastUtils.showToast(act, "两次密码不同");
                    }


                } else {
                    ToastUtils.showToast(act, "输入框内容不能为空!");
                }
            }
        });
        btnCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    class HomeAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mItems.length;
        }

        @Override
        public Object getItem(int i) {
            return mItems[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View contentView, ViewGroup viewGroup) {
            View view = View.inflate(HomeActivity.this, R.layout.home_list_item, null);
            ImageView ivView = (ImageView) view.findViewById(R.id.iv_item);
            TextView tvView = (TextView) view.findViewById(R.id.tv_item);

            tvView.setText(mItems[i]);
            ivView.setImageResource(mPics[i]);
            return view;
        }
    }
}
