package com.university.externalFile;

import com.university.db.entity.StudentsEntity;

import java.util.List;

public class UpLoadToDB {
    public UpLoadToDB() {
    }

    public List<StudentsEntity> upLoadToUser(String path){
        return new WorkWithExcel(path).getColumnList();
    }
}
