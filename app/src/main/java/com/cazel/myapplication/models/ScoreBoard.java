package com.cazel.myapplication.models;

import android.text.Html;
import android.util.Log;

import java.io.Serializable;

public class ScoreBoard implements Serializable {
    private static final long serialVersionUID = -7084413313205671134L;
    private Winner[] winnersList;

    public ScoreBoard(Winner winner){
        winnersList = new Winner[1];
        winnersList[0] = winner;
    }

    public Winner[] getWinnersList(){
        return this.winnersList;
    }

    public void addWinner(Winner newWinner){
        Winner[] temp = new Winner[getWinnersList().length + 1];
        System.arraycopy(getWinnersList(), 0, temp, 0, getWinnersList().length);
        temp[winnersList.length] = newWinner;
        winnersList = temp;
        Log.d("WinnerLook",winnersList.toString()+"___"+winnersList.length);
    }

}
