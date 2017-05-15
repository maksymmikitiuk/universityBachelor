package com.university.ui;

import com.university.db.control.dbController;
import com.university.db.entity.UsersEntity;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;

public class rootActivity extends Application{

    public static UsersEntity currentUserInformation;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(final Stage stage) throws Exception {
        dbController.getSession();
        dbController.setStage(stage);
        Parent root = (Parent)FXMLLoader.load(rootActivity.class.getResource("/ui/view/rootActivity.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.DECORATED);
        stage.centerOnScreen();
        stage.getIcons().add(new Image(rootActivity.class.getResourceAsStream("/ui/img/icon.png")));
        stage.show();
    }


}
