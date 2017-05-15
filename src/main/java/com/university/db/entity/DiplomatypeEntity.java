package com.university.db.entity;

import javax.persistence.*;

/**
 * Created by maksymmikitiuk on 5/7/17.
 */
@Entity
@Table(name = "diplomatype", schema = "university")
public class DiplomatypeEntity {
    private int iddiplomaType;
    private String name;
    private String comment;
    private DiplomaformEntity form;

    @Id
    @Column(name = "iddiplomaType")
    public int getIddiplomaType() {
        return iddiplomaType;
    }

    public void setIddiplomaType(int iddiplomaType) {
        this.iddiplomaType = iddiplomaType;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @ManyToOne
    @JoinColumn(name = "form_id")
    public DiplomaformEntity getForm() {
        return form;
    }

    public void setForm(DiplomaformEntity form) {
        this.form = form;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DiplomatypeEntity that = (DiplomatypeEntity) o;

        if (iddiplomaType != that.iddiplomaType) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = iddiplomaType;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return name;
    }
}
