package com.university.ui;

import com.university.db.control.dbController;
import com.university.db.entity.UsersEntity;
import com.university.ui.control.StudentActivityController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class studentActivity extends Application {

    public static UsersEntity currentUserInformation;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(final Stage stage) throws Exception {
        dbController.getSession();
        dbController.setStage(stage);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/view/studentActivity.fxml"));
        stage.setScene(new Scene((Pane) loader.load()));
        stage.initStyle(StageStyle.UNDECORATED);
        StudentActivityController controller = loader.<StudentActivityController>getController();
        controller.fillForm(1);

        stage.show();
    }


}
