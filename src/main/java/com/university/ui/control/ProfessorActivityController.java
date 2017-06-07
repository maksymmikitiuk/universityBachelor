package com.university.ui.control;

import com.university.comboBox.FillComboBox;
import com.university.db.control.ChairController;
import com.university.db.control.DBController;
import com.university.db.control.TeacherController;
import com.university.db.entity.ChairsEntity;
import com.university.db.entity.TeachersEntity;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by maksymmikitiuk on 4/15/17.
 */
public class ProfessorActivityController implements Initializable {
    public Button create, cancel;
    public ComboBox chairList;
    public TextField lName, fName, mName;
    private boolean newTeacher = true;
    private TeachersEntity teacher;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new FillComboBox(chairList).fillChair();

        create.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (checkFill()) {
                    if (newTeacher)
                        create();
                    else
                        update();
                    create.getScene().getWindow().hide();
                }

            }
        });

        fName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                fName.getStyleClass().add("true_field");
            }
        });

        lName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                lName.getStyleClass().add("true_field");
            }
        });

        mName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                mName.getStyleClass().add("true_field");
            }
        });

        chairList.valueProperty().addListener(new ChangeListener<ChairsEntity>() {
            @Override
            public void changed(ObservableValue<? extends ChairsEntity> observable, ChairsEntity oldValue, ChairsEntity newValue) {
                chairList.getStyleClass().add("true_field");
            }
        });

        cancel.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                cancel.getScene().getWindow().hide();
            }
        });
    }

    private void update() {
        teacher.setFirstName(fName.getText());
        teacher.setMiddleName(mName.getText());
        teacher.setLastName(lName.getText());
        teacher.setIdchairs((ChairsEntity) chairList.getValue());

        if(!new DBController().update(teacher)){

        }
    }

    public void fillForm(int id) {
        create.setText("Зберегти");
        newTeacher = false;

        teacher = new TeacherController().getTeacherById(id);
        fName.setText(teacher.getFirstName());
        lName.setText(teacher.getLastName());
        mName.setText(teacher.getMiddleName());
        chairList.getSelectionModel().select(new ChairController().getChairById(teacher.getIdchairs().getIdchairs()));
    }

    private boolean checkFill() {
        boolean check = true;

        if (fName.getText().isEmpty()) {
            fName.getStyleClass().add("false_field");
            check = false;
        }

        if (mName.getText().isEmpty()) {
            mName.getStyleClass().add("false_field");
            check = false;
        }

        if (lName.getText().isEmpty()) {
            lName.getStyleClass().add("false_field");
            check = false;
        }

        if (chairList.valueProperty().isNull().getValue()) {
            chairList.getStyleClass().add("false_field");
            check = false;
        }

        return check;
    }

    private void create() {
        teacher= new TeachersEntity();
        teacher.setFirstName(fName.getText());
        teacher.setMiddleName(mName.getText());
        teacher.setLastName(lName.getText());
        teacher.setIdchairs((ChairsEntity) chairList.getValue());

        if (!new DBController().create(teacher)) {

        }
    }
}
