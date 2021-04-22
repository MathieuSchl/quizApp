
package com.cazel.myapplication.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.cazel.myapplication.R;
import com.cazel.myapplication.models.Player;
import com.cazel.myapplication.models.ScoreBoard;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String  BUTTON_START= "start";
    private static final String  BUTTON_IMAGE_CHANGE= "image_change";
    private static final String  BUTTON_OPTION= "option";
    private static final Integer  GAME_ACTIVITY_REQUEST_CODE= 1;
    private static final Integer  OPTION_ACTIVITY_REQUEST_CODE= 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Player currentPlayer = Player.getInstance();

        TextView playerText=findViewById(R.id.nickName);
        playerText.setHint(currentPlayer.getUsername());
        ImageView avatar=findViewById(R.id.avatarImage);
        avatar.setImageResource(currentPlayer.getPlayerAvatar());

        Button buttonStart = findViewById(R.id.button);
        buttonStart.setTag(BUTTON_START);
        buttonStart.setOnClickListener(this);

        ImageButton changeImage = findViewById(R.id.change_image);
        changeImage.setTag(BUTTON_IMAGE_CHANGE);
        changeImage.setOnClickListener(this);

        Button buttonOption = findViewById(R.id.options);
        buttonOption.setTag(BUTTON_OPTION);
        buttonOption.setOnClickListener(this);
        }

    @Override
    public void onClick(View V) {
        TextView username = findViewById(R.id.nickName);
        Player currentPlayer = Player.getInstance();
        if(username.getText().toString()!=""){
            currentPlayer.setUsername(username.getText().toString());
        }else{
            currentPlayer.setUsername(username.getHint().toString());
        }

        if(V.getTag().equals(BUTTON_START)){
            SharedPreferences prefs = getSharedPreferences("com.cazel.myapplication.prefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();

            String difficulty = "";
            editor.putString("difficulty", difficulty);

            String type = "";
            editor.putString("type", type);

            String category = "";
            editor.putString("category", category);

            String nbQuestion = "10";
            editor.putString("nbQuestion", nbQuestion);

            Intent intent = new Intent (MainActivity.this, GameActivity.class);
            editor.apply();
            startActivityForResult(intent,GAME_ACTIVITY_REQUEST_CODE);

        }
        if(V.getTag().equals(BUTTON_OPTION)){
            SharedPreferences prefs = getSharedPreferences("com.cazel.myapplication.prefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();

            EditText nickName = findViewById(R.id.nickName);
            nickName.setHint(getResources().getString(R.string.pseudo));
            editor.putString("nickName", nickName.getText().toString());
            editor.apply();

            Intent intent = new Intent (MainActivity.this, GameSelectorActivity.class);
            startActivityForResult(intent,OPTION_ACTIVITY_REQUEST_CODE);
        }
        if(V.getTag().equals(BUTTON_IMAGE_CHANGE)){
            changeAvatar();
        }
    }

    public void changeAvatar(){
        Random rand = new Random();
        Player player=Player.getInstance();
        int x=rand.nextInt(player.getListImageAvatar().length);
        player.setNewPlayerAvatar(x);
        ImageView avatar=findViewById(R.id.avatarImage);
        avatar.setImageResource(player.getPlayerAvatar());
    }

}