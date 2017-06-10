package com.university.externalFile;

import com.university.db.control.SettingsController;
import com.university.db.entity.StudentsEntity;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by maksymmikitiuk on 5/31/17.
 */
public class WorkWithFile {
    private static String OS = System.getProperty("os.name").toLowerCase();

    public WorkWithFile() {
    }

    public String copyFile(File from, StudentsEntity student) {
        String path = new SettingsController().getSettings().get(0).getPath()
                + "/" + student.getIdstudents() + "/" + getDate();
        File to = new File(path);

        if (!to.exists())
            to.mkdirs();

        path = to.getPath() + "/" + from.getName();

        try {
            Files.copy(from.toPath(), new File(path).toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return to + "/" + from.getName();
    }

    private String getDate() {
        DateFormat df = new SimpleDateFormat("MM-dd-yyyy-HH-mm-ss");
        Date today = Calendar.getInstance().getTime();
        String reportDate = df.format(today);
        return reportDate;
    }

    public void openFile(String path) throws IOException {
        File file = new File(path);

        //first check if Desktop is supported by Platform or not
//        if(!Desktop.isDesktopSupported()){
//            System.out.println("Desktop is not supported");
//            return;
//        }
//
//        Desktop desktop = Desktop.getDesktop();
//        if(file.exists()) desktop.open(file);
        String command = "";
        if (isUnix())
            command = "nautilus";
        if (isWindows())
            command = "explorer";
        if (isMac())
            command = "open";

        String output = executeCommand(command + " " + file.getPath());
        System.out.println(output);
    }

    private String executeCommand(String command) {

        StringBuffer output = new StringBuffer();

        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return output.toString();

    }

    public static boolean isWindows() {

        return (OS.indexOf("win") >= 0);

    }

    public static boolean isMac() {

        return (OS.indexOf("mac") >= 0);

    }

    public static boolean isUnix() {

        return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0);

    }
}
