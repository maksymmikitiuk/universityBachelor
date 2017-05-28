package com.university.ui.control;

import com.university.db.control.DBController;
import com.university.db.control.MarksController;
import com.university.db.entity.MarksEntity;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by maksymmikitiuk on 5/16/17.
 */
public class SettingsController implements Initializable {
    public TextField ns0, ns1, ns2, ns3, ns4, ns5, ns6,
            nsn0, nsn1, nsn2, nsn3, nsn4, nsn5, nsn6,
            ects0, ects1, ects2, ects3, ects4, ects5, ects6,
            t0, t1, t2, t3, t4, t5, t6,
            f0, f1, f2, f3, f4, f5, f6,
            id0, id1, id2, id3, id4, id5, id6;
    public Button save_mark;
    private List<List<TextField>> col = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        save_mark.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                saveMark();
            }
        });

        List<TextField> r0 = new ArrayList<>();
        r0.add(id0);
        r0.add(ns0);
        r0.add(nsn0);
        r0.add(ects0);
        r0.add(f0);
        r0.add(t0);
        col.add(r0);

        List<TextField> r1 = new ArrayList<>();
        r1.add(id1);
        r1.add(ns1);
        r1.add(nsn1);
        r1.add(ects1);
        r1.add(f1);
        r1.add(t1);
        col.add(r1);

        List<TextField> r2 = new ArrayList<>();
        r2.add(id2);
        r2.add(ns2);
        r2.add(nsn2);
        r2.add(ects2);
        r2.add(f2);
        r2.add(t2);
        col.add(r2);

        List<TextField> r3 = new ArrayList<>();
        r3.add(id3);
        r3.add(ns3);
        r3.add(nsn3);
        r3.add(ects3);
        r3.add(f3);
        r3.add(t3);
        col.add(r3);

        List<TextField> r4 = new ArrayList<>();
        r4.add(id4);
        r4.add(ns4);
        r4.add(nsn4);
        r4.add(ects4);
        r4.add(f4);
        r4.add(t4);
        col.add(r4);

        List<TextField> r5 = new ArrayList<>();
        r5.add(id5);
        r5.add(ns5);
        r5.add(nsn5);
        r5.add(ects5);
        r5.add(f5);
        r5.add(t5);
        col.add(r5);

        List<TextField> r6 = new ArrayList<>();
        r6.add(id6);
        r6.add(ns6);
        r6.add(nsn6);
        r6.add(ects6);
        r6.add(f6);
        r6.add(t6);
        col.add(r6);

        fillMarks();
    }

    private void fillMarks() {
        List<MarksEntity> marks = new MarksController().getAllMarks();

        for (int i = 0; i < 7; i++) {
            col.get(i).get(1).setText(marks.get(i).getNationalscale());
            col.get(i).get(2).setText(marks.get(i).getNationalscalenumber().toString());
            col.get(i).get(3).setText(marks.get(i).getEcts());
            col.get(i).get(4).setText(marks.get(i).getPointsfrom().toString());
            col.get(i).get(5).setText(marks.get(i).getPointsto().toString());
            col.get(i).get(0).setText(String.valueOf(marks.get(i).getIdmarks()));
        }
    }

    private void saveMark() {
        for (int i = 0; i < 7; i++) {
            MarksEntity marksEntity = new MarksEntity();

            marksEntity.setNationalscale(col.get(i).get(1).getText());
            marksEntity.setNationalscalenumber(Integer.valueOf(col.get(i).get(2).getText()));
            marksEntity.setEcts(col.get(i).get(3).getText());
            marksEntity.setPointsfrom(Integer.valueOf(col.get(i).get(4).getText()));
            marksEntity.setPointsto(Integer.valueOf(col.get(i).get(5).getText()));
            marksEntity.setIdmarks(Integer.valueOf(col.get(i).get(0).getText()));

            new DBController().update(marksEntity);
        }

        fillMarks();
    }

}

