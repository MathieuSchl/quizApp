package com.cazel.myapplication.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cazel.myapplication.R;
import com.cazel.myapplication.models.GameData;
import com.cazel.myapplication.models.Question;

import org.json.JSONObject;

public class GameActivity extends AppCompatActivity implements QuestionsLoaderAsyncInterface, View.OnClickListener {
private GameData game;
private Question actualQuestion;
private LinearLayout answerButtonZone;
private static final String  BUTTON_TRUE= "True";
private static final String  BUTTON_FALSE= "False";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        QuestionsLoaderAsyncTask questionsLoaderAsyncTask = new QuestionsLoaderAsyncTask(this);
        questionsLoaderAsyncTask.execute("https://opentdb.com/api.php?amount=6&type=boolean");
    }

    @Override
    public void onFinish(JSONObject json) {

        Bundle extra = getIntent().getExtras();
        String nickName = extra.getString("nickName");
        this.game = new GameData(json,nickName);
        start_game(this.game.getActualQuestion());

    }
    public void start_game(Question question){

        this.actualQuestion=question;
        show_new_question(question);
        updateLinearLayout();

    }

    @Override
    public void onBackPressed() {
    }

    public void show_new_question(Question question){
        TextView question_container=findViewById(R.id.questionContent);
        TextView question_number=findViewById(R.id.questionNumber);
        TextView question_type=findViewById(R.id.questionType);
        TextView question_difficulty=findViewById(R.id.questionDifficulty);
        question_number.setText("Question: "+this.game.getActualIndexQuestion()+" / "+this.game.getNbQuestions());
        question_type.setText(question.getCategory());
        question_difficulty.setText(question.getDifficulty());
        question_container.setText(question.getQuestion());
    }

    public void updateLinearLayout(){
        this.answerButtonZone = (LinearLayout) findViewById(R.id.linearLayoutAnswerButton);
        this.answerButtonZone.removeAllViews();
        if(this.actualQuestion.getType().equals("boolean")){
            createButton("True");
            createButton("False");
        }
        else if (this.actualQuestion.getType().equals("multiple")){
            createButtons(4);
        }

    }

    public void createButton(String text){
        Button button = new Button(this);
        button.setText(text);
        button.setOnClickListener(this);
        button.setTag(text);
        this.answerButtonZone.addView(button, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
    }
    public void createButtons(int n){
        for(int i=0;i<n;i++){
            String[] answers=this.actualQuestion.getAnswers();
            createButton(answers[i]);
        }
    }


    @Override
    public void onClick(View V) {
        if(V.getTag().equals("True")){
            sendAnswer(V.getTag().toString());
        }
        if(V.getTag().equals("False")){
            sendAnswer(V.getTag().toString());
        }
    }

    public void sendAnswer(String answer){
        this.game.answerToActualQuestion(answer);

        if (this.game.getActualIndexQuestion() < this.game.getNbQuestions()){
            start_game(this.game.getActualQuestion());
        }else {
            // Start score Activity
        }
    }
}