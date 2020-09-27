package com.teyson.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartGameController implements Initializable {

    public StartGameController() {
    }

    public void startGameHandler(ActionEvent actionEvent) {

    }

    public void optionsHandler(ActionEvent actionEvent) {
        try {
            App.setRoot("questionOptions");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void rulesHandler(ActionEvent actionEvent) {
    }

    public void creditsHandler(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
