package com.university.ui.control;

import com.university.comboBox.FillComboBox;
import com.university.db.control.DBController;
import com.university.db.control.GroupController;
import com.university.db.control.StudentController;
import com.university.db.entity.GroupsEntity;
import com.university.db.entity.StudentsEntity;
import com.university.ui.widgets.MaskField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentActivityController implements Initializable {

    public Button create, cancel;
    public ComboBox groupList;
    public MaskField studentId, phone;
    public TextField lName, fName, mName, email;
    public Pane createGroup;
    private boolean newStudent = true;
    private StudentsEntity student;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new FillComboBox(groupList).fillGroup();

        create.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (checkFill()) {
                    if (newStudent)
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

        studentId.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                studentId.getStyleClass().add("true_field");
            }
        });

        phone.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                phone.getStyleClass().add("true_field");
            }
        });

        email.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                email.getStyleClass().add("true_field");
            }
        });

        groupList.valueProperty().addListener(new ChangeListener<GroupsEntity>() {
            @Override
            public void changed(ObservableValue<? extends GroupsEntity> observable, GroupsEntity oldValue, GroupsEntity newValue) {
                groupList.getStyleClass().add("true_field");
            }
        });

        cancel.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                cancel.getScene().getWindow().hide();
            }
        });

        createGroup.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                createGroup();
            }
        });
    }

    private void update() {
        student.setFirstName(fName.getText());
        student.setMiddleName(mName.getText());
        student.setLastName(lName.getText());
        student.setIdgroups((GroupsEntity) groupList.getValue());
        student.setStudentidcard(studentId.getPlainText());
        student.setEmail(email.getText());
        student.setPhone(phone.getPlainText());

        if(!new DBController().update(student)){

        }
    }

    private void createGroup() {

    }

    public void fillForm(int id) {
        create.setText("Зберегти");
        newStudent = false;

        student = new StudentController().getStudentById(id);
        fName.setText(student.getFirstName());
        lName.setText(student.getLastName());
        mName.setText(student.getMiddleName());
        email.setText(student.getEmail());
        studentId.setPlainText(student.getStudentidcard());
        phone.setPlainText(student.getPhone());
        groupList.getSelectionModel().select(new GroupController().getGroupById(student.getIdgroups().getIdgroups()));
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
        if (studentId.getText().isEmpty()) {
            studentId.getStyleClass().add("false_field");
            check = false;
        }
//        if (phone.getText().isEmpty()) {
//            phone.getStyleClass().add("false_field");
//            check = false;
//        }
//        if (email.getText().isEmpty()) {
//            email.getStyleClass().add("false_field");
//            check = false;
//        }
        if (groupList.valueProperty().isNull().getValue()) {
            groupList.getStyleClass().add("false_field");
            check = false;
        }

        return check;
    }

    private void create() {
        student = new StudentsEntity();
        student.setFirstName(fName.getText());
        student.setMiddleName(mName.getText());
        student.setLastName(lName.getText());
        student.setIdgroups((GroupsEntity) groupList.getValue());
        student.setStudentidcard(studentId.getPlainText());
        student.setEmail(email.getText());
        student.setPhone(phone.getPlainText());

        if (!new DBController().create(student)) {

        }
    }
}
