package com.university.ui;

import com.university.db.control.dbController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class settingsActivity extends Application {


    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(final Stage stage) throws Exception {
        dbController.getSession();
        dbController.setStage(stage);

        Parent root = (Parent) FXMLLoader.load(rootActivity.class.getResource("/ui/view/settingsActivity.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }


}
