package com.cazel.myapplication.models;

import com.cazel.myapplication.R;

import java.io.Serializable;

public class Winner implements Serializable {
    private static final long serialVersionUID = -1786162005835235855L;
    private String username;
    private Integer avatarId;
    private Integer score;
    private int[] listImageAvatar;

    public Winner(String username,Integer avatarId,Integer score,int[] listImageAvatar){
        this.username = (username!=null && !username.equals("")) ? username : "new player";
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
        return this.listImageAvatar[avatarId];
    }

}
