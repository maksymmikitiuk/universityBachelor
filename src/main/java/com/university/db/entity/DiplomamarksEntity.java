package com.university.db.entity;

import javax.persistence.*;

/**
 * Created by maksymmikitiuk on 5/3/17.
 */
@Entity
@Table(name = "diplomamarks", schema = "university")
public class DiplomamarksEntity {
    private int iddiplomaMarks;
    private TeachersEntity owner;
    private TypeOwnerMarkEntity typeOwner;
    private MarksEntity mark;
    private Integer point;
    private DiplomasubjectsEntity id_diploma;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "iddiplomaMarks")
    public int getIddiplomaMarks() {
        return iddiplomaMarks;
    }

    public void setIddiplomaMarks(int iddiplomaMarks) {
        this.iddiplomaMarks = iddiplomaMarks;
    }

    @ManyToOne
    @JoinColumn(name = "owner")
    public TeachersEntity getOwner() {
        return owner;
    }

    public void setOwner(TeachersEntity owner) {
        this.owner = owner;
    }

    @ManyToOne
    @JoinColumn(name = "typeOwner")
    public TypeOwnerMarkEntity getTypeOwner() {
        return typeOwner;
    }

    public void setTypeOwner(TypeOwnerMarkEntity typeOwner) {
        this.typeOwner = typeOwner;
    }

    @ManyToOne
    @JoinColumn(name = "mark")
    public MarksEntity getMark() {
        return mark;
    }

    public void setMark(MarksEntity mark) {
        this.mark = mark;
    }

    @Basic
    @Column(name = "point")
    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    @ManyToOne
    @JoinColumn(name = "id_diploma")
    public DiplomasubjectsEntity getId_diploma() {
        return id_diploma;
    }

    public void setId_diploma(DiplomasubjectsEntity id_diploma) {
        this.id_diploma = id_diploma;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DiplomamarksEntity that = (DiplomamarksEntity) o;

        if (iddiplomaMarks != that.iddiplomaMarks) return false;
        if (owner != null ? !owner.equals(that.owner) : that.owner != null) return false;
        if (typeOwner != null ? !typeOwner.equals(that.typeOwner) : that.typeOwner != null) return false;
        if (mark != null ? !mark.equals(that.mark) : that.mark != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = iddiplomaMarks;
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (typeOwner != null ? typeOwner.hashCode() : 0);
        result = 31 * result + (mark != null ? mark.hashCode() : 0);
        return result;
    }
}
