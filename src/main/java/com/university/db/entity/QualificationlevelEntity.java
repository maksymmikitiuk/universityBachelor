package com.university.db.entity;

import javax.persistence.*;

/**
 * Created by maksymmikitiuk on 5/7/17.
 */
@Entity
@Table(name = "qualificationlevel", schema = "university", catalog = "")
public class QualificationlevelEntity {
    private int idqualificationLevel;
    private String name;

    @Id
    @Column(name = "idqualificationLevel")
    public int getIdqualificationLevel() {
        return idqualificationLevel;
    }

    public void setIdqualificationLevel(int idqualificationLevel) {
        this.idqualificationLevel = idqualificationLevel;
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

        QualificationlevelEntity that = (QualificationlevelEntity) o;

        if (idqualificationLevel != that.idqualificationLevel) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idqualificationLevel;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
