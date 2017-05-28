package com.university.ui.control;

import com.university.db.control.DBController;
import com.university.db.control.GroupController;
import com.university.db.control.StudentController;
import com.university.db.entity.GroupsEntity;
import com.university.db.entity.StudentsEntity;
import com.university.externalFile.UpLoadToDB;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by maksymmikitiuk on 4/9/17.
 */
public class LoadStudentController implements Initializable {
    public Button open_file, cancel, load;
    public TextField path_field;
    private List<StudentsEntity> list;

    @FXML
    TableView<StudentsEntity> tableStudents;
    @FXML
    TableColumn<StudentsEntity, String> tableStudentsLName;
    @FXML
    TableColumn<StudentsEntity, String> tableStudentsFName;
    @FXML
    TableColumn<StudentsEntity, String> tableStudentsMName;
    @FXML
    TableColumn<StudentsEntity, GroupsEntity> tableStudentsGroup;
    @FXML
    TableColumn<StudentsEntity, String> tableStudentsIdCard;
    @FXML
    TableColumn<StudentsEntity, String> tableStudentsIdPhone;
    @FXML
    TableColumn<StudentsEntity, String> tableStudentsIdMail;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableStudents.setEditable(true);
        tableStudentsLName.setCellValueFactory(new PropertyValueFactory("lastName"));
        tableStudentsLName.setCellFactory(TextFieldTableCell.<StudentsEntity>forTableColumn());
        tableStudentsLName.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<StudentsEntity, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<StudentsEntity, String> event) {
                StudentsEntity entity = tableStudents.getSelectionModel().getSelectedItem();
                list.remove(list.indexOf(entity));
                entity.setLastName(event.getNewValue());
                list.add(entity);
            }
        });
        tableStudentsFName.setCellValueFactory(new PropertyValueFactory("firstName"));
        tableStudentsFName.setCellFactory(TextFieldTableCell.<StudentsEntity>forTableColumn());
        tableStudentsFName.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<StudentsEntity, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<StudentsEntity, String> event) {
                StudentsEntity entity = tableStudents.getSelectionModel().getSelectedItem();
                list.remove(list.indexOf(entity));
                entity.setFirstName(event.getNewValue());
                list.add(entity);
            }
        });
        tableStudentsMName.setCellValueFactory(new PropertyValueFactory("middleName"));
        tableStudentsMName.setCellFactory(TextFieldTableCell.<StudentsEntity>forTableColumn());
        tableStudentsMName.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<StudentsEntity, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<StudentsEntity, String> event) {
                StudentsEntity entity = tableStudents.getSelectionModel().getSelectedItem();
                list.remove(list.indexOf(entity));
                entity.setMiddleName(event.getNewValue());
                list.add(entity);
            }
        });
        tableStudentsGroup.setCellValueFactory(new PropertyValueFactory("idgroups"));
        tableStudentsGroup.setCellFactory(ComboBoxTableCell.<StudentsEntity, GroupsEntity>forTableColumn(FXCollections.<GroupsEntity>observableArrayList(new GroupController().getAllGroup())));
        tableStudentsGroup.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<StudentsEntity, GroupsEntity>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<StudentsEntity, GroupsEntity> event) {

            }
        });
        tableStudentsIdCard.setCellValueFactory(new PropertyValueFactory("studentidcard"));
        tableStudentsIdCard.setCellFactory(TextFieldTableCell.<StudentsEntity>forTableColumn());
        tableStudentsIdCard.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<StudentsEntity, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<StudentsEntity, String> event) {
                StudentsEntity entity = tableStudents.getSelectionModel().getSelectedItem();
                list.remove(list.indexOf(entity));
                entity.setStudentidcard(event.getNewValue());
                list.add(entity);
            }
        });

        tableStudentsIdPhone.setCellValueFactory(new PropertyValueFactory("phone"));
        tableStudentsIdPhone.setCellFactory(TextFieldTableCell.<StudentsEntity>forTableColumn());
        tableStudentsIdPhone.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<StudentsEntity, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<StudentsEntity, String> event) {
                StudentsEntity entity = tableStudents.getSelectionModel().getSelectedItem();
                list.remove(list.indexOf(entity));
                entity.setPhone(event.getNewValue());
                list.add(entity);
            }
        });

        tableStudentsIdMail.setCellValueFactory(new PropertyValueFactory("email"));
        tableStudentsIdMail.setCellFactory(TextFieldTableCell.<StudentsEntity>forTableColumn());
        tableStudentsIdMail.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<StudentsEntity, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<StudentsEntity, String> event) {
                StudentsEntity entity = tableStudents.getSelectionModel().getSelectedItem();
                list.remove(list.indexOf(entity));
                entity.setEmail(event.getNewValue());
                list.add(entity);
            }
        });

        open_file.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                File file = new FileChooser().showOpenDialog(open_file.getScene().getWindow());
                if (file != null) {
                    list = new UpLoadToDB().upLoadToUser(file.getPath());
                    path_field.setText(file.getPath());
                    tableStudents.getItems().clear();
                    tableStudents.getItems().addAll(FXCollections.observableArrayList(
                            list));
                    open_file.setText("Змінити файл");
                }
            }
        });

        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cancel.getScene().getWindow().hide();
            }
        });

        load.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadStudent();
                load.getScene().getWindow().hide();
            }
        });
    }

    private void loadStudent() {
        for (StudentsEntity entity:list)
            if(new StudentController().checkStudent(entity))
                new DBController().create(entity);
    }
}
