package com.university.ui.control;

import com.university.db.control.DBController;
import com.university.db.entity.ChairsEntity;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by maksymmikitiuk on 6/10/17.
 */
public class ChairsController implements Initializable {

    public TextField abbreviature, name;
    public Button cancel, create;
    private ChairsEntity chair;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        create.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                chair.setAbbreviation(abbreviature.getText());
                chair.setName(name.getText());
                if (create.getText().equals("Зберегти"))
                    new DBController().update(chair);
                else
                    new DBController().create(chair);
                create.getScene().getWindow().hide();
            }
        });

        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cancel.getScene().getWindow().hide();
            }
        });
    }

    public void setChairs(ChairsEntity chairs) {
        this.chair = chairs;
        abbreviature.setText(chair.getAbbreviation());
        name.setText(chair.getName());

        if (abbreviature.getText() == null
                && name.getText() == null)
            create.setText("Створити");
        else
            create.setText("Зберегти");
    }
}
