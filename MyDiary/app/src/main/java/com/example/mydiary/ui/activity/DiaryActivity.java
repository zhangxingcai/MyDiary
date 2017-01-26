package com.example.mydiary.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mydiary.R;
import com.example.mydiary.item.Diary;
import com.example.mydiary.item.Song;
import com.example.mydiary.tool.ImageTool;
import com.squareup.picasso.Picasso;

public class DiaryActivity extends BaseActivity implements View.OnClickListener{

    private Button btnAddMusic,btnAddImage,btnYulan;
    private TextView tvSongName,tvImage;
    private EditText edit;

    private final static int REQUEST_CODE=1;
    private final static int REQUEST_CODE2=2;
    private Song song;
    private Uri imagePath;

    private ImageTool imageTool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btnAddMusic= (Button) findViewById(R.id.btnAddMusic);
        btnAddMusic.setOnClickListener(this);
        btnAddImage= (Button) findViewById(R.id.btnAddImage);
        btnAddImage.setOnClickListener(this);
        tvSongName= (TextView) findViewById(R.id.tvSongName);
        tvImage= (TextView) findViewById(R.id.tvImage);
        edit= (EditText) findViewById(R.id.edit);
        btnYulan= (Button) findViewById(R.id.btnYulan);
        btnYulan.setOnClickListener(this);

        imageTool=new ImageTool(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_diary;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAddMusic:
                Intent intent=new Intent(this,PickMusicActivity.class);
                startActivityForResult(intent,REQUEST_CODE);
                break;
            case R.id.btnAddImage:
                //打开系统的选图界面
                Intent intent1=new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent1,REQUEST_CODE2);
                break;
            case R.id.btnYulan:
                if (edit.getText().length()==0) edit.setText(" ");
                String imagePathString=null;
                if (imagePath!=null){
                    imagePathString=imagePath.toString();
                }
                Diary diary=new Diary(song,edit.getText().toString(),imagePathString);
                Bundle bundle=new Bundle();
                bundle.putSerializable("diary",diary);
                Intent intent2=new Intent(this,ShowActivity.class);
                intent2.putExtras(bundle);
                startActivity(intent2);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case REQUEST_CODE:
                if (resultCode==RESULT_OK){
                    Song pickSong= (Song) data.getExtras().get("song");
                    song=pickSong;
                    tvSongName.setText(pickSong.getName());
                }
                break;
            case REQUEST_CODE2://处理选图返回值
                if (data.getData()!=null){
                    final Uri imageUri=data.getData();
                    this.imagePath=imageUri;
                    final Bitmap bitmap2=imageTool.uriToBitmap(imageUri);
                    Drawable drawable=imageTool.bitmapToDrawable(bitmap2);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        //tvImage.setBackground(drawable);
                        tvImage.setText("");
                        postDelay(new Runnable() {//异步处理图片压缩
                            @Override
                            public void run() {
                                Bitmap bitmap3=imageTool.compressBitmap(imageTool.uriToPath(imageUri));
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                    tvImage.setBackground(imageTool.bitmapToDrawable(bitmap3));
                                }
                            }
                        },0);
                    } else {
                        tvImage.setText("已添加");
                    }
                }
                break;
        }
    }
}
