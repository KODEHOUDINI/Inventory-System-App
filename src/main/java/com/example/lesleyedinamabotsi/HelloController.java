package com.example.lesleyedinamabotsi;

import com.example.lesleyedinamabotsi.data.Good;
import com.example.lesleyedinamabotsi.data.SOURCE;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.List;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {

        welcomeText.setText("Welcome to JavaFX Application!");
        //testing the database
    }
}