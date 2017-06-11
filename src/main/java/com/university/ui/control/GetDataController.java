package com.university.ui.control;

import com.university.comboBox.FillComboBox;
import com.university.db.control.StudentController;
import com.university.db.control.TeacherController;
import com.university.db.entity.*;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

/**
 * Created by maksymmikitiuk on 5/7/17.
 */
public class GetDataController implements Initializable {
    public AnchorPane PROFESSOR;
    public AnchorPane STUDENT;
    public AnchorPane STUDENT_P_FILTER, STUDENT_P_MAIN;
    public Label STUDENT_P_FILTER_CLEAR;
    public ComboBox STUDENT_P_FILTER_GROUP, STUDENT_P_FILTER_CHAIRS, STUDENT_P_FILTER_GROUPTYPE,
            STUDENT_P_FILTER_QUALIFICATION, STUDENT_P_FILTER_FACULTY;
    public Button STUDENT_P_CREATE, STUDENT_P_LOAD;
    public TextField SEARCH;
    private boolean STUDENT_P_IS_FILTER;
    public Label STUDENT_P_FILTER_GROUP_CLEAR, STUDENT_P_FILTER_CHAIRS_CLEAR, STUDENT_P_FILTER_GROUPTYPE_CLEAR,
            STUDENT_P_FILTER_QUALIFICATION_CLEAR;
    public Button SELECT, CANCEL;
    public int I;

    @FXML
    TableView<StudentsEntity> tableStudents;
    @FXML
    TableColumn<StudentsEntity, String> tableStudentsLName;
    @FXML
    TableColumn<StudentsEntity, String> tableStudentsFName;
    @FXML
    TableColumn<StudentsEntity, String> tableStudentsMName;
    @FXML
    TableColumn<StudentsEntity, String> tableStudentsGroup;
    @FXML
    TableColumn<StudentsEntity, String> tableStudentsIdCard;

    public AnchorPane PROFESSOR_P_MAIN;
    public Label PROFESSOR_P_FILTER_CLEAR;
    public ComboBox PROFESSOR_P_FILTER_CHAIRS, PROFESSOR_P_FILTER_FACULTY;
    public Button PROFESSOR_P_CREATE, PROFESSOR_P_LOAD;
    private boolean PROFESSOR_P_IS_FILTER;
    public Label PROFESSOR_P_FILTER_CHAIRS_CLEAR, PROFESSOR_P_FILTER_FACULTY_CLEAR;
    @FXML
    TableView<TeachersEntity> tableProfessor;
    @FXML
    TableColumn<TeachersEntity, String> tableProfessorLName;
    @FXML
    TableColumn<TeachersEntity, String> tableProfessorFName;
    @FXML
    TableColumn<TeachersEntity, String> tableProfessorMName;
    @FXML
    TableColumn<TeachersEntity, String> tableProfessorChair;

    Stage stage;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SELECT.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SEARCH.getScene().getWindow().hide();
            }
        });

        CANCEL.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setUserData((I == 0) ? new StudentsEntity() : new TeachersEntity());
                SEARCH.getScene().getWindow().hide();
            }
        });
    }

    private void student() {
        STUDENT.setVisible(true);

        initStudentTable();

        tableStudents.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                    selectStudent(tableStudents.getSelectionModel().getSelectedItem());
                } else if (event.isPrimaryButtonDown() && event.getClickCount() == 1) {
                    stage.setUserData(tableStudents.getSelectionModel().getSelectedItem());
                }
            }
        });

        STUDENT_P_FILTER_CLEAR.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clearStudentFilter();
            }
        });

        SEARCH.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                updateStudentTableByParameter();
            }
        });

        STUDENT_P_FILTER_CHAIRS.valueProperty().addListener(new ChangeListener<ChairsEntity>() {
            @Override
            public void changed(ObservableValue<? extends ChairsEntity> observable, ChairsEntity oldValue, ChairsEntity newValue) {
                updateStudentTableByParameter();
            }
        });

        STUDENT_P_FILTER_FACULTY.valueProperty().addListener(new ChangeListener<GroupsEntity>() {
            @Override
            public void changed(ObservableValue<? extends GroupsEntity> observable, GroupsEntity oldValue, GroupsEntity newValue) {
                updateStudentTableByParameter();
            }
        });

        STUDENT_P_FILTER_GROUP.valueProperty().addListener(new ChangeListener<GroupsEntity>() {
            @Override
            public void changed(ObservableValue<? extends GroupsEntity> observable, GroupsEntity oldValue, GroupsEntity newValue) {
                updateStudentTableByParameter();
            }
        });

        STUDENT_P_FILTER_GROUPTYPE.valueProperty().addListener(new ChangeListener<GrouptypeEntity>() {
            @Override
            public void changed(ObservableValue<? extends GrouptypeEntity> observable, GrouptypeEntity oldValue, GrouptypeEntity newValue) {
                updateStudentTableByParameter();
            }
        });

        STUDENT_P_FILTER_QUALIFICATION.valueProperty().addListener(new ChangeListener<QualificationlevelEntity>() {
            @Override
            public void changed(ObservableValue<? extends QualificationlevelEntity> observable, QualificationlevelEntity oldValue, QualificationlevelEntity newValue) {
                updateStudentTableByParameter();
            }
        });

        new FillComboBox(STUDENT_P_FILTER_GROUP).fillGroup();
        new FillComboBox(STUDENT_P_FILTER_CHAIRS).fillChair();
        new FillComboBox(STUDENT_P_FILTER_GROUPTYPE).fillGroupForm();
        new FillComboBox(STUDENT_P_FILTER_QUALIFICATION).fillQualificationLevel();

        tableStudents.setRowFactory(new Callback<TableView<StudentsEntity>, TableRow<StudentsEntity>>() {
            @Override
            public TableRow<StudentsEntity> call(TableView<StudentsEntity> param) {
                final TableRow<StudentsEntity> row = new TableRow<>();
                final ContextMenu rowMenu = new ContextMenu();
                final ContextMenu tableMenu = tableStudents.getContextMenu();
                if (tableMenu != null) {
                    rowMenu.getItems().addAll(tableMenu.getItems());
                    rowMenu.getItems().add(new SeparatorMenuItem());
                }

                final MenuItem editItem = new MenuItem("Редагувати");
                editItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        openStudent(tableStudents.getSelectionModel().getSelectedItem().getIdstudents());
                    }
                });

                final MenuItem createItem = new MenuItem("Створити");
                createItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        createStudent();
                    }
                });

                rowMenu.getItems().addAll(editItem);
                row.contextMenuProperty().bind(
                        Bindings.when(Bindings.isNotNull(row.itemProperty()))
                                .then(rowMenu)
                                .otherwise(new ContextMenu(createItem)));
                return row;
            }
        });

        STUDENT_P_CREATE.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                createStudent();
            }
        });

        STUDENT_P_LOAD.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                loadStudent();
            }
        });

        STUDENT_P_FILTER_CHAIRS_CLEAR.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                STUDENT_P_FILTER_CHAIRS.getSelectionModel().clearSelection();
                isClearAllFilterStudent();
            }
        });

        STUDENT_P_FILTER_QUALIFICATION_CLEAR.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                STUDENT_P_FILTER_QUALIFICATION.getSelectionModel().clearSelection();
                isClearAllFilterStudent();
            }
        });

        STUDENT_P_FILTER_GROUPTYPE_CLEAR.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                STUDENT_P_FILTER_GROUPTYPE.getSelectionModel().clearSelection();
                isClearAllFilterStudent();
            }
        });

        STUDENT_P_FILTER_GROUP_CLEAR.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                STUDENT_P_FILTER_GROUP.getSelectionModel().clearSelection();
                isClearAllFilterStudent();
            }
        });

        updateStudentTable();
        stage.setUserData(new StudentsEntity());
    }

    private void selectStudent(StudentsEntity student) {
        stage.setUserData(student);
        SEARCH.getScene().getWindow().hide();
    }

    private void isClearAllFilterStudent() {
        if (STUDENT_P_FILTER_GROUP.getValue() == null &&
                STUDENT_P_FILTER_GROUPTYPE.getValue() == null &&
                STUDENT_P_FILTER_QUALIFICATION.getValue() == null &&
                STUDENT_P_FILTER_CHAIRS.getValue() == null &&
                STUDENT_P_FILTER_FACULTY.getValue() == null &&
                SEARCH.getText().isEmpty()) {
            STUDENT_P_IS_FILTER = false;
            updateStudentTable();
        } else {
            STUDENT_P_IS_FILTER = true;
            updateStudentTableByParameter();
        }
    }

    private void clearStudentFilter() {
        STUDENT_P_FILTER_FACULTY.getSelectionModel().clearSelection();
        STUDENT_P_FILTER_QUALIFICATION.getSelectionModel().clearSelection();
        STUDENT_P_FILTER_GROUP.getSelectionModel().clearSelection();
        STUDENT_P_FILTER_GROUPTYPE.getSelectionModel().clearSelection();
        STUDENT_P_FILTER_CHAIRS.getSelectionModel().clearSelection();
        SEARCH.clear();

        updateStudentTable();
        STUDENT_P_IS_FILTER = false;
    }

    private void updateStudentTableByParameter() {
        tableStudents.getItems().clear();
        ObservableList<StudentsEntity> students = FXCollections.observableArrayList(
                new StudentController().getStudentByParameter(SEARCH.getText(), STUDENT_P_FILTER_GROUP));

        for (Iterator<StudentsEntity> iterator = students.iterator(); iterator.hasNext(); ) {
            StudentsEntity px = iterator.next();

            if (STUDENT_P_FILTER_GROUPTYPE.getValue() != null
                    && !px.getIdgroups().getIdgroupType().equals(STUDENT_P_FILTER_GROUPTYPE.getValue())
                    || STUDENT_P_FILTER_CHAIRS.getValue() != null
                    && !px.getIdgroups().getIdchairs().equals(STUDENT_P_FILTER_CHAIRS.getValue())
                    || STUDENT_P_FILTER_QUALIFICATION.getValue() != null
                    && !px.getIdgroups().getIdqualificationLevel().equals(STUDENT_P_FILTER_QUALIFICATION.getValue()))
                iterator.remove();
        }


        tableStudents.getItems().addAll(students);
        STUDENT_P_IS_FILTER = true;
    }

    private void updateStudentTable() {
        tableStudents.getItems().clear();
        tableStudents.getItems().addAll(FXCollections.observableArrayList(new StudentController().getAllStudent()));
    }

    private void initStudentTable() {
        tableStudentsLName.setCellValueFactory(new PropertyValueFactory("lastName"));
        tableStudentsFName.setCellValueFactory(new PropertyValueFactory("firstName"));
        tableStudentsMName.setCellValueFactory(new PropertyValueFactory("middleName"));
        tableStudentsGroup.setCellValueFactory(new PropertyValueFactory("idgroups"));
        tableStudentsIdCard.setCellValueFactory(new PropertyValueFactory("studentidcard"));
    }

    private void openStudent(int id) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/view/studentActivity.fxml"));
            stage.setScene(new Scene((Pane) loader.load()));
            StudentActivityController controller = loader.<StudentActivityController>getController();
            controller.fillForm(id);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(SEARCH.getScene().getWindow());
            stage.centerOnScreen();
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (STUDENT_P_IS_FILTER)
            updateStudentTableByParameter();
        else
            updateStudentTable();
    }

    private void createStudent() {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/view/studentActivity.fxml"));
            stage.setScene(new Scene((Pane) loader.load()));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(SEARCH.getScene().getWindow());
            stage.centerOnScreen();
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (STUDENT_P_IS_FILTER)
            updateStudentTableByParameter();
        else
            updateStudentTable();
    }

    private void loadStudent() {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/view/loadStudent.fxml"));
            stage.setScene(new Scene((Pane) loader.load()));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(SEARCH.getScene().getWindow());
            stage.centerOnScreen();
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (STUDENT_P_IS_FILTER)
            updateStudentTableByParameter();
        else
            updateStudentTable();
    }

    private void professor() {
        PROFESSOR.setVisible(true);

        initProfessorTable();

        tableProfessor.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                    selectProfessor(tableProfessor.getSelectionModel().getSelectedItem());
                } else if (event.isPrimaryButtonDown() && event.getClickCount() == 1) {
                    stage.setUserData(tableProfessor.getSelectionModel().getSelectedItem());
                }
            }
        });

        PROFESSOR_P_FILTER_CLEAR.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clearProfessorFilter();
            }
        });

        SEARCH.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                updateProfessorTableByParameter();
            }
        });

        PROFESSOR_P_FILTER_CHAIRS.valueProperty().addListener(new ChangeListener<ChairsEntity>() {
            @Override
            public void changed(ObservableValue<? extends ChairsEntity> observable, ChairsEntity oldValue, ChairsEntity newValue) {
                updateProfessorTableByParameter();
            }
        });

        new FillComboBox(PROFESSOR_P_FILTER_CHAIRS).fillChair();

        tableProfessor.setRowFactory(new Callback<TableView<TeachersEntity>, TableRow<TeachersEntity>>() {
            @Override
            public TableRow<TeachersEntity> call(TableView<TeachersEntity> param) {
                final TableRow<TeachersEntity> row = new TableRow<>();
                final ContextMenu rowMenu = new ContextMenu();
                final ContextMenu tableMenu = tableProfessor.getContextMenu();
                if (tableMenu != null) {
                    rowMenu.getItems().addAll(tableMenu.getItems());
                    rowMenu.getItems().add(new SeparatorMenuItem());
                }

                final MenuItem editItem = new MenuItem("Редагувати");
                editItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        openProfessor(tableProfessor.getSelectionModel().getSelectedItem().getIdteachers());
                    }
                });

                final MenuItem createItem = new MenuItem("Створити");
                createItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        createProfessor();
                    }
                });

                rowMenu.getItems().addAll(editItem);
                row.contextMenuProperty().bind(
                        Bindings.when(Bindings.isNotNull(row.itemProperty()))
                                .then(rowMenu)
                                .otherwise(new ContextMenu(createItem)));
                return row;
            }
        });

        PROFESSOR_P_CREATE.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                createProfessor();
            }
        });

        PROFESSOR_P_FILTER_CHAIRS_CLEAR.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                PROFESSOR_P_FILTER_CHAIRS.getSelectionModel().clearSelection();
                isClearAllFilterProfessor();
            }
        });

        updateProfessorTable();

        stage.setUserData(new TeachersEntity());
    }

    private void selectProfessor(TeachersEntity teacher) {
        stage.setUserData(teacher);
        SEARCH.getScene().getWindow().hide();
    }

    private void createProfessor() {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/view/professorActivity.fxml"));
            stage.setScene(new Scene((Pane) loader.load()));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(SEARCH.getScene().getWindow());
            stage.centerOnScreen();
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (PROFESSOR_P_IS_FILTER)
            updateProfessorTableByParameter();
        else
            updateProfessorTable();

    }

    private void updateProfessorTableByParameter() {
        tableProfessor.getItems().clear();
        ObservableList<TeachersEntity> teachers = FXCollections.observableArrayList(
                new TeacherController().getTeacherByParameter(SEARCH.getText(), PROFESSOR_P_FILTER_CHAIRS));

        tableProfessor.getItems().addAll(teachers);
        PROFESSOR_P_IS_FILTER = true;
    }

    private void clearProfessorFilter() {
        PROFESSOR_P_FILTER_FACULTY.getSelectionModel().clearSelection();
        PROFESSOR_P_FILTER_CHAIRS.getSelectionModel().clearSelection();
        SEARCH.clear();

        updateStudentTable();
        PROFESSOR_P_IS_FILTER = false;
    }

    private void openProfessor(int id) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/view/professorActivity.fxml"));
            stage.setScene(new Scene((Pane) loader.load()));
            ProfessorActivityController controller = loader.<ProfessorActivityController>getController();
            controller.fillForm(id);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(SEARCH.getScene().getWindow());
            stage.centerOnScreen();
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (PROFESSOR_P_IS_FILTER)
            updateProfessorTableByParameter();
        else
            updateProfessorTable();

    }

    private void updateProfessorTable() {
        tableProfessor.getItems().clear();
        tableProfessor.getItems().addAll(FXCollections.observableArrayList(new TeacherController().getAllTeacher()));
    }

    private void initProfessorTable() {
        tableProfessorLName.setCellValueFactory(new PropertyValueFactory("lastName"));
        tableProfessorFName.setCellValueFactory(new PropertyValueFactory("firstName"));
        tableProfessorMName.setCellValueFactory(new PropertyValueFactory("middleName"));
        tableProfessorChair.setCellValueFactory(new PropertyValueFactory("idchairs"));
    }

    private void isClearAllFilterProfessor() {
        if (PROFESSOR_P_FILTER_FACULTY.getValue() == null &&
                PROFESSOR_P_FILTER_CHAIRS.getValue() == null &&
                SEARCH.getText().isEmpty()) {
            PROFESSOR_P_IS_FILTER = false;
            updateProfessorTable();
        } else {
            PROFESSOR_P_IS_FILTER = true;
            updateProfessorTableByParameter();
        }
    }

    public void setVisible(int i) {
        switch (i) {
            case 0:
                student();
                I = 0;
                break;
            case 1:
                professor();
                I = 1;
                break;
        }
    }
}
