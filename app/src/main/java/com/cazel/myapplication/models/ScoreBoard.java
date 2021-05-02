package com.cazel.myapplication.models;

import android.util.Log;

import java.io.Serializable;

public class ScoreBoard implements Serializable {
    private static final long serialVersionUID = -7084413313205671134L;
    private Winner[] winnersList;

    public ScoreBoard(Winner winner){
        this.winnersList = new Winner[1];
        this.winnersList[0] = winner;
    }

    public Winner[] getWinnersList(){
        return this.winnersList;
    }

    public void addWinner(Winner newWinner){
        int scoreNewWinner = newWinner.getScore();
        Winner[] temp = new Winner[0];

        boolean newWinnerIsPushed = false;
        for (int i = 0; i < this.winnersList.length; i++) {
            if(!newWinnerIsPushed && this.winnersList[i].getScore()<scoreNewWinner){
                temp = this.pushWinner(temp, newWinner);
                newWinnerIsPushed = true;
            }
            temp = this.pushWinner(temp, this.winnersList[i]);
        }
        if(!newWinnerIsPushed){
            temp = this.pushWinner(temp, newWinner);
        }
        this.winnersList = temp;
    }

    private Winner[] pushWinner(Winner[] winnersList,Winner winnerToPush){
        Winner[] temp = new Winner[winnersList.length + 1];
        System.arraycopy(winnersList, 0, temp, 0, winnersList.length);
        temp[winnersList.length] = winnerToPush;
        return temp;
    }
}
