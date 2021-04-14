package com.cazel.myapplication.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.cazel.myapplication.R;
import com.cazel.myapplication.models.GameData;
import com.cazel.myapplication.models.Question;

import org.json.JSONObject;

import java.io.IOException;

public class GameActivity extends AppCompatActivity implements QuestionsLoaderAsyncInterface{
private GameData game = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        QuestionsLoaderAsyncTask questionsLoaderAsyncTask = new QuestionsLoaderAsyncTask(this);
        questionsLoaderAsyncTask.execute("https://opentdb.com/api.php?amount=1&type=boolean");
    }

    @Override
    public void onFinish(JSONObject json) {

        Bundle extra = getIntent().getExtras();
        String nickName = extra.getString("nickName");
        this.game = new GameData(json,nickName);

        start_game(this.game.getActualQuestion());

    }
    public void start_game(Question question){

    }
}