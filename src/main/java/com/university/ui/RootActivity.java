package com.university.ui;

import com.university.db.control.DBController;
import com.university.db.entity.UsersEntity;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RootActivity extends Application{

    public static UsersEntity currentUserInformation;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(final Stage stage) throws Exception {
        DBController.getSession();
        DBController.setStage(stage);
        Parent root = (Parent)FXMLLoader.load(RootActivity.class.getResource("/ui/view/rootActivity.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.DECORATED);
        stage.centerOnScreen();
        stage.getIcons().add(new Image(RootActivity.class.getResourceAsStream("/ui/img/icon.png")));
        stage.show();
    }


}
