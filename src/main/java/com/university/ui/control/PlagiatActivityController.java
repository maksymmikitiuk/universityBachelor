package com.university.ui.control;

import com.university.db.entity.DiplomasubjectsEntity;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by maksymmikitiuk on 6/10/17.
 */
public class PlagiatActivityController implements Initializable {
    public ListView plagiatlist;
    public Button okplagiat;
    private Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        okplagiat.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                okplagiat.getScene().getWindow().hide();
            }
        });
    }

    public void setStage(Stage stage) {
        this.stage = stage;
        List<DiplomasubjectsEntity> list = (List<DiplomasubjectsEntity>) stage.getUserData();
        for (DiplomasubjectsEntity d : list){
            Text text = new Text(d.getSubject());
            text.setTextAlignment(TextAlignment.JUSTIFY);
            text.setWrappingWidth(330);
            plagiatlist.getItems().add(text);
            Text text1 = new Text(d.getStudent().getLastName()
            + " " + d.getStudent().getFirstName().charAt(0) + ". "
            + d.getStudent().getMiddleName().charAt(0) + "."
            + "\n" + d.getStudent().getIdgroups().toString());
            text1.setTextAlignment(TextAlignment.RIGHT);
            text1.setWrappingWidth(330);
            plagiatlist.getItems().add(text1);
            plagiatlist.getItems().add("-------------------------------------------");
        }

    }
}
