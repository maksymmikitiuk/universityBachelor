package com.university.ui.animation;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * Created by maksy on 12-Jul-16.
 */
public class animation {
    public animation() {
    }

    public void showFilter(AnchorPane anchorPane, Label show, Label hide, AnchorPane work, boolean isShow){
        final Timeline slideOut = new Timeline();
        int x, o1, o2;
        slideOut.setCycleCount(1);
        slideOut.setAutoReverse(false);
        if (isShow) {
            x = 0;
            o1 = 1;
            o2 = 0;
            AnchorPane.setRightAnchor(work, 231.0);
        } else {
            x = 231;
            o1 = 0;
            o2 = 1;
            AnchorPane.setRightAnchor(work, 40.0);
        }
        final KeyValue kv1 = new KeyValue(anchorPane.translateXProperty(), x);
        final KeyFrame kf1 = new KeyFrame(Duration.millis(100), kv1);
        final KeyValue kv2 = new KeyValue(hide.opacityProperty(), o1);
        final KeyFrame kf2 = new KeyFrame(Duration.millis(100), kv2);
        final KeyValue kv3 = new KeyValue(show.opacityProperty(), o2);
        final KeyFrame kf3 = new KeyFrame(Duration.millis(100), kv3);
        slideOut.getKeyFrames().addAll(kf1, kf2, kf3);
        slideOut.play();
    }

    public static boolean Position;

    public void showCreateUser(AnchorPane anchorPane) {
        final Timeline slideOut = new Timeline();
        int x;
        slideOut.setCycleCount(1);
        slideOut.setAutoReverse(false);
        if (Position) {
            x = 0;
            Position = false;
        } else {
            x = -500;
            Position = true;
        }
        final KeyValue kv1 = new KeyValue(anchorPane.translateXProperty(), x);
        final KeyFrame kf1 = new KeyFrame(Duration.millis(500), kv1);
        slideOut.getKeyFrames().addAll(kf1);
        slideOut.play();
    }

    public static boolean isPosition() {
        return Position;
    }
}
