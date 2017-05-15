package com.university.Antiplagiarism;

import com.university.db.control.SubjectController;
import com.university.db.entity.DiplomasubjectsEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by maksymmikitiuk on 5/1/17.
 */
public class CheckAntiplagiarism {
    private static final int PERCENT = 60;
    private List<String> newSubject = new ArrayList<>();

    public CheckAntiplagiarism(String subject) {
        this.newSubject = Arrays.asList(subject.split(";"));
    }

    public List<DiplomasubjectsEntity> getSubjects() {
        List<DiplomasubjectsEntity> allSubject = new ArrayList<>();

        for (DiplomasubjectsEntity subject : new SubjectController().getAllSubject())
            if (isValid(subject.getTag()))
                allSubject.add(subject);

        return allSubject;
    }

    private boolean isValid(String tag) {
        String[] tagList = tag.split(";");
        int cnt = 0;

        for (String t : tagList)
            if (newSubject.contains(t))
                cnt++;

        return (cnt * 100 / ((newSubject.size() == 0) ? 1 : newSubject.size()) > PERCENT) ? true : false;
    }
}
