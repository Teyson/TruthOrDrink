package com.teyson.presentation;

import com.teyson.domain.Game;
import com.teyson.domain.Question;
import com.teyson.interfaces.IPersistence;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class QuestionOptionsController implements Initializable {

    private final Game game = Game.getInstance();
    private final IPersistence persistence = IPersistence.getInstance();
    public ListView<Question> questionList;
    public TextField questionTxtbox;
    private ObservableList<Question> allQuestions = FXCollections.observableArrayList(game.getAllQuestions());

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allQuestions.addListener(new ListChangeListener<Question>() {
            @Override
            public void onChanged(Change<? extends Question> change) {

            }
        });

        questionList.setItems(allQuestions);
    }

    public void addBtnHandler(ActionEvent actionEvent) {
        Question q = new Question(persistence.getHighestId() + 1, this.questionTxtbox.getText());
        if (!allQuestions.contains(q)) {
            allQuestions.add(q);
            game.getAllQuestions().add(q);
            persistence.addQuestion(q);
        }
    }

    public void removeBtnHandler(ActionEvent actionEvent) {
        Question selectedQuestion = questionList.getSelectionModel().getSelectedItem();
        allQuestions.remove(selectedQuestion);
        persistence.removeQuestion(selectedQuestion);
    }


    public void backBtnHandler(ActionEvent actionEvent) {
        try {
            App.setRoot("startGame");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
