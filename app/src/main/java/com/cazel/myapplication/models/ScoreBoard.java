package com.cazel.myapplication.models;

import android.text.Html;

import java.io.Serializable;

public class ScoreBoard implements Serializable {
    private static ScoreBoard instance;
    private Winner[] winnersList;

    public ScoreBoard(Winner winner){
        winnersList = new Winner[1];
        winnersList[0] = winner;
        instance = this;
    }

    public static ScoreBoard getInstance( Winner winner){
        if(instance==null){
            instance = new ScoreBoard(winner);
        }
        return instance;
    }
    public static ScoreBoard getInstance(){
        return instance;
    }

    public Winner[] getWinnersList(){
        return this.winnersList;
    }

    public void addWinner(Winner newWinner){
        Winner[] temp = new Winner[winnersList.length + 1];
        System.arraycopy(winnersList, 0, temp, 0, winnersList.length);
        temp[winnersList.length] = newWinner;
        winnersList = temp;
    }
    
}
