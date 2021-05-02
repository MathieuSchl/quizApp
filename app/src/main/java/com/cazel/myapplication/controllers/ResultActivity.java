package com.cazel.myapplication.controllers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cazel.myapplication.R;
import com.cazel.myapplication.models.GameData;
import com.cazel.myapplication.models.Player;
import com.cazel.myapplication.models.ScoreBoard;
import com.cazel.myapplication.models.Winner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String FILE_NAME = "/scoreBoard.ser";

    private Winner newWinner;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        GameData data = GameData.getInstance();
        Player player = Player.getInstance();
        player.setScore(data.getScore());
        TextView nickName = findViewById(R.id.nickNameResult);
        nickName.setText(player.getUsername());

        this.newWinner = new Winner(player.getUsername(),player.getCurrentAvatarId(),player.getScore(),player.getListImageAvatar());
        TextView noteResult = findViewById(R.id.noteResult);
        noteResult.setText(data.getScore()+"/"+data.getNbQuestions());

        ImageView avatar = findViewById(R.id.resultAvatarZone);
        avatar.setImageResource(player.getPlayerAvatar());

        Button buttonHome = findViewById(R.id.result_page_home_button);
        buttonHome.setTag("Home");
        buttonHome.setOnClickListener(this);
        try {
            updateScoreBoard();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {

        if(v.getTag().equals("Home")){
            finish();
        }
    }
    public void updateScoreBoard() throws IOException {
        String path = getFilesDir() + FILE_NAME;
        File file = new File(path);
        ScoreBoard FileScoreBoard = null;
        if(!file.exists()) {
            FileScoreBoard = CreateFile(file);
        }
        else{
            FileScoreBoard = GetFileScoreBoard();
            Log.d("status","Avant");
            for (int i = 0; i < FileScoreBoard.getWinnersList().length; i++) {
                Log.d("test",FileScoreBoard.getWinnersList()[i].getUsername());
            }
            FileScoreBoard.addWinner(newWinner);
            Log.d("status","Après");
            for (int i = 0; i < FileScoreBoard.getWinnersList().length; i++) {
                Log.d("test",FileScoreBoard.getWinnersList()[i].getUsername());
                Log.d("test",FileScoreBoard.getWinnersList()[i].getScore().toString());
            }
            saveNewScoreBoard(FileScoreBoard);
            }
        fillScoreBoard(FileScoreBoard);
    }
    public ScoreBoard CreateFile(File file) {
        ScoreBoard scoreBoard = new ScoreBoard(this.newWinner);

        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(scoreBoard);
            os.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scoreBoard;
    }
    public ScoreBoard GetFileScoreBoard() throws IOException {
        String path = getFilesDir() + FILE_NAME;
        FileInputStream fis = new FileInputStream(new File(path));
        ObjectInputStream is = new ObjectInputStream(fis);
        ScoreBoard scoreBoard = null;
        try {
            scoreBoard = (ScoreBoard) is.readObject();
            scoreBoard.getWinnersList();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        is.close();
        fis.close();

        return scoreBoard;
    }
    public void saveNewScoreBoard(ScoreBoard scoreBoard) throws IOException {
        String path = getFilesDir() + FILE_NAME;
        FileOutputStream fos = new FileOutputStream(new File(path));
        ObjectOutputStream os = new ObjectOutputStream(fos);
        os.writeObject(scoreBoard);
        os.close();
        fos.close();
    }

    public void fillScoreBoard(ScoreBoard board){
        Integer index = 1;

        for (Winner winner : board.getWinnersList()) {
            addLineScoreBoard(winner,index);
            Log.d("LOOK","test after addLine");
            index++;
        }
    }
    public void addLineScoreBoard(Winner winner,Integer index){
        LinearLayout parent = findViewById(R.id.linearLayoutScoreBoard);
        LinearLayout layout1 = new LinearLayout(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout1.setLayoutParams(lp);
        layout1.setOrientation(LinearLayout.HORIZONTAL);
        parent.addView(layout1);

        TextView position = new TextView(this);
        position.setText(index.toString());

        TextView username = new TextView(this);
        username.setText(winner.getUsername());
        username.setPadding(30, 0, 30, 0);

        TextView score = new TextView(this);
        score.setText(winner.getScore().toString());

        ImageView avatarView = new ImageView(this);
        avatarView.setImageResource(winner.getWinnerAvatar());
        avatarView.setMaxHeight(100);
        avatarView.setMaxWidth(100);
        avatarView.setScaleType(ImageView.ScaleType.FIT_START);
        avatarView.setAdjustViewBounds(true);
        avatarView.setPadding(20,0,0,0);

        layout1.addView(position);
        layout1.addView(avatarView);
        layout1.addView(username);
        layout1.addView(score);
    }
}
