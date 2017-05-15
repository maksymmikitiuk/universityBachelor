package com.university.db.entity;

import javax.persistence.*;

/**
 * Created by Антон Микитюк on 17.11.2016.
 */
@Entity
@Table(name = "diplomaform", schema = "university")
public class DiplomaformEntity {
    private int iddiplomaForm;
    private String name;
    private QualificationlevelEntity qualification;

    @Id
    @Column(name = "iddiplomaForm")
    public int getIddiplomaForm() {
        return iddiplomaForm;
    }

    public void setIddiplomaForm(int iddiplomaForm) {
        this.iddiplomaForm = iddiplomaForm;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name = "qualification_id")
    public QualificationlevelEntity getQualification() {
        return qualification;
    }

    public void setQualification(QualificationlevelEntity qualification) {
        this.qualification = qualification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DiplomaformEntity that = (DiplomaformEntity) o;

        if (iddiplomaForm != that.iddiplomaForm) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = iddiplomaForm;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return name;
    }
}
