package com.university.db.entity;

import javax.persistence.*;

/**
 * Created by Антон Микитюк on 17.11.2016.
 */
@Entity
@Table(name = "chairs", schema = "university")
public class ChairsEntity {
    private int idchairs;
    private String abbreviation;
    private String name;

    @Id
    @Column(name = "idchairs")
    public int getIdchairs() {
        return idchairs;
    }

    public void setIdchairs(int idchairs) {
        this.idchairs = idchairs;
    }

    @Basic
    @Column(name = "abbreviation")
    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChairsEntity that = (ChairsEntity) o;

        if (idchairs != that.idchairs) return false;
        if (abbreviation != null ? !abbreviation.equals(that.abbreviation) : that.abbreviation != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idchairs;
        result = 31 * result + (abbreviation != null ? abbreviation.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return abbreviation;
    }
}
