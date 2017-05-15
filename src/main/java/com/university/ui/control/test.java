package com.university.ui.control;

import javafx.fxml.Initializable;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by maksymmikitiuk on 4/23/17.
 */
public class test implements Initializable {
    public TextFlow text;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Text t = new Text("You can create several Text nodes and lay them out in a single text flow by using the TextFlow layout pane");
        t.setFill(Color.WHITE);
        t.setFont(Font.font(16));
        text.getChildren().addAll(t);
    }
}
