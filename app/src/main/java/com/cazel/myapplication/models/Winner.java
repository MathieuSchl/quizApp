package com.cazel.myapplication.models;

import com.cazel.myapplication.R;

import java.io.Serializable;

public class Winner implements Serializable {
    private String username;
    private Integer avatarId;
    private Integer score;
    private transient  int[] listImageAvatar;

    public Winner(String username,Integer avatarId,Integer score,int[] listImageAvatar){
        this.username = username;
        this.avatarId = avatarId;
        this.score = score;
        this.listImageAvatar = listImageAvatar;
    }

    public String getUsername() {
        return username;
    }

    public Integer getScore() {
        return score;
    }
    public int getWinnerAvatar(){
        return listImageAvatar[avatarId];
    }

}
