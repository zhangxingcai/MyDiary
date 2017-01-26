package com.example.mydiary.item;

import java.io.Serializable;

/**
 * Created by lenovo on 2017/1/23.
 */
public class Song implements Serializable{
    private String name;
    private String singer;
    private String path;//uri字符串

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Song(String name, String singer, String path) {
        this.name = name;
        this.singer = singer;
        this.path = path;

    }
}
