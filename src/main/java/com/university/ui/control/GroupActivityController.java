package com.university.ui.control;

import com.university.comboBox.FillComboBox;
import com.university.db.control.DBController;
import com.university.db.entity.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by maksymmikitiuk on 6/10/17.
 */
public class GroupActivityController implements Initializable {
    public TextField abbreviature, name;
    public Button cancel, create;
    public ComboBox chair, ql, form, type;
    private GroupsEntity group;

    public void setGroup(GroupsEntity groups) {
        this.group = groups;
        chair.getSelectionModel().select(group.getIdchairs());
        ql.getSelectionModel().select(group.getIdqualificationLevel());
        form.getSelectionModel().select(group.getIdgroupForm());
        type.getSelectionModel().select(group.getIdgroupType());
        abbreviature.setText(groups.getAbbreviation());
        name.setText(groups.getName());

        if (abbreviature.getText() == null
                && name.getText() == null)
            create.setText("Створити");
        else
            create.setText("Зберегти");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new FillComboBox(chair).fillChair();
        new FillComboBox(ql).fillQualificationLevel();
        new FillComboBox(form).fillGroupForm();
        new FillComboBox(type).fillGroupType();

        create.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                group.setAbbreviation(abbreviature.getText());
                group.setName(name.getText());
                group.setIdchairs((ChairsEntity) chair.getSelectionModel().getSelectedItem());
                group.setIdgroupForm((GroupformEntity) form.getSelectionModel().getSelectedItem());
                group.setIdgroupType((GrouptypeEntity) type.getSelectionModel().getSelectedItem());
                group.setIdqualificationLevel((QualificationlevelEntity) ql.getSelectionModel().getSelectedItem());

                if (create.getText().equals("Зберегти"))
                    new DBController().update(group);
                else
                    new DBController().create(group);
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
}
