package com.cazel.myapplication.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cazel.myapplication.R;
import com.cazel.myapplication.models.GameData;
import com.cazel.myapplication.models.Player;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        GameData data = GameData.getInstance();
        Player player = Player.getInstance();

        TextView nickName = findViewById(R.id.nickNameResult);
        nickName.setText(player.getUsername());

        TextView noteResult = findViewById(R.id.noteResult);
        noteResult.setText(data.getScore()+"/"+data.getNbQuestions());

        ImageView avatar = findViewById(R.id.resultAvatarZone);
        avatar.setImageResource(player.getPlayerAvatar());

        Button buttonHome = findViewById(R.id.result_page_home_button);
        buttonHome.setTag("Home");
        buttonHome.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {

        if(v.getTag().equals("Home")){
            Intent intent = new Intent (ResultActivity.this, MainActivity.class);
            finish();
        }
    }
}