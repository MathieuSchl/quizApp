
package com.cazel.myapplication.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.cazel.myapplication.R;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String  BUTTON_START= "start";
    private static final String  BUTTON_IMAGE_CHANGE= "image_change";
    private static final String  BUTTON_OPTION= "option";
    private static int idAvatar=0;
    private static final int[] listImageAvatar = { R.drawable.starwars1, R.drawable.starwars2,R.drawable.starwars3,R.drawable.starwars4,R.drawable.starwars5,R.drawable.starwars6,R.drawable.starwars7,
            R.drawable.starwars8,R.drawable.starwars9,R.drawable.starwars10,R.drawable.starwars11,R.drawable.starwars12,R.drawable.starwars13,R.drawable.starwars14,R.drawable.starwars15};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Random rand = new Random();
        int x=rand.nextInt(listImageAvatar.length);
        ImageView avatar=findViewById(R.id.avatarImage);
        avatar.setImageResource(listImageAvatar[x]);
        this.idAvatar=x;
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

            editor.putInt("idAvatar",idAvatar);

            Intent intent = new Intent (MainActivity.this, GameActivity.class);
            EditText nickName = findViewById(R.id.nickName);
            intent.putExtra("nickName",nickName.getText());
            editor.putString("nickName", nickName.getText().toString());
            editor.apply();
            startActivity(intent);
        }
        if(V.getTag().equals(BUTTON_IMAGE_CHANGE)){

            Random rand = new Random();
            int x=rand.nextInt(listImageAvatar.length);
            ImageView avatar=findViewById(R.id.avatarImage);
            avatar.setImageResource(listImageAvatar[x]);
            this.idAvatar=x;

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