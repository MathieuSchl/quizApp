package com.cazel.myapplication.models;

import org.json.JSONObject;

public class GameData {
    private String category;
    private String type;
    private Integer nbQuestion;
    private Integer actualQuestion;
    private Question[] questionList;

    public GameData(String category,String type,Integer nbQuestion){
        this.category = category;
        this.type = type;
        this.nbQuestion = nbQuestion;
        this.actualQuestion = 0;

        this.generateQuestions();
    }

    private void generateQuestions(){
        JSONObject res = apiCall();

        for( JSONObject element : res.results ) {
            Question question = new question(element.category, element.type, element.difficulty, element.question, element.correct_answer, element.incorrect_answers);
            this.questionList.push(question);
        }
    }

    public void answerToActualQuestion(String answer){
        this.questionList[this.actualQuestion].userSetAnswer(answer);
        this.actualQuestion++;
    }
}
