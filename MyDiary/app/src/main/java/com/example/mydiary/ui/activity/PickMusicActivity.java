package com.example.mydiary.ui.activity;
/**
 * 选择本地歌曲的场景
 */

import android.content.Intent;
import android.database.Cursor;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.mydiary.R;
import com.example.mydiary.adapter.MyAdapter;
import com.example.mydiary.item.Song;

import java.util.ArrayList;
import java.util.List;

public class PickMusicActivity extends BaseActivity {

    private ListView lvLocal;
    private List<Song> list;//存储歌曲的数组

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lvLocal = (ListView) findViewById(R.id.lvLocal);
        getMusic();
        lvLocal.setAdapter(new MyAdapter(this,R.layout.item_music,list));
        lvLocal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //将所选歌曲传回去
                Song pickSong=list.get(position);
                Bundle bundle=new Bundle();
                bundle.putSerializable("song",pickSong);
                Intent intent =new Intent();//只是简单负责传递数据
                intent.putExtras(bundle);
                setResult(RESULT_OK,intent);//设置结果码
                finish();
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_pick_music;
    }

    public void getMusic() {
        list=new ArrayList<>();
        Cursor cursor = this.getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null,
                MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        int i = 0;
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            //歌曲信息
            String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
            String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
            String uriData = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
            list.add(i++, new Song(title, artist, uriData));
        }
    }
}
