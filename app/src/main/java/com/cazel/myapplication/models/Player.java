package com.cazel.myapplication.models;

import com.cazel.myapplication.R;

import java.util.Random;

public class Player {
    private static Player instance;
    private static String username;
    private static Integer currentAvatarId;
    private static Integer score;
    private static final int[] listImageAvatar = { R.drawable.starwars1, R.drawable.starwars2,R.drawable.starwars3,R.drawable.starwars4,R.drawable.starwars5,R.drawable.starwars6,R.drawable.starwars7,
            R.drawable.starwars8,R.drawable.starwars9,R.drawable.starwars10,R.drawable.starwars11,R.drawable.starwars12,R.drawable.starwars13,R.drawable.starwars14,R.drawable.starwars15};

    private Player(){
        Random rand = new Random();
        int x=rand.nextInt(listImageAvatar.length);
        username="New Player";
        currentAvatarId=x;
        instance=this;
    }

    public static Player getInstance(){
        if(instance==null){
            instance=new Player();
        }
        return instance;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String newUsername){
        username=newUsername;
    }
    public int getPlayerAvatar(){
        return listImageAvatar[currentAvatarId];
    }
    public int[] getListImageAvatar(){
        return listImageAvatar;
    }
    public void setNewPlayerAvatar(Integer id){
        currentAvatarId=id;
    }
    public Integer getScore(){
        return score;
    }
    public void setScore(Integer newScore){
        score=newScore;
    }

}
