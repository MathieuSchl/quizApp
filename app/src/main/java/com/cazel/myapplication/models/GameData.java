package com.cazel.myapplication.models;

import android.text.Html;

import org.json.JSONException;
import org.json.JSONObject;
import com.cazel.myapplication.controllers.GameActivity;
import com.cazel.myapplication.controllers.QuestionsLoaderAsyncTask;

import java.util.Map;

public class GameData {
    private static GameData instance;

    private String nickName;
    private Integer id_avatar;
    private String category;
    private String type;
    private Integer nbQuestion;
    private Integer actualQuestion;
    private Question[] questionList = new Question[0];

    public GameData(JSONObject json, String nickName, Integer id_avatar){
        this.nickName = nickName;
        this.id_avatar = id_avatar;
        try {
            this.nbQuestion = json.getJSONArray("results").length();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.actualQuestion = 0;

        this.generateQuestions(json);
        instance=this;
    }

    public static GameData getInstance(){
        return instance;
    }
    private void generateQuestions(JSONObject json){
        try {
            for (int index = 0; index < json.getJSONArray("results").length(); index++) {
                JSONObject element = json.getJSONArray("results").getJSONObject(index);
                String category = element.getString("category");
                String type = element.getString("type");
                String difficulty = element.getString("difficulty");
                String question = Html.fromHtml(element.getString("question")).toString();
                String correct_answer = element.getString("correct_answer");
                String[] incorrect_answers = new String[0];//element.getJSONArray("incorrect_answers");
                for (int indexIncorrect_answers = 0; indexIncorrect_answers < element.getJSONArray("incorrect_answers").length(); indexIncorrect_answers++) {
                    String[] temp = new String[incorrect_answers.length + 1];
                    System.arraycopy(incorrect_answers, 0, temp, 0, incorrect_answers.length);
                    temp[incorrect_answers.length] = element.getJSONArray("incorrect_answers").getString(indexIncorrect_answers);
                    incorrect_answers = temp;
                }

                Question newQuestion = new Question(category, type, difficulty, question, correct_answer, incorrect_answers);

                Question[] temp = new Question[this.questionList.length + 1];
                System.arraycopy(this.questionList, 0, temp, 0, this.questionList.length);
                temp[this.questionList.length] = newQuestion;
                this.questionList = temp;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void answerToActualQuestion(String answer){
        if(this.actualQuestion<this.nbQuestion){
            this.questionList[this.actualQuestion].userSetAnswer(answer);
            this.actualQuestion++;
        }
    }

    public Question getActualQuestion(){
        try {
            return this.questionList[this.actualQuestion];
        }catch (Exception e){
            return new Question("Error", "Error", "Error", "Error", "Error", new String[]{ "Error" });
        }
    }

    public Integer getActualIndexQuestion(){
        return this.actualQuestion+1;
    }

    public Integer getNbQuestions(){
        return this.nbQuestion;
    }
    public String getNickName(){
        return this.nickName;
    }
}
