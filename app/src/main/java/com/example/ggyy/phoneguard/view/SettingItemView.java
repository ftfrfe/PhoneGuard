package com.example.ggyy.phoneguard.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.ggyy.phoneguard.R;

import butterknife.Bind;


/**
 * Created by T430 on 2016/11/23.
 */

public class SettingItemView extends RelativeLayout {
    private static final String NAMESPACE = "http://schemas.android.com/apk/res/com.example.ggyy.phoneguard";


    private String mTitle;
    private String mDescOn;
    private String mDescOff;
    private TextView tvTitle;
    private TextView tvDesc;
    private CheckBox cbStatus;


    public SettingItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    public SettingItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTitle = attrs.getAttributeValue(NAMESPACE, "title");
        mDescOn = attrs.getAttributeValue(NAMESPACE, "desc_on");
        mDescOff = attrs.getAttributeValue(NAMESPACE, "desc_off");
        initView();
    }
    public SettingItemView(Context context) {
        super(context);
        initView();
    }
    private void initView() {
        View.inflate(getContext(), R.layout.view_setting_item, this);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvDesc = (TextView) findViewById(R.id.tv_desc);
        cbStatus = (CheckBox) findViewById(R.id.cb_status);

        setTitle(mTitle);

    }
    public void setTitle(String title) {
        tvTitle.setText(title);
    }
    public boolean isChecked(){
        return  cbStatus.isChecked();
    }
    public void setChecked(boolean check){
        cbStatus.setChecked(check);
        if(check){
            setDesc(mDescOn);
        }else {
            setDesc(mDescOff);
        }
    }

    private void setDesc(String mDescOn) {
        tvDesc.setText(mDescOn);
    }

}
