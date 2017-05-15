package com.university.externalFile;

import com.university.db.entity.StudentsEntity;

import java.util.List;

public class upLoadToDB {
    public upLoadToDB() {
    }

    public List<StudentsEntity> upLoadToUser(String path){
        return new workWithExcel(path).getColumnList();
    }
}
