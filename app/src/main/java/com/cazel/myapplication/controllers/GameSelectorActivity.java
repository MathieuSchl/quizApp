package com.cazel.myapplication.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.cazel.myapplication.R;

public class GameSelectorActivity extends AppCompatActivity {

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
        String[] itemsCategory = new String[]{"All","Video Games","History","Anime & Manga",};
        setNewSpinner(spinnerCategory,itemsCategory);

        Spinner spinnerNumbQuestion = findViewById(R.id.spinnerNumbQuestion);
        String[] itemsNumbQuestion = new String[]{"5","10","15","20"};
        setNewSpinner(spinnerNumbQuestion,itemsNumbQuestion);


    }

    public void setNewSpinner(Spinner spinner, String[] list){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, list);
        spinner.setAdapter(adapter);
    }
}