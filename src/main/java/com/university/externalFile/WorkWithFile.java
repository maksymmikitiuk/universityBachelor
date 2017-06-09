package com.university.externalFile;

import com.university.db.control.SettingsController;
import com.university.db.entity.StudentsEntity;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by maksymmikitiuk on 5/31/17.
 */
public class WorkWithFile {
    public WorkWithFile() {
    }

    public String copyFile(File from, StudentsEntity student){
        String path = new SettingsController().getSettings().get(0).getPath()
                + "/" + student.getIdstudents() + "/" + getDate();
        File to = new File(path);

        if(!to.exists())
            to.mkdirs();

        path = to.getPath() + "/" + from.getName();

        try {
            Files.copy(from.toPath(), new File(path).toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return to + "/" + from.getName();
    }

    private String getDate(){
        DateFormat df = new SimpleDateFormat("MM-dd-yyyy-HH-mm-ss");
        Date today = Calendar.getInstance().getTime();
        String reportDate = df.format(today);
        return reportDate;
    }

    public void openFile(String path) throws IOException {
        File file = new File(path);

        //first check if Desktop is supported by Platform or not
        if(!Desktop.isDesktopSupported()){
            System.out.println("Desktop is not supported");
            return;
        }

        Desktop desktop = Desktop.getDesktop();
        if(file.exists()) desktop.open(file);
    }
}
