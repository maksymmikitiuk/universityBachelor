package com.university.ui.control;

import com.university.db.control.DBController;
import com.university.db.control.UserController;
import com.university.db.entity.UsersEntity;
import com.university.security.GeneratePassword;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by maksymmikitiuk on 4/16/17.
 */
public class UserSettingsController implements Initializable {
    public TextField lastName, firstName, middleName, oldUsername, newUsername, reNewUsername;
    public PasswordField oldPassword, newPassword, reNewPassword;
    public Button OK, changePassword, changeUsername;
    private UsersEntity user;
    private boolean vPassword, vUsername, vOldPassword, vOldUsername;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        oldPassword.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (user.getPassword().equals(new GeneratePassword().generatedSecuredPasswordHash(newValue, user.getUsername()))) {
                    oldPassword.getStyleClass().removeAll("false_field");
                    oldPassword.getStyleClass().add("true_field");
                    vOldPassword = true;
                } else {
                    oldPassword.getStyleClass().removeAll("true_field");
                    oldPassword.getStyleClass().add("false_field");
                    vOldPassword = false;
                }
            }
        });

        oldUsername.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (oldUsername.getText().equals(user.getUsername())) {
                    oldUsername.getStyleClass().removeAll("false_field");
                    oldUsername.getStyleClass().add("true_field");
                    vOldUsername = true;
                } else {
                    oldUsername.getStyleClass().removeAll("true_field");
                    oldUsername.getStyleClass().add("false_field");
                    vOldUsername = false;
                }
            }
        });

        reNewPassword.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                validationPassword(newValue);
            }
        });

        reNewUsername.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                validationUsername(newValue);
            }
        });

        OK.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                user.setfName(firstName.getText());
                user.setmName(middleName.getText());
                user.setlName(lastName.getText());

                new DBController().update(user);

                OK.getScene().getWindow().hide();
            }
        });

        changePassword.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (vPassword && vOldPassword) {
                    user.setPassword(new GeneratePassword().generatedSecuredPasswordHash(newPassword.getText().trim(), user.getUsername()));
                    new DBController().update(user);
                    clearFields();
                    reNewPassword.getStyleClass().remove("false_field");
                    reNewPassword.getStyleClass().add("true_field");
                    oldPassword.getStyleClass().remove("false_field");
                    oldPassword.getStyleClass().add("true_field");
                }
            }
        });

        changeUsername.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (vUsername&&vOldUsername) {
                    user.setUsername(newUsername.getText());
                    new DBController().update(user);
                    clearFields();
                    reNewUsername.getStyleClass().remove("false_field");
                    reNewUsername.getStyleClass().add("true_field");
                    oldUsername.getStyleClass().remove("false_field");
                    oldUsername.getStyleClass().add("true_field");
                }
            }
        });
    }

    public void fillForm(int id) {
        user = new UserController().getUserById(id);

        firstName.setText(user.getfName());
        lastName.setText(user.getlName());
        middleName.setText(user.getmName());
    }

    private void clearFields() {
        newPassword.clear();
        reNewPassword.clear();
        oldPassword.clear();
        oldUsername.clear();
        reNewUsername.clear();
        newUsername.clear();
    }

    private void validationPassword(String newValue) {
        if (!reNewPassword.getText().isEmpty() && !newValue.isEmpty()
                && newPassword.getText().equals(newValue)) {
            reNewPassword.getStyleClass().remove("false_field");
            reNewPassword.getStyleClass().add("true_field");
            vPassword = true;
        } else {
            reNewPassword.getStyleClass().remove("true_field");
            reNewPassword.getStyleClass().add("false_field");
            vPassword = false;
        }
    }

    private void validationUsername(String newValue) {
        if (!reNewUsername.getText().isEmpty() && !newValue.isEmpty()
                && newUsername.getText().equals(newValue)) {
            reNewUsername.getStyleClass().remove("false_field");
            reNewUsername.getStyleClass().add("true_field");
            vUsername = true;
        } else {
            reNewUsername.getStyleClass().remove("true_field");
            reNewUsername.getStyleClass().add("false_field");
            vUsername = false;
        }
    }
}
