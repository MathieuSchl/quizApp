
package com.cazel.myapplication.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.cazel.myapplication.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String  BUTTON_START= "start";
    private static final String  BUTTON_IMAGE_CHANGE= "image_change";
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
        }

    @Override
    public void onClick(View V) {

        if(V.getTag().equals(BUTTON_START)){
            Intent intent = new Intent (MainActivity.this, GameActivity.class);
            EditText nickName = findViewById(R.id.nickName);
            intent.putExtra("nickName",nickName.getText());
            startActivity(intent);
        }
        if(V.getTag().equals(BUTTON_IMAGE_CHANGE)){


        }


    }
}