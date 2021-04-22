package com.cazel.myapplication.models;

import com.cazel.myapplication.R;

import java.io.Serializable;

public class Winner implements Serializable {
    private static String username;
    private static Integer avatarId;
    private static Integer score;
    private transient static int[] listImageAvatar;

    public Winner(Player player){
        Winner.username = player.getUsername();
        Winner.avatarId = player.getCurrentAvatarId();
        Winner.score = player.getScore();
        Winner.listImageAvatar = player.getListImageAvatar();
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
