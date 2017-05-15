package com.university.ui.control;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by maksymmikitiuk on 4/9/17.
 */
public class DeleteDialogController implements Initializable {
    public Button DELETE_YES, DELETE_NO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DELETE_YES.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MainActivityController.DELETE = true;
                DELETE_YES.getScene().getWindow().hide();
            }
        });

        DELETE_NO.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MainActivityController.DELETE = false;
                DELETE_NO.getScene().getWindow().hide();
            }
        });
    }
}
