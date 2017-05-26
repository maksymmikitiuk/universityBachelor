package com.university.ui.control;

import com.university.Antiplagiarism.Antiplagiarism;
import com.university.Antiplagiarism.CheckAntiplagiarism;
import com.university.comboBox.FillComboBox;
import com.university.db.control.*;
import com.university.db.entity.*;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class SubjectActivityController implements Initializable {
    public Button create, cancel, selectStudent, selectCurator, selectReviewer, addMarks;
    public TextArea subject;
    public TextField plagiat, student, curator, reviewer, ects, points, nationalScale;
    public ComboBox diplomaType, diplomaForm;
    private DiplomasubjectsEntity diplomasubjects = new DiplomasubjectsEntity();
    private StudentsEntity newStudent;
    private DiplomamarksEntity totalMarks = new DiplomamarksEntity();
    private ObservableList<DiplomamarksEntity> diplomamarks;

    @FXML
    TableView<DiplomamarksEntity> marksTable;
    @FXML
    TableColumn<MarksEntity, TeachersEntity> marksTableOwner;
    @FXML
    TableColumn<MarksEntity, TypeOwnerMarkEntity> marksTableTypeOwner;
    @FXML
    TableColumn<MarksEntity, Integer> marksTablePoint;
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
                Stage stage = new Stage();
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/view/getData.fxml"));
                    stage.setScene(new Scene((Pane) loader.load()));
                    GetDataController controller = loader.<GetDataController>getController();
                    controller.setStage(stage);
                    controller.setVisible(1);
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(selectCurator.getScene().getWindow());
                    stage.centerOnScreen();
                    stage.showAndWait();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (stage.getUserData() != null) {
                        diplomasubjects.setCurator((TeachersEntity) stage.getUserData());
                        curator.setText(diplomasubjects.getCurator().getLfmName());
                    }
                }
            }
        });

        selectReviewer.setOnAction(new EventHandler<ActionEvent>() {
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
                    stage.initOwner(selectCurator.getScene().getWindow());
                    stage.centerOnScreen();
                    stage.showAndWait();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (stage.getUserData() != null) {
                        diplomasubjects.setReviewer((TeachersEntity) stage.getUserData());
                        reviewer.setText(diplomasubjects.getReviewer().getLfmName());
                    }
                }
            }
        });

        selectStudent.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = new Stage();
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/view/getData.fxml"));
                    stage.setScene(new Scene((Pane) loader.load()));
                    GetDataController controller = loader.<GetDataController>getController();
                    controller.setStage(stage);
                    controller.setVisible(0);
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(selectStudent.getScene().getWindow());
                    stage.centerOnScreen();
                    stage.showAndWait();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (stage.getUserData() != null) {
                        diplomasubjects.setStudent((StudentsEntity) stage.getUserData());
                        student.setText(diplomasubjects.getStudent().getLfmiddleName());
                        diplomaForm.setDisable(false);
                        new FillComboBox(diplomaForm).fillDiplomaForm(diplomasubjects.getStudent().getIdgroups().getIdqualificationLevel());
                    }
                }
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
                    } else {
                        create.setDisable(false);
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

        diplomaForm.valueProperty().addListener(new ChangeListener<DiplomaformEntity>() {
            @Override
            public void changed(ObservableValue ov, DiplomaformEntity t, DiplomaformEntity t1) {
                if (diplomaForm.getValue() != null) {
                    diplomaType.setDisable(false);
                    new FillComboBox(diplomaType).fillDiplomaType((DiplomaformEntity) diplomaForm.getValue());
                }
            }
        });

        addMarks.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = new Stage();
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/view/addMark.fxml"));
                    stage.setScene(new Scene((Pane) loader.load()));
                    AddMarkController controller = loader.<AddMarkController>getController();
                    controller.setStage(stage);
                    controller.setDiplomasubjects(diplomasubjects);
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(addMarks.getScene().getWindow());
                    stage.centerOnScreen();
                    stage.setResizable(false);
                    stage.showAndWait();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (stage.getUserData() != null) {
                        marksTable.getItems().add((DiplomamarksEntity) stage.getUserData());
                        marksTable.refresh();
                    }
                }
            }
        });
    }

    private void updateMarksTable() {
        marksTable.getItems().clear();
        diplomamarks = FXCollections.<DiplomamarksEntity>observableArrayList(new DiplomaMarksController().getDiplomaMarksById(diplomasubjects));
        marksTable.getItems().addAll(diplomamarks);
    }

    private void initMarksTable() {
        marksTable.setEditable(true);

        marksTableOwner.setCellValueFactory(new PropertyValueFactory("owner"));
        ObservableList<TeachersEntity> teachersList = FXCollections.observableList(new TeacherController().getAllTeacher());
        marksTableOwner.setCellFactory(ComboBoxTableCell.<MarksEntity, TeachersEntity>forTableColumn(teachersList));
        marksTableOwner.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<MarksEntity, TeachersEntity>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<MarksEntity, TeachersEntity> event) {
                        marksTable.getSelectionModel().getSelectedItem().setOwner(event.getNewValue());
                        diplomamarks.clear();
                        diplomamarks.addAll(marksTable.getItems());
                        marksTable.refresh();
                    }
                }
        );

        marksTableTypeOwner.setCellValueFactory(new PropertyValueFactory("typeOwner"));
        ObservableList<TypeOwnerMarkEntity> typeOwnerList = FXCollections.observableList(new TypeOwnerMarkController().getAllType());
        marksTableTypeOwner.setCellFactory(ComboBoxTableCell.<MarksEntity, TypeOwnerMarkEntity>forTableColumn(typeOwnerList));
        marksTableTypeOwner.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<MarksEntity, TypeOwnerMarkEntity>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<MarksEntity, TypeOwnerMarkEntity> event) {
                        marksTable.getSelectionModel().getSelectedItem().setTypeOwner(event.getNewValue());
                        diplomamarks.clear();
                        diplomamarks.addAll(marksTable.getItems());
                        marksTable.refresh();
                    }
                }
        );

        Callback<TableColumn<MarksEntity, Integer>, TableCell<MarksEntity, Integer>> cellFactory =
                new Callback<TableColumn<MarksEntity, Integer>, TableCell<MarksEntity, Integer>>() {
                    public EditingCell call(TableColumn p) {
                        return new EditingCell();
                    }
                };

        marksTablePoint.setCellValueFactory(new PropertyValueFactory<MarksEntity, Integer>("point"));
        marksTablePoint.setCellFactory(cellFactory);
        marksTablePoint.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<MarksEntity, Integer>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<MarksEntity, Integer> event) {
                        int value = (event.getNewValue() > 100) ? 100 : event.getNewValue();
                        marksTable.getSelectionModel().getSelectedItem().setPoint(value);
                        marksTable.getSelectionModel().getSelectedItem().setMark(new MarksController().getMarksByPoints(value));
                        diplomamarks.clear();
                        diplomamarks.addAll(marksTable.getItems());
                        marksTable.refresh();
                    }
                });


        marksTableEcts.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DiplomamarksEntity, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call
                    (TableColumn.CellDataFeatures<DiplomamarksEntity, String> p) {
                return new SimpleStringProperty(p.getValue().getMark().getEcts());
            }
        });

        marksTableScale.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DiplomamarksEntity, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call
                    (TableColumn.CellDataFeatures<DiplomamarksEntity, String> p) {
                return new SimpleStringProperty(p.getValue().getMark().getNationalscale()
                        + " (" + p.getValue().getMark().getNationalscalenumber() + ")");
            }
        });
    }

    private void fillMarks() {
        if (Pattern.matches("\\d+", points.getText())) {
            MarksEntity m = new MarksController().getMarksByPoints(Integer.valueOf(points.getText()));
            ects.setText(m.getEcts());
            nationalScale.setText(m.getNationalscale() + " (" + m.getNationalscalenumber() + ")");
            totalMarks.setTypeOwner(new TypeOwnerMarkController().getFinish());
            totalMarks.setPoint(Integer.valueOf(points.getText()));
            totalMarks.setMark(m);
            totalMarks.setId_diploma(diplomasubjects);
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
        diplomaForm.setDisable(false);
        diplomaType.setDisable(false);

        create.setText("Зберегти");
    }

    private void fillFinish() {
        DiplomamarksEntity diplomamarks = new DiplomaMarksController().getFinish(diplomasubjects);
        if(diplomamarks.getPoint() != null){
        points.setText(diplomamarks.getPoint().toString());
        MarksEntity m = new MarksController().getMarksByPoints(Integer.valueOf(points.getText()));
        ects.setText(m.getEcts());
        nationalScale.setText(m.getNationalscale() + " (" + m.getNationalscalenumber() + ")");
        totalMarks = diplomamarks;}
    }

    private void createSubject() {
        //add subject
        diplomasubjects.setPlag((diplomasubjects.getPlag() == null)? BigDecimal.valueOf(0.00) :diplomasubjects.getPlag());

        diplomasubjects.setType((DiplomatypeEntity) diplomaType.getSelectionModel().getSelectedItem());
        if (create.getText().equals("Зберегти"))
            new dbController().update(diplomasubjects);
        else
            new dbController().create(diplomasubjects);

        //add mark
        for (DiplomamarksEntity mark : marksTable.getItems()) {
            mark.setId_diploma(diplomasubjects);
            if(new DiplomaMarksController().isFind(mark.getIddiplomaMarks()))
                new dbController().update(mark);
            else
                new dbController().create(mark);
        }

        //add total mark
        if (totalMarks.getPoint() != null) {
            totalMarks.setId_diploma(diplomasubjects);
            if(new DiplomaMarksController().isFind(totalMarks.getIddiplomaMarks()))
                new dbController().update(totalMarks);
            else
                new dbController().create(totalMarks);
        }

        //add file


        create.getScene().getWindow().hide();
    }
}

class EditingCell extends TableCell<MarksEntity, Integer> {

    private TextField textField;

    public EditingCell() {
    }

    @Override
    public void startEdit() {
        super.startEdit();

        if (textField == null) {
            createTextField();
        }

        setGraphic(textField);
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        textField.selectAll();
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();

        setText(String.valueOf(getItem()));
        setContentDisplay(ContentDisplay.TEXT_ONLY);
    }

    @Override
    public void updateItem(Integer item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            if (isEditing()) {
                if (textField != null) {
                    textField.setText(getString());
                }
                setGraphic(textField);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            } else {
                setText(getString());
                setContentDisplay(ContentDisplay.TEXT_ONLY);
            }
        }
    }

    private void createTextField() {
        textField = new TextField(getString());
        textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
        textField.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent t) {
                if (t.getCode() == KeyCode.ENTER) {
                    commitEdit(Integer.valueOf(textField.getText()));
                } else if (t.getCode() == KeyCode.ESCAPE) {
                    cancelEdit();
                }
            }
        });
    }

    private String getString() {
        return getItem() == null ? "" : getItem().toString();
    }
}