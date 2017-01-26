package com.example.mydiary.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mydiary.R;

import cn.bmob.v3.Bmob;

public class SplashActivity extends BaseActivity {

    private final String APP_KEY="12ee96a454486127e823e192c1d7c406";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bmob.initialize(this,APP_KEY);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }
}
