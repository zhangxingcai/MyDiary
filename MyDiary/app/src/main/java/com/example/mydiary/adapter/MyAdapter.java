package com.example.mydiary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mydiary.R;
import com.example.mydiary.item.Song;

import java.util.List;

/**
 * Created by lenovo on 2017/1/23.
 */
public class MyAdapter extends ArrayAdapter<Song> {

    //本地音乐列表每一个列表项的布局
    private int resourseId;

    public MyAdapter(Context context, int resource, List<Song> objects) {
        super(context, resource, objects);
        resourseId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Song song = getItem(position);
        View view;
        //列表优化，存在一个viewholder中避免多次创建
        ViewHolder viewHolder;
        if (convertView==null){
            view = LayoutInflater.from(getContext()).inflate(resourseId,null);
            viewHolder = new ViewHolder();
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tvName = (TextView) view.findViewById(R.id.tvNameOfSong1);
        viewHolder.tvName.setText(song.getName());
        viewHolder.tvSinger = (TextView) view.findViewById(R.id.tvSinger1);
        viewHolder.tvSinger.setText(song.getSinger());
        return view;
    }

    class ViewHolder{
        TextView tvName,tvSinger;
    }
}
