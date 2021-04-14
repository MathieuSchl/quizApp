package com.cazel.myapplication.models;

import org.json.JSONException;
import org.json.JSONObject;
import com.cazel.myapplication.controllers.GameActivity;
import com.cazel.myapplication.controllers.QuestionsLoaderAsyncTask;

import java.util.Map;

public class GameData {
    private String category;
    private String type;
    private Integer nbQuestion;
    private Integer actualQuestion;
    private Question[] questionList;

    public GameData(JSONObject json){
        try {
            this.nbQuestion = json.getJSONArray("results").length();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.actualQuestion = 0;

        this.generateQuestions(json);
    }

    private void generateQuestions(JSONObject json){

        try {
            for (int index = 0; index < json.getJSONArray("results").length(); index++) {
                JSONObject element = json.getJSONArray("results").getJSONObject(index);
                String category = element.getString("category");
                String type = element.getString("type");
                String difficulty = element.getString("difficulty");
                String question = element.getString("question");
                String correct_answer = element.getString("correct_answer");
                String[] incorrect_answers = new String[0];//element.getJSONArray("incorrect_answers");
                for (int indexIncorrect_answers = 0; indexIncorrect_answers < element.getJSONArray("incorrect_answers").length(); indexIncorrect_answers++) {
                    String[] temp = new String[incorrect_answers.length + 1];
                    System.arraycopy(incorrect_answers, 0, temp, 0, incorrect_answers.length);
                    temp[incorrect_answers.length] = element.getJSONArray("incorrect_answers").getString(indexIncorrect_answers);
                    incorrect_answers = temp;
                }

                Question newQuestion = new Question(category, type, difficulty, question, correct_answer, incorrect_answers);

                for (int indexQuestion = 0; indexQuestion < this.questionList.length; indexQuestion++) {
                    Question[] temp = new Question[this.questionList.length + 1];
                    System.arraycopy(this.questionList, 0, temp, 0, this.questionList.length);
                    temp[this.questionList.length] = newQuestion;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void answerToActualQuestion(String answer){
        this.questionList[this.actualQuestion].userSetAnswer(answer);
        this.actualQuestion++;
    }
}
