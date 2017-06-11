package com.university.ui.control;

import com.university.Antiplagiarism.Antiplagiarism;
import com.university.Antiplagiarism.CheckAntiplagiarism;
import com.university.comboBox.FillComboBox;
import com.university.db.control.*;
import com.university.db.entity.*;
import com.university.externalFile.WorkWithFile;
import javafx.beans.property.SimpleBooleanProperty;
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
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Timestamp;
import java.util.List;
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
    private ObservableList<DiplomamarksEntity> diplomamarks = FXCollections.observableArrayList();
    private ObservableList<DocumentregistrationEntity> document;

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

    @FXML
    TableView<DocumentregistrationEntity> fileTable;
    @FXML
    TableColumn<DocumentregistrationEntity, String> fileTableType;
    @FXML
    TableColumn<DocumentregistrationEntity, String> fileTablePath;
    @FXML
    TableColumn<DocumentregistrationEntity, String> fileTableDate;
    @FXML
    TableColumn<DocumentregistrationEntity, String> fileTableUser;
    @FXML
    TableColumn<DocumentregistrationEntity, Boolean> fileTableAD;

    public DiplomasubjectsEntity getDiplomasubjects() {
        return diplomasubjects;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initMarksTable();
        initFileTable();
        updateFileTable();

        diplomasubjects.setSubject("");

        diplomaType.valueProperty().addListener(new ChangeListener<DiplomatypeEntity>() {
            @Override
            public void changed(ObservableValue<? extends DiplomatypeEntity> observable, DiplomatypeEntity oldValue, DiplomatypeEntity newValue) {
                updateFileTable();
            }
        });

        create.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                createSubject();
            }
        });

        cancel.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                cancel.getScene().getWindow().hide();
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
                }
                if (stage.getUserData() != null) {
                    TeachersEntity t = (TeachersEntity) stage.getUserData();
                    if (t.getIdteachers() != 0) {
                        diplomasubjects.setCurator(t);
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
                }
                if (stage.getUserData() != null) {
                    TeachersEntity t = (TeachersEntity) stage.getUserData();
                    if (t.getIdteachers() != 0) {
                        diplomasubjects.setReviewer(t);
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
                }
                if (stage.getUserData() != null) {
                    StudentsEntity s = (StudentsEntity) stage.getUserData();
                    if (s.getIdstudents() != 0) {
                        diplomasubjects.setStudent(s);
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
                }
                if (stage.getUserData() != null) {
                    marksTable.getItems().add((DiplomamarksEntity) stage.getUserData());
                    marksTable.refresh();
                }

            }
        });
    }


    private boolean checkSubject() {

        String tagSubject = new Antiplagiarism().getTagSubject(subject.getText().trim());

        CheckAntiplagiarism checkAntiplagiarism = new CheckAntiplagiarism(tagSubject);

        List<DiplomasubjectsEntity> l = checkAntiplagiarism.getSubjects();
        if (l.contains(diplomasubjects))
            l.remove(diplomasubjects);

        if (!l.isEmpty()) {
            Stage stage = new Stage();
            try {
                stage.setUserData(l);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/view/plagiatList.fxml"));
                stage.setScene(new Scene((Pane) loader.load()));
                PlagiatActivityController controller = loader.<PlagiatActivityController>getController();
                controller.setStage(stage);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(selectCurator.getScene().getWindow());
                stage.centerOnScreen();
                stage.setTitle("Схожі теми");
                stage.setResizable(false);
                stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        } else {
            diplomasubjects.setTag(tagSubject);
            diplomasubjects.setSubject(subject.getText());
            return true;
        }
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

        updateMarksTable();
        updateFileTable();
    }

    private void fillFinish() {
        DiplomamarksEntity diplomamarks = new DiplomaMarksController().getFinish(diplomasubjects);
        if (diplomamarks.getPoint() != null) {
            points.setText(diplomamarks.getPoint().toString());
            MarksEntity m = new MarksController().getMarksByPoints(Integer.valueOf(points.getText()));
            ects.setText(m.getEcts());
            nationalScale.setText(m.getNationalscale() + " (" + m.getNationalscalenumber() + ")");
            totalMarks = diplomamarks;
        }
    }

    private void createSubject() {
        if (checkSubject()) {
            //add subject
            diplomasubjects.setPlag((diplomasubjects.getPlag() == null) ? BigDecimal.valueOf(0.00) : diplomasubjects.getPlag());

            diplomasubjects.setType((DiplomatypeEntity) diplomaType.getSelectionModel().getSelectedItem());
            if (create.getText().equals("Зберегти"))
                new DBController().update(diplomasubjects);
            else
                new DBController().create(diplomasubjects);

            //add mark
            for (DiplomamarksEntity mark : marksTable.getItems()) {
                mark.setId_diploma(diplomasubjects);
                if (new DiplomaMarksController().isFind(mark.getIddiplomaMarks()))
                    new DBController().update(mark);
                else
                    new DBController().create(mark);
            }

            //add total mark
            if (totalMarks.getPoint() != null) {
                totalMarks.setId_diploma(diplomasubjects);
                if (new DiplomaMarksController().isFind(totalMarks.getIddiplomaMarks()))
                    new DBController().update(totalMarks);
                else
                    new DBController().create(totalMarks);
            }

            //add file
            List<DocumentregistrationEntity> list = fileTable.getItems();
            for (DocumentregistrationEntity d : list) {
                if (d.getPath() != null) {
                    d.setIddiplomaSubjects(diplomasubjects);
                    if (d.getIddocumentRegistration() == 0)
                        new DBController().create(d);
                    else
                        new DBController().update(d);
                }
            }

            create.getScene().getWindow().hide();
        }
    }

    private void updateFileTable() {
        fileTable.getItems().clear();
        document = FXCollections.observableArrayList(new DocumentRegistrationController().getFileByDiploma(diplomasubjects));
        List<DocumenttypeEntity> structure = new StructureOfTheDiplomaController().getStructure((DiplomatypeEntity) diplomaType.getSelectionModel().getSelectedItem());

        if (document.size() != 0) {
            for (DocumentregistrationEntity d : document) {
                if (structure.contains(d.getId_type()))
                    structure.remove(d.getId_type());
            }

            for (DocumenttypeEntity s : structure) {
                DocumentregistrationEntity d = new DocumentregistrationEntity();
                d.setId_type(s);
                document.add(d);
            }
        } else
            for (DocumenttypeEntity s : structure) {
                DocumentregistrationEntity d = new DocumentregistrationEntity();
                d.setId_type(s);
                document.add(d);
            }

        fileTable.getItems().addAll(document);
    }


    private void initFileTable() {
        fileTable.setEditable(true);

        fileTableType.setCellValueFactory(new PropertyValueFactory("id_type"));
        fileTableAD.setSortable(false);
        fileTableAD.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DocumentregistrationEntity, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<DocumentregistrationEntity, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });
        fileTableAD.setCellFactory(
                new Callback<TableColumn<DocumentregistrationEntity, Boolean>, TableCell<DocumentregistrationEntity, Boolean>>() {
                    @Override
                    public TableCell<DocumentregistrationEntity, Boolean> call(TableColumn<DocumentregistrationEntity, Boolean> param) {
                        return new ButtonCell(fileTable, diplomasubjects.getStudent());
                    }
                });
        fileTableDate.setCellValueFactory(new PropertyValueFactory("documentregistration"));
        fileTablePath.setCellValueFactory(new PropertyValueFactory("path"));
        fileTableUser.setCellValueFactory(new PropertyValueFactory("idusers"));
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

class ButtonCell extends TableCell<DocumentregistrationEntity, Boolean> {
    final Button cellButton = new Button();

    ButtonCell(final TableView tblView, StudentsEntity student) {
        cellButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                int selectdIndex = getTableRow().getIndex();

                DocumentregistrationEntity selectedRecord = (DocumentregistrationEntity) tblView.getItems().get(selectdIndex);

                if (selectedRecord.getPath() != null) {
                    if (selectedRecord.getIddocumentRegistration() == 0) {
                        selectedRecord.setDocumentregistration(null);
                        selectedRecord.setPath(null);
                        selectedRecord.setIdusers(null);
                    } else {
                        new DBController().delete(selectedRecord);
                        selectedRecord.setDocumentregistration(null);
                        selectedRecord.setPath(null);
                        selectedRecord.setIdusers(null);
                    }

                    tblView.refresh();
                } else {
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.getExtensionFilters().addAll(
                            new FileChooser.ExtensionFilter("All Files", "*.*"),
                            new FileChooser.ExtensionFilter("DOC", "*.doc"),
                            new FileChooser.ExtensionFilter("ODT", "*.odt"),
                            new FileChooser.ExtensionFilter("DOCX", "*.docx")
                    );
                    File file = fileChooser.showOpenDialog(tblView.getScene().getWindow());

                    String path = new WorkWithFile().copyFile(file, student);

                    ((DocumentregistrationEntity) tblView.getItems().get(selectdIndex)).setPath(path);
                    ((DocumentregistrationEntity) tblView.getItems().get(selectdIndex)).setIdusers(DBController.currentUser);
                    ((DocumentregistrationEntity) tblView.getItems().get(selectdIndex)).setDocumentregistration(new Timestamp(System.currentTimeMillis()));
                    tblView.refresh();
                }
            }
        });
    }

    //Display button if the row is not empty
    @Override
    protected void updateItem(Boolean t, boolean empty) {
        super.updateItem(t, empty);
        if (!empty) {
            cellButton.setPrefWidth(200);
            DocumentregistrationEntity d = (DocumentregistrationEntity) getTableRow().getItem();
            if (d != null)
                if (d.getPath() == null)
                    cellButton.setText("Додати");
                else
                    cellButton.setText("Видалити");

            setGraphic(cellButton);
        }
    }
}