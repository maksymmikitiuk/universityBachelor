package com.university.ui.control;

import com.university.db.entity.StudentsEntity;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by maksymmikitiuk on 5/7/17.
 */
public class GetDataController implements Initializable {
    Stage stage;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
        StudentsEntity entity = new StudentsEntity();
        entity.setFirstName("Maks");
        stage.setUserData(entity);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
