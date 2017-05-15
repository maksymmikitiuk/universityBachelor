package com.university.db.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Антон Микитюк on 17.11.2016.
 */
public class StudentsEntityPK implements Serializable {
    private int idstudents;
    private int idgroups;

    @Column(name = "idstudents")
    @Id
    public int getIdstudents() {
        return idstudents;
    }

    public void setIdstudents(int idstudents) {
        this.idstudents = idstudents;
    }

    @Column(name = "idgroups")
    @Id
    public int getIdgroups() {
        return idgroups;
    }

    public void setIdgroups(int idgroups) {
        this.idgroups = idgroups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentsEntityPK that = (StudentsEntityPK) o;

        if (idstudents != that.idstudents) return false;
        if (idgroups != that.idgroups) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idstudents;
        result = 31 * result + idgroups;
        return result;
    }
}
