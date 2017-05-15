package com.university.db.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Антон Микитюк on 17.11.2016.
 */
public class TeachersEntityPK implements Serializable {
    private int idteachers;
    private int idchairs;

    @Column(name = "idteachers")
    @Id
    public int getIdteachers() {
        return idteachers;
    }

    public void setIdteachers(int idteachers) {
        this.idteachers = idteachers;
    }

    @Column(name = "idchairs")
    @Id
    public int getIdchairs() {
        return idchairs;
    }

    public void setIdchairs(int idchairs) {
        this.idchairs = idchairs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeachersEntityPK that = (TeachersEntityPK) o;

        if (idteachers != that.idteachers) return false;
        if (idchairs != that.idchairs) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idteachers;
        result = 31 * result + idchairs;
        return result;
    }
}
