package com.university.ui;

import com.university.db.control.dbController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class testActivity extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        //getSession();
        dbController.setStage(stage);
        Parent root = (Parent) FXMLLoader.load(rootActivity.class.getResource("/ui/view/createSubjectActivity.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.centerOnScreen();
        stage.show();
    }
}
