package com.cazel.myapplication.controllers;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.cazel.myapplication.R;

public class GameSelectorActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String  BUTTON_PLAY= "play";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_selector);

        Spinner spinnerDifficulty = findViewById(R.id.spinnerDifficulty);
        String[] itemsDifficulty = new String[]{"All","Easy", "Medium", "Hard"};
        setNewSpinner(spinnerDifficulty,itemsDifficulty);

        Spinner spinnerType = findViewById(R.id.spinnerType);
        String[] itemsType = new String[]{"All","Multiple_Choice","True/False"};
        setNewSpinner(spinnerType,itemsType);

        Spinner spinnerCategory = findViewById(R.id.spinnerCategory);
        String[] itemsCategory = new String[]{"All","Video Games","Films","History","Anime & Manga"};
        setNewSpinner(spinnerCategory,itemsCategory);

        Spinner spinnerNumbQuestion = findViewById(R.id.spinnerNumbQuestion);
        String[] itemsNumbQuestion = new String[]{"5","10","15","20"};
        setNewSpinner(spinnerNumbQuestion,itemsNumbQuestion);

        Button buttonOption = findViewById(R.id.play_custom_game);
        buttonOption.setTag(BUTTON_PLAY);
        buttonOption.setOnClickListener(this);
    }

    public void setNewSpinner(Spinner spinner, String[] list){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, list);
        spinner.setAdapter(adapter);
    }

    @Override
    public void onClick(View V) {
        if(V.getTag().equals(BUTTON_PLAY)){
            SharedPreferences prefs = getSharedPreferences("com.cazel.myapplication.prefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();

            Spinner spinnerDifficulty = (Spinner) findViewById(R.id.spinnerDifficulty);
            String difficulty = spinnerDifficulty.getSelectedItem().toString();
            editor.putString("difficulty", difficulty);

            Spinner spinnerType = (Spinner) findViewById(R.id.spinnerType);
            String type = spinnerType.getSelectedItem().toString();
            editor.putString("type", type);

            Spinner spinnerCategory = (Spinner) findViewById(R.id.spinnerCategory);
            String category = spinnerCategory.getSelectedItem().toString();
            editor.putString("category", category);

            Spinner spinnerNbQuestion = (Spinner) findViewById(R.id.spinnerNumbQuestion);
            String nbQuestion = spinnerNbQuestion.getSelectedItem().toString();
            editor.putString("nbQuestion", nbQuestion);
            editor.apply();

            Intent intent = new Intent (GameSelectorActivity.this, GameActivity.class);
            startActivityForResult(intent,0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        finish();
    }
}