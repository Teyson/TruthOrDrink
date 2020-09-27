package com.teyson.domain;

import com.teyson.interfaces.IPersistence;

import java.util.List;

public class Game {
    private static Game instance;
    private static IPersistence persistence;

    private List<Question> availableQuestions;
    private List<Question> allQuestions;

    private Game(){
        persistence = IPersistence.getInstance();
        this.availableQuestions = persistence.loadQuestions();
        this.allQuestions = this.availableQuestions;
    }

    public static Game getInstance(){
        if(instance == null){
            instance = new Game();
        }

        return instance;
    }

    private void reloadQuestions(){
        this.availableQuestions = persistence.loadQuestions();
    }

    private Question getNewQuestion(){
        int questionIndex = (int)Math.round(Math.random() * (float) this.availableQuestions.size());

        return this.availableQuestions.remove(questionIndex);
    }

    public List<Question> getAvailableQuestions() {
        return availableQuestions;
    }

    public void setAvailableQuestions(List<Question> availableQuestions) {
        this.availableQuestions = availableQuestions;
    }

    public List<Question> getAllQuestions() {
        return allQuestions;
    }

    public void setAllQuestions(List<Question> allQuestions) {
        this.allQuestions = allQuestions;
    }
}
