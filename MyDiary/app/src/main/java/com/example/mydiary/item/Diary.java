package com.example.mydiary.item;

import android.graphics.Bitmap;
import android.net.Uri;

import java.io.Serializable;

/**
 * Created by lenovo on 2017/1/24.
 */
public class Diary implements Serializable{
    private Song song;
    private String text;
    private String uriString;//图片的uri字符串

    public String getUriString() {
        return uriString;
    }

    public void setUriString(String uriString) {
        this.uriString = uriString;
    }

    public Diary(Song song, String text, String uriString) {
        this.song = song;
        this.text = text;
        this.uriString = uriString;

    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
