package com.cazel.myapplication.models;

public class ScoreBoard {
    private Player[] playersList;

    public ScoreBoard(){
        playersList = new Player[0];
    }

    public Player[] getPlayersList(){
        return this.playersList;
    }
}