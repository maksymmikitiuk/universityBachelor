package com.university.ui.control;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by maksymmikitiuk on 6/1/17.
 */
public class ExitController implements Initializable{
    public Button EXIT_YES, EXIT_NO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        EXIT_YES.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MainActivityController.EXIT = true;
                EXIT_YES.getScene().getWindow().hide();
            }
        });

        EXIT_NO.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MainActivityController.EXIT= false;
                EXIT_NO.getScene().getWindow().hide();
            }
        });
    }
}
