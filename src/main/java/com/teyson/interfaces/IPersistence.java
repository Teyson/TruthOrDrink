package com.teyson.interfaces;

import com.teyson.domain.Question;
import com.teyson.persistence.Persistence;

import java.util.List;

public interface IPersistence {
    static Persistence getInstance() {
        return Persistence.getInstance();
    }

    boolean addQuestion(Question q);
    boolean removeQuestion(Question q);
    boolean saveQuestions();
    int getHighestId();
    List<Question> loadQuestions();

}
