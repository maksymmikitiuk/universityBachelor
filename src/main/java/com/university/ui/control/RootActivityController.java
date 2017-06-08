package com.university.ui.control;

import com.university.db.control.DBController;
import com.university.db.control.RoleController;
import com.university.db.control.UserController;
import com.university.db.entity.UsersEntity;
import com.university.security.GeneratePassword;
import com.university.ui.MainActivity;
import com.university.ui.animation.Animation;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RootActivityController implements Initializable {
    public AnchorPane loginPane;
    public Button login;
    public Label createUserLabel, backToLogin, createUser, closewindow, ok, ok1;
    public TextField username, newfname, newmname, newlname, newusername, passwordtext, newpasswordtext,
            newrepasswordtext;
    private Boolean newrepasswordvalid, newusernamevalid;
    public PasswordField password, newpassword, newrepassword;
    public Pane draggablepanel, setvisiblepassword, setvisiblenewpassword, setvisiblenewrepassword, error, notactive;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setvisiblepassword.setStyle("-fx-background-image: url(ui/img/visible.png)");
        passwordtext.setVisible(false);
        passwordtext.setDisable(true);
        setvisiblenewpassword.setStyle("-fx-background-image: url(ui/img/visible.png)");
        newpasswordtext.setVisible(false);
        newpasswordtext.setDisable(true);
        setvisiblenewrepassword.setStyle("-fx-background-image: url(ui/img/visible.png)");
        newrepasswordtext.setVisible(false);
        newrepasswordtext.setDisable(true);

        closewindow.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    System.exit(0);
                }
            }
        });

        createUserLabel.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    new Animation().showCreateUser(loginPane);
                    cleatFields();
                }
            }
        });

        backToLogin.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    new Animation().showCreateUser(loginPane);
                    cleatFields();
                }
            }
        });

        login.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY)
                    login();
            }
        });

        ok.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY)
                    error.setVisible(false);
            }
        });

        ok1.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY)
                    notactive.setVisible(false);
            }
        });

        createUser.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (checkFill()) {
                    if (event.getButton() == MouseButton.PRIMARY) {
                        if (newusernamevalid && newrepasswordvalid) {
                            UsersEntity user = new UsersEntity();
                            user.setUsername(newusername.getText().trim());
                            user.setPassword(new GeneratePassword().generatedSecuredPasswordHash(newpassword.getText().trim(), user.getUsername()));
                            user.setFirstName(newfname.getText().trim());
                            user.setMiddleName(newmname.getText().trim());
                            user.setLastName(newlname.getText().trim());
                            user.setIdUserrole(new RoleController().getUser());
                            user.setActive(0);
                            user.setAdmin((byte) 0);

                            if (new DBController().create(user)) {
                                cleatFields();
                                new Animation().showCreateUser(loginPane);
                            } else {
                                System.err.println("Пользователь не создан");
                            }
                        }
                    }
                }
            }
        });

        newrepassword.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                validationPassword(newValue);
            }
        });

        newrepasswordtext.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                validationPassword(newValue);
            }
        });

        newusername.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                newusername.getStyleClass().clear();
                newusername.getStyleClass().addAll("text-input", "text-field", "field");
                if (UserController.validationUser(newValue)) {
                    newusername.getStyleClass().add("true_field");
                    newusernamevalid = true;
                } else {
                    newusername.getStyleClass().add("false_field");
                    newusernamevalid = false;
                }
            }
        });

        password.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                passwordtext.setText(newValue);
            }
        });

        passwordtext.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                password.setText(newValue);
            }
        });

        newpassword.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                newpasswordtext.setText(newValue);
            }
        });

        newpasswordtext.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                newpassword.setText(newValue);
            }
        });

        newrepassword.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                newrepasswordtext.setText(newValue);
            }
        });

        newrepasswordtext.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                newrepassword.setText(newValue);
            }
        });

        setvisiblepassword.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    setVisiblePassword(setvisiblepassword, passwordtext, password);
                }
            }
        });

        setvisiblenewpassword.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    setVisiblePassword(setvisiblenewpassword, newpasswordtext, newpassword);
                }
            }
        });

        setvisiblenewrepassword.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    setVisiblePassword(setvisiblenewrepassword, newrepasswordtext, newrepassword);
                }
            }
        });

        username.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    login();
                }
            }
        });

        password.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    login();
                }
            }
        });

        passwordtext.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    login();
                }
            }
        });

        newpasswordtext.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                newpasswordtext.getStyleClass().add("true_field");
            }
        });

        newpassword.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                newpassword.getStyleClass().add("true_field");
            }
        });

        newfname.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                newfname.getStyleClass().add("true_field");
            }
        });

        newmname.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                newmname.getStyleClass().add("true_field");
            }
        });

        newlname.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                newlname.getStyleClass().add("true_field");
            }
        });
    }

    private void login() {
        if (new UserController().isActive(username.getText().trim())) {
            if (new UserController().passwordAuthentication(password.getText().trim(), username.getText().trim())) {
                DBController.currentUser = new UserController().getCurrentUserInformation(username.getText());
                cleatFields();
                Parent root = null;
                try {
                    root = (Parent) FXMLLoader.load(MainActivity.class.getResource("/ui/view/mainActivity.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Scene scene = new Scene(root);
                DBController.mainStage.setScene(scene);
                DBController.mainStage.centerOnScreen();
                DBController.mainStage.setResizable(true);
                DBController.mainStage.show();
            } else {
                error.setVisible(true);
            }
        } else {
            notactive.setVisible(true);
        }
    }

    private void setVisiblePassword(Pane isVisible, TextField passwordtext, PasswordField password) {
        isVisible.setStyle(passwordtext.isVisible() ? "-fx-background-image: url(ui/img/visible.png)" :
                "-fx-background-image: url(ui/img/invisible.png)");
        password.setVisible(passwordtext.isVisible());
        passwordtext.setVisible(!passwordtext.isVisible());
        passwordtext.setDisable(password.isVisible());
    }

    private void cleatFields() {
        username.clear();
        password.clear();
        newpassword.clear();
        newrepassword.clear();
        newusername.clear();
        newfname.clear();
        newmname.clear();
        newlname.clear();
        passwordtext.clear();
        newpasswordtext.clear();
        newrepasswordtext.clear();
        newrepassword.getStyleClass().remove("false_field");
        newrepassword.getStyleClass().add("true_field");
        newrepasswordtext.getStyleClass().remove("false_field");
        newrepasswordtext.getStyleClass().add("true_field");
    }

    private void validationPassword(String newValue) {
        if (!newrepassword.getText().isEmpty() && !newValue.isEmpty()
                && newpassword.getText().equals(newValue)) {
            newrepassword.getStyleClass().remove("false_field");
            newrepasswordtext.getStyleClass().remove("false_field");
            newrepassword.getStyleClass().add("true_field");
            newrepasswordtext.getStyleClass().add("true_field");
            newrepasswordvalid = true;
        } else {
            newrepassword.getStyleClass().remove("true_field");
            newrepassword.getStyleClass().add("false_field");
            newrepasswordtext.getStyleClass().remove("true_field");
            newrepasswordtext.getStyleClass().add("false_field");
            newrepasswordvalid = false;
        }
    }

    private boolean checkFill() {
        boolean check = true;

        if (newfname.getText().isEmpty()) {
            newfname.getStyleClass().add("false_field");
            check = false;
        }
        if (newmname.getText().isEmpty()) {
            newmname.getStyleClass().add("false_field");
            check = false;
        }
        if (newlname.getText().isEmpty()) {
            newlname.getStyleClass().add("false_field");
            check = false;
        }
        if (newusername.getText().isEmpty()) {
            newusername.getStyleClass().add("false_field");
            check = false;
        }
        if (newpasswordtext.getText().isEmpty()) {
            newpasswordtext.getStyleClass().add("false_field");
            check = false;
        }
        if (newrepasswordtext.getText().isEmpty()) {
            newrepasswordtext.getStyleClass().add("false_field");
            check = false;
        }
        if (newpassword.getText().isEmpty()) {
            newpassword.getStyleClass().add("false_field");
            check = false;
        }
        if (newrepassword.getText().isEmpty()) {
            newrepassword.getStyleClass().add("false_field");
            check = false;
        }

        return check;
    }
}
