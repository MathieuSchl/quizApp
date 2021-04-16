package com.cazel.myapplication.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cazel.myapplication.R;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

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
            startActivity(intent);
        }
    }
}