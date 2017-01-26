package com.example.mydiary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

import com.example.mydiary.ui.activity.BaseActivity;
import com.example.mydiary.ui.activity.DiaryActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    private boolean quit=false;
    private CardView card1,card2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        card1= (CardView) findViewById(R.id.card1);
        card1.setOnClickListener(this);
        card2= (CardView) findViewById(R.id.card2);
        card2.setOnClickListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onBackPressed() {//双击退出
        if (quit==false)
        {
            Toast.makeText(MainActivity.this,"再按一次退出",Toast.LENGTH_SHORT).show();
            quit=true;
            new Timer(true).schedule(new TimerTask() {
                @Override
                public void run() {
                    quit=false;
                }
            },2000);
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.card1:
                startActivity(DiaryActivity.class,false);
                break;
            case R.id.card2:
                toast("card2");
                break;
        }
    }
}
