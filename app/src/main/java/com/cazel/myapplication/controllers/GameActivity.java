package com.cazel.myapplication.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.cazel.myapplication.R;
import com.cazel.myapplication.models.GameData;

import org.json.JSONObject;

import java.io.IOException;

public class GameActivity extends AppCompatActivity implements QuestionsLoaderAsyncInterface{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        QuestionsLoaderAsyncTask questionsLoaderAsyncTask = new QuestionsLoaderAsyncTask(this);
        questionsLoaderAsyncTask.execute("https://opentdb.com/api.php?amount=1&type=boolean");
    }

    @Override
    public void onFinish(JSONObject json) {
        new GameData(json);

    }
}