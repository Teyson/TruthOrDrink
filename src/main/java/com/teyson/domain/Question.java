package com.teyson.domain;

import java.util.Objects;

public class Question implements Comparable{
    private final int id;
    private String question;

    public Question(int id, String question) {
        this.id = id;
        this.question = question;
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        //Checks if the reference is the same
        if (this == o) return true;

        //checks if the input is even the right class
        if (o == null || getClass() != o.getClass()) return false;

        //converts the input to a Question
        Question question1 = (Question) o;

        //Checks if the id is the same
        if (this.id == question1.getId()) return true;

        //Checks if the question text is the same
        return this.question.equals(question1.getQuestion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, question);
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Question){
            return this.id - ((Question) o).getId();
        }

        return 0;
    }

    @Override
    public String toString() {
        return this.question;
    }
}
