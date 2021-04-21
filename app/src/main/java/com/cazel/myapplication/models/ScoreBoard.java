package com.cazel.myapplication.models;

import android.text.Html;

public class ScoreBoard {
    private Player[] playersList;

    public ScoreBoard(){
        playersList = new Player[0];
    }

    public Player[] getPlayersList(){
        return this.playersList;
    }

    public void addPlayer(Player newPlayer){
        Player[] temp = new Player[playersList.length + 1];
        System.arraycopy(playersList, 0, temp, 0, playersList.length);
        temp[playersList.length] = newPlayer;
        playersList = temp;
    }
}