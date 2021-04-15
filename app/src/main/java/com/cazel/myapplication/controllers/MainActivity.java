
package com.cazel.myapplication.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.cazel.myapplication.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String  BUTTON_START= "start";
    private static final String  BUTTON_IMAGE_CHANGE= "image_change";
    private static final String  BUTTON_OPTION= "option";
    private static String[] imageList = new String[]{"gonk.png","droidDeca.png","droidB1.png"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
            EditText nickName = findViewById(R.id.nickName);
            intent.putExtra("nickName",nickName.getText());
            editor.putString("nickName", nickName.getText().toString());
            editor.apply();
            startActivity(intent);
        }
        if(V.getTag().equals(BUTTON_IMAGE_CHANGE)){

        }
        if(V.getTag().equals(BUTTON_OPTION)){
            SharedPreferences prefs = getSharedPreferences("com.cazel.myapplication.prefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();

            EditText nickName = findViewById(R.id.nickName);
            editor.putString("nickName", nickName.getText().toString());
            editor.apply();

            Intent intent = new Intent (MainActivity.this, GameSelectorActivity.class);
            startActivity(intent);
        }


    }
}