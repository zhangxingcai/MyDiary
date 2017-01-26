package com.example.mydiary.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.mydiary.R;

public abstract class BaseActivity extends AppCompatActivity {

    public final String TAG="BaseActivity";//方便打印
    private Handler mHandler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
    }

    //跳转方法，并判断是否在跳转后finish
    protected void startActivity(Class activity,boolean ifFinish){
        Intent intent=new Intent(this,activity);
        startActivity(intent);
        if (ifFinish){
            finish();
        }
    }

    public abstract int getLayoutId();

    protected void toast(String text){
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }

    protected void postDelay(Runnable runnable,long millis){
        mHandler.postDelayed(runnable,millis);
    }
}
