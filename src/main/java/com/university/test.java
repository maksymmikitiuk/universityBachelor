package com.university;


import com.university.db.control.DBController;
import com.university.db.entity.DictionaryEntity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class test {

    public static void main(String[] args) throws IOException {
        DBController.getSession();
        List<DictionaryEntity> dictionaryEntities = new ArrayList<>();
        try {
            File file = new File("/home/maksymmikitiuk/Desktop/wrd.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(file), "UTF8"));
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] word = line.split(" ");
                DictionaryEntity dictionaryEntity = new DictionaryEntity();
                dictionaryEntity.setWord(word[0]);
                dictionaryEntity.setNormal(word[1]);
                dictionaryEntities.add(dictionaryEntity);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int col = dictionaryEntities.size();

        for (DictionaryEntity entity:dictionaryEntities){
            System.out.println("Осталось " + col);
            new DBController().create(entity);
            col--;
        }
    }
}
