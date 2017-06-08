package com.university.ui.control;

import com.university.comboBox.FillComboBox;
import com.university.db.control.DBController;
import com.university.db.control.UserController;
import com.university.db.entity.UserroleEntity;
import com.university.db.entity.UsersEntity;
import com.university.security.GeneratePassword;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by maksymmikitiuk on 5/31/17.
 */
public class AdminUserSettingsController implements Initializable {
    public CheckBox active;
    public TextField lastName, firstName, middleName, username;
    public PasswordField password;
    public ComboBox role;
    public Button save, cancel;
    private Stage stage;
    private UsersEntity user;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new FillComboBox(role).fillRole();

        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (check()) {
                    save();
                    save.getScene().getWindow().hide();
                }
            }
        });

        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cancel.getScene().getWindow().hide();
            }
        });

        username.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                username.getStyleClass().clear();
                username.getStyleClass().addAll("text-input", "text-field", "field");
                if (UserController.validationUser(newValue)) {
                    username.getStyleClass().add("true_field");
                } else {
                    username.getStyleClass().add("false_field");
                }
            }
        });

        firstName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                firstName.getStyleClass().add("true_field");
            }
        });

        middleName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                middleName.getStyleClass().add("true_field");
            }
        });

        lastName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                lastName.getStyleClass().add("true_field");
            }
        });

        password.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                password.getStyleClass().add("true_field");
            }
        });
    }

    private void save() {
        user.setActive((active.isSelected()) ? 1 : 0);
        user.setIdUserrole((UserroleEntity) role.getValue());
        user.setLastName(lastName.getText());
        user.setMiddleName(middleName.getText());
        user.setFirstName(firstName.getText());
        if (!password.getText().isEmpty())
            user.setPassword(new GeneratePassword().generatedSecuredPasswordHash(password.getText().trim(), username.getText()));

        new DBController().update(user);
    }

    private boolean check() {
        boolean check = true;

        if (firstName.getText().isEmpty()) {
            firstName.getStyleClass().add("false_field");
            check = false;
        }
        if (middleName.getText().isEmpty()) {
            middleName.getStyleClass().add("false_field");
            check = false;
        }
        if (lastName.getText().isEmpty()) {
            lastName.getStyleClass().add("false_field");
            check = false;
        }
        if (username.getText().isEmpty()) {
            username.getStyleClass().add("false_field");
            check = false;
        }

        return check;
    }

    public void fillForm(UsersEntity user) {
        this.user = user;
        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        middleName.setText(user.getMiddleName());
        password.setText("");
        role.getSelectionModel().select(user.getIdUserrole());
        username.setText(user.getUsername());
        active.setSelected((user.getActive() == 1) ? true : false);

    }

    public void setUser(UsersEntity selectedItem) {
        fillForm(selectedItem);
    }
}
