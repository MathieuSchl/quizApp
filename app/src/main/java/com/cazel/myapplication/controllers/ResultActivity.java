package com.cazel.myapplication.controllers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
        fillScoreBoard();
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

    public void fillScoreBoard(){
        addLineScoreBoard();
    }
    public void addLineScoreBoard(){
        LinearLayout parent = findViewById(R.id.linearLayoutScoreBoard);
        LinearLayout layout1 = new LinearLayout(this);

        layout1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        layout1.setOrientation(LinearLayout.HORIZONTAL);
        parent.addView(layout1);
        TextView position = new TextView(this);
        position.setText("1");
        ImageView avatarView = new ImageView(this);
        Player player = Player.getInstance();
        avatarView.setImageResource(player.getPlayerAvatar());
        avatarView.setMaxHeight(100);
        avatarView.setMaxWidth(100);
        avatarView.setScaleType(ImageView.ScaleType.FIT_START);
        avatarView.setAdjustViewBounds(true);
        layout1.addView(position);
        layout1.addView(avatarView);


    }
}
