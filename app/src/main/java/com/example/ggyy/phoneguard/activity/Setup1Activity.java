package com.example.ggyy.phoneguard.activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.ggyy.phoneguard.R;

public class Setup1Activity extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup1);


    }

    @Override
    public void showNextPage() {
        startActivity(new Intent(this,Setup2Activity.class));
        finish();
        overridePendingTransition(R.anim.tran_in, R.anim.tran_out);
    }

    @Override
    public void showPreviousPage() {

    }
}
