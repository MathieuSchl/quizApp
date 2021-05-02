package com.cazel.myapplication.models;

import com.cazel.myapplication.R;

import java.io.Serializable;

public class Winner implements Serializable {
    private static String username;
    private static Integer avatarId;
    private static Integer score;
    private transient static int[] listImageAvatar;

    public Winner(String username,Integer avatarId,Integer score,int[] listImageAvatar){
        Winner.username = username;
        Winner.avatarId = avatarId;
        Winner.score = score;
        Winner.listImageAvatar = listImageAvatar;
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
