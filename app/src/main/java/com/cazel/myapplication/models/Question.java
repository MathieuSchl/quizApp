package com.cazel.myapplication.models;

import android.util.Log;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Random;

public class Question {
    private final String category;
    private final String type;
    private final String difficulty;
    private final String question;
    private final String correct_answer;
    private final String[] incorrect_answers;
    private final Boolean starWarsQuestion;
    private final int indexQuestion;
    private String playerAnswer;
    private Boolean isCorrectAnswer;

    public Question(String category, String type, String difficulty, String question, String correct_answer, String[] incorrect_answers, int indexQuestion){
        this.category = category;
        this.type = type;
        this.difficulty = difficulty;
        this.question = question;
        this.correct_answer = correct_answer;
        this.incorrect_answers = incorrect_answers;
        this.indexQuestion = indexQuestion;
        this.starWarsQuestion = testIfStarWarsQuestion(this.question);
    }

    public String getCategory(){
        return this.category;
    }

    public String getType(){
        return this.type;
    }

    public String getQuestion(){
        return this.question;
    }

    public String getCorrect_answer(){
        return this.correct_answer;
    }

    public String getDifficulty(){
        return this.difficulty;
    }

    public String[] getIncorrect_answers(){
        return this.incorrect_answers;
    }

    public String getPlayerAnswer(){
        return this.playerAnswer;
    }

    public Boolean getIsCorrectAnswer(){
        return this.isCorrectAnswer;
    }

    public Boolean getStarWarsQuestion(){
        return this.starWarsQuestion;
    }

    public int getindexQuestion(){
        return this.indexQuestion;
    }

    private boolean testIfStarWarsQuestion(String question){
        String questionLowerCase = question.toLowerCase();
        String[] starWarsWords = new String[]{"starwars","star wars","droid","jedi","anakin","r2d2","r2-d2","c3po","vador","palpatine"};
        for (int i = 0; i < starWarsWords.length; i++) {
            if(questionLowerCase.split(starWarsWords[i]).length!=1){
                return true;
            }
        }
        return false;
    }

    public Boolean userSetAnswer(String answer){
        this.playerAnswer = answer;
        if(this.correct_answer.equals(this.playerAnswer)){
            this.isCorrectAnswer=true;
        }else{
            this.isCorrectAnswer=false;
        }
        return this.isCorrectAnswer;
    }

    public String[] getAnswers(){
        String[] allAnswers = new String[incorrect_answers.length + 1];
        System.arraycopy(incorrect_answers, 0, allAnswers, 0, incorrect_answers.length);
        allAnswers[incorrect_answers.length] = correct_answer;

        Random rand = new Random();

        for (int i = 0; i < allAnswers.length; i++) {
            int randomIndexToSwap = rand.nextInt(allAnswers.length);
            String temp = allAnswers[randomIndexToSwap];
            allAnswers[randomIndexToSwap] = allAnswers[i];
            allAnswers[i] = temp;
        }
        return allAnswers;
    }
}
