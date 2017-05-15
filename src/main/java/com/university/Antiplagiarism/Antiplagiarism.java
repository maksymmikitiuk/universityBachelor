package com.university.Antiplagiarism;

import com.university.db.control.DictionaryController;
import com.university.db.control.ExceptionsController;

/**
 * Created by maksymmikitiuk on 4/30/17.
 */
public class Antiplagiarism {
    public Antiplagiarism() {
    }

    public String getTagSubject(String subject) {
        String tagSubject = "";
        for (String word : subject.split(" "))
            if (new ExceptionsController().getЕxception(word))
                tagSubject += getWord(word.replace("`", "'")) + ";";
        return tagSubject;
    }

    public String findWord(String param) {
        return getWord(param);
    }

    private String getWord(String param) {
        return new DictionaryController().getWord(param);
    }
}
