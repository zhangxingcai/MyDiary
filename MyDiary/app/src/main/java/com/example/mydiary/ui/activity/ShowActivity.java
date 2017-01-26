package com.example.mydiary.ui.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mydiary.R;
import com.example.mydiary.item.Diary;
import com.example.mydiary.tool.ImageTool;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.Calendar;

public class ShowActivity extends BaseActivity {

    private Diary diary;
    private ImageView imageView;
    private TextView tvText,tvDate;
    private boolean isPlaying=true;
    private MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        imageView= (ImageView) findViewById(R.id.imageview);
        tvText= (TextView) findViewById(R.id.tvText);
        tvDate= (TextView) findViewById(R.id.tvDate);

        Intent intent=getIntent();
        diary= (Diary) intent.getExtras().get("diary");
        if (diary.getUriString()==null){                                 //设置图片
            imageView.setVisibility(View.GONE);
        } else {
            ImageTool tool=new ImageTool(this);
            imageView.setImageBitmap(tool.compressBitmap(tool.uriToPath(Uri.parse(diary.getUriString()))));
        }
        tvText.setText(diary.getText());//设置文本
        Calendar calendar=Calendar.getInstance();//设置日期文本
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH)+1;
        int day=calendar.get(Calendar.DAY_OF_MONTH);
        tvDate.setText(year+"年"+month+"月"+day+"日");


        if (diary.getSong()!=null)//播放设置的音乐
            initMusic(diary.getUriString());
    }

    private void initMusic(String uriString) {
        player=new MediaPlayer();
        try {
            player.setDataSource(this, Uri.parse(diary.getSong().getPath()));
            player.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        player.start();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_show;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player!=null){
            player.stop();
            player.release();
        }
    }

}
