package com.university.ui.control;

import com.university.comboBox.FillComboBox;
import com.university.db.control.MarksController;
import com.university.db.entity.DiplomamarksEntity;
import com.university.db.entity.DiplomasubjectsEntity;
import com.university.db.entity.TeachersEntity;
import com.university.db.entity.TypeOwnerMarkEntity;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

/**
 * Created by maksymmikitiuk on 5/26/17.
 */
public class AddMarkController implements Initializable {
    public ComboBox type;
    public TextField teacher, ects, nationalScale, points;
    public Button ok, cancel, selectTeacher;

    private DiplomamarksEntity diplomamarks = new DiplomamarksEntity();
    private Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectTeacher.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = new Stage();
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/view/getData.fxml"));
                    stage.setScene(new Scene((Pane) loader.load()));
                    GetDataController controller = loader.<GetDataController>getController();
                    controller.setStage(stage);
                    controller.setVisible(1);
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(selectTeacher.getScene().getWindow());
                    stage.centerOnScreen();
                    stage.showAndWait();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (stage.getUserData() != null) {
                    diplomamarks.setOwner((TeachersEntity) stage.getUserData());
                    teacher.setText(diplomamarks.getOwner().getLastName() + " "
                            + diplomamarks.getOwner().getFirstName().charAt(0) + ". "
                            + diplomamarks.getOwner().getMiddleName().charAt(0) + ".");
                }
            }
        });

        new FillComboBox(type).fillTeacherType();

        points.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.length() > 3)
                    points.setText(points.getText().substring(0, 3));
                else if (newValue.length() == 0) {
                    ects.setText("");
                    nationalScale.setText("");
                } else if (Integer.valueOf(newValue) > 100)
                    points.setText("100");

                fillMarks();
            }
        });

        ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                diplomamarks.setTypeOwner((TypeOwnerMarkEntity) type.getSelectionModel().getSelectedItem());
                stage.setUserData(diplomamarks);
                ok.getScene().getWindow().hide();
            }
        });

        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setUserData(null);
                cancel.getScene().getWindow().hide();
            }
        });
    }

    public void setDiplomasubjects(DiplomasubjectsEntity diplomasubjects) {
        diplomamarks.setId_diploma(diplomasubjects);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }


    private void fillMarks() {
        if (Pattern.matches("\\d+", points.getText())) {
            diplomamarks.setPoint(Integer.valueOf(points.getText()));
            diplomamarks.setMark(new MarksController().getMarksByPoints(Integer.valueOf(points.getText())));
            ects.setText(diplomamarks.getMark().getEcts());
            nationalScale.setText(diplomamarks.getMark().getNationalscale() + " (" + diplomamarks.getMark().getNationalscalenumber() + ")");
        }
    }
}
