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
        testWinners();
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
            FileScoreBoard.addWinner(newWinner);
            saveNewScoreBoard(FileScoreBoard);
            }
        fillScoreBoard(FileScoreBoard);
    }
    public void testWinners(){
        String path = getFilesDir() + FILE_NAME;
        File file = new File(path);
        ScoreBoard board  = new ScoreBoard(new Winner("caz",0,422,new int[]{ R.drawable.starwars1, R.drawable.starwars2,R.drawable.starwars3,R.drawable.starwars4,R.drawable.starwars5,R.drawable.starwars6,R.drawable.starwars7,
                R.drawable.starwars8,R.drawable.starwars9,R.drawable.starwars10,R.drawable.starwars11,R.drawable.starwars12,R.drawable.starwars13,R.drawable.starwars14,R.drawable.starwars15})
        );
        for ( int i = 0; i <8;i++){
            Winner winner = new Winner("cody",i,i,new int[]{ R.drawable.starwars1, R.drawable.starwars2,R.drawable.starwars3,R.drawable.starwars4,R.drawable.starwars5,R.drawable.starwars6,R.drawable.starwars7,
                    R.drawable.starwars8,R.drawable.starwars9,R.drawable.starwars10,R.drawable.starwars11,R.drawable.starwars12,R.drawable.starwars13,R.drawable.starwars14,R.drawable.starwars15});
            board.addWinner(winner);
        }
        for (int i = 0; i < board.getWinnersList().length;i++) {
        }

        fillScoreBoard(board);
    }
    public ScoreBoard CreateFile(File file) {
        ScoreBoard scoreBoard = new ScoreBoard(new Winner("caz",0,422,new int[]{ R.drawable.starwars1, R.drawable.starwars2,R.drawable.starwars3,R.drawable.starwars4,R.drawable.starwars5,R.drawable.starwars6,R.drawable.starwars7,
                R.drawable.starwars8,R.drawable.starwars9,R.drawable.starwars10,R.drawable.starwars11,R.drawable.starwars12,R.drawable.starwars13,R.drawable.starwars14,R.drawable.starwars15})
        );

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
        LinearLayout parent = findViewById(R.id.linearLayoutScoreBoard);
        parent.removeAllViews();
        for (Winner winner : board.getWinnersList()) {
            addLineScoreBoard(winner,index);
            index++;
        }
    }
    public void addLineScoreBoard(Winner winner,Integer index){
        LinearLayout parent = findViewById(R.id.linearLayoutScoreBoard);
        LinearLayout layout1 = new LinearLayout(this);
        layout1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        layout1.setOrientation(LinearLayout.HORIZONTAL);
        parent.addView(layout1);
        TextView position = new TextView(this);
        position.setText(index.toString());
        TextView username = new TextView(this);
        username.setText(winner.getUsername());

        ImageView avatarView = new ImageView(this);
        avatarView.setImageResource(winner.getWinnerAvatar());
        avatarView.setMaxHeight(100);
        avatarView.setMaxWidth(100);
        avatarView.setScaleType(ImageView.ScaleType.FIT_START);
        avatarView.setAdjustViewBounds(true);

        layout1.addView(position);
        layout1.addView(avatarView);
        layout1.addView(username);
    }
}
