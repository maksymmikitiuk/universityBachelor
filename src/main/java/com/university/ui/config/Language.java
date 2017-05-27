package com.university.ui.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Properties;

public class Language {
    public static Properties propertiesText = new Properties();

    private static void initText(String lang) throws FileNotFoundException {
        try {
            InputStream in = Language.class.getClassLoader().getResourceAsStream("ui/config/" + lang + ".ini");
            InputStreamReader inR = new InputStreamReader(in, "UTF-8");
            propertiesText.load(inR);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initial() throws IOException {
        Properties properties = new Properties();
        try {
            InputStream inputStream = Language.class.getClassLoader().getResourceAsStream("ui/config/settings.ini");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Если LANG пустой, то язык системы
        initText((properties.getProperty("LANG").equals("")) ? Locale.getDefault().getLanguage() : properties.getProperty("LANG"));
    }
}
