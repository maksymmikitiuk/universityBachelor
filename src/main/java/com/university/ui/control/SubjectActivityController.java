package com.university.ui.control;

import com.university.Antiplagiarism.Antiplagiarism;
import com.university.Antiplagiarism.CheckAntiplagiarism;
import com.university.comboBox.FillComboBox;
import com.university.db.control.DiplomaMarksController;
import com.university.db.control.MarksController;
import com.university.db.entity.DiplomamarksEntity;
import com.university.db.entity.DiplomasubjectsEntity;
import com.university.db.entity.MarksEntity;
import com.university.db.entity.StudentsEntity;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

/**
 * Created by maksymmikitiuk on 5/4/17.
 */
public class SubjectActivityController implements Initializable {
    public Button create, cancel, selectStudent, selectCurator, selectReviewer;
    public TextArea subject;
    public TextField plagiat, student, curator, reviewer, ects, points, nationalScale;
    public ComboBox diplomaType, diplomaForm;
    private DiplomasubjectsEntity diplomasubjects = new DiplomasubjectsEntity();
    private StudentsEntity newStudent;
    private MarksEntity totalMarks;

    @FXML
    TableView<DiplomamarksEntity> marksTable;
    @FXML
    TableColumn<DiplomamarksEntity, String> marksTableOwner;
    @FXML
    TableColumn<DiplomamarksEntity, String> marksTableTypeOwner;
    @FXML
    TableColumn<DiplomamarksEntity, String> marksTablePoint;
    @FXML
    TableColumn<DiplomamarksEntity, String> marksTableScale;
    @FXML
    TableColumn<DiplomamarksEntity, String> marksTableEcts;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initMarksTable();

        diplomasubjects.setSubject("");

        create.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                createSubject();
            }
        });

        selectCurator.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getData();
            }
        });

        selectReviewer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

        selectStudent.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

        subject.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!diplomasubjects.getSubject().equals(subject.getText().trim())) {
                    String tagSubject = new Antiplagiarism().getTagSubject(subject.getText().trim());

                    CheckAntiplagiarism checkAntiplagiarism = new CheckAntiplagiarism(tagSubject);

                    if (!checkAntiplagiarism.getSubjects().isEmpty()) {
                        System.out.println("Plagiat epta!");
                        create.setDisable(true);
                    }

                    diplomasubjects.setTag(tagSubject);
                    diplomasubjects.setSubject(subject.getText());
                }
            }
        });

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
    }

    private void updateMarksTable() {
        marksTable.getItems().clear();
        marksTable.getItems().addAll(FXCollections.<DiplomamarksEntity>observableArrayList(new DiplomaMarksController()
                .getDiplomaMarksById(diplomasubjects)));
    }

    private void initMarksTable() {
        marksTableOwner.setCellValueFactory(new PropertyValueFactory("owner"));
        marksTableTypeOwner.setCellValueFactory(new PropertyValueFactory("typeOwner"));
        marksTablePoint.setCellValueFactory(new PropertyValueFactory("point"));
        marksTableEcts.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DiplomamarksEntity, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DiplomamarksEntity, String> p) {
                return new SimpleStringProperty(p.getValue().getMark().getEcts());
            }
        });

        marksTableScale.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DiplomamarksEntity, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<DiplomamarksEntity, String> p) {
                return new SimpleStringProperty(p.getValue().getMark().getNationalscale()
                        + " (" + p.getValue().getMark().getNationalscalenumber() + ")");
            }
        });
    }

    private void fillMarks() {
        if (Pattern.matches("\\d+", points.getText())) {
            totalMarks = new MarksController().getMarksByPoints(Integer.valueOf(points.getText()));
            ects.setText(totalMarks.getEcts());
            nationalScale.setText(totalMarks.getNationalscale() + " (" + totalMarks.getNationalscalenumber() + ")");
        }
    }

    public void fillForm(DiplomasubjectsEntity diploma) {
        diplomasubjects = diploma;

        updateMarksTable();

        fillFinish();

        newStudent = diploma.getStudent();

        subject.setText(diploma.getSubject());
        plagiat.setText(diploma.getPlag().toString());
        student.setText(diploma.getStudent().getLfmiddleName());
        curator.setText(diploma.getCurator().toString());
        reviewer.setText(diploma.getReviewer().toString());
        new FillComboBox(diplomaType).fillDiplomaType(diploma.getType().getForm());
        new FillComboBox(diplomaForm).fillDiplomaForm(diploma.getStudent().getIdgroups().getIdqualificationLevel());
        diplomaType.getSelectionModel().select(diploma.getType());
        diplomaForm.getSelectionModel().select(diploma.getType().getForm());
    }

    private void fillFinish() {
        DiplomamarksEntity diplomamarks = new DiplomaMarksController().getFinish(diplomasubjects);
        points.setText(diplomamarks.getPoint().toString());
        totalMarks = new MarksController().getMarksByPoints(Integer.valueOf(points.getText()));
        ects.setText(totalMarks.getEcts());
        nationalScale.setText(totalMarks.getNationalscale() + " (" + totalMarks.getNationalscalenumber() + ")");
    }

    private void createSubject() {
        String tagSubject = new Antiplagiarism().getTagSubject(subject.getText());
        if (!new CheckAntiplagiarism(tagSubject).getSubjects().isEmpty())
            return;
//        diplomasubjects.setSubject(subject.getText());
//        diplomasubjects.setTag(tagSubject);
//        diplomasubjects.setDefencediploma((Timestamp) new Date());
//        diplomasubjects.setIdcurator();
//        diplomasubjects.setIddiplomaMarks();
//        diplomasubjects.setIddiplomaType();
//        diplomasubjects.setIdreviewer();
//        diplomasubjects.setIdstudent();
//        diplomasubjects.setPlag(Float.parseFloat(plagiat.getText()));
    }

    private Object getData() {
        Stage stage = new Stage();
        try {
            stage.initStyle(StageStyle.DECORATED);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/view/getData.fxml"));
            Scene scene = new Scene((Pane) loader.load());
            stage.setScene(scene);
            GetDataController controller = loader.<GetDataController>getController();
            controller.setStage(stage);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(create.getScene().getWindow());
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stage.getUserData();
    }
}
