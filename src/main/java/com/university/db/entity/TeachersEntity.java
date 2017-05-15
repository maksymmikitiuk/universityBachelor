package com.university.db.entity;

import javax.persistence.*;

/**
 * Created by Антон Микитюк on 17.11.2016.
 */
@Entity
@Table(name = "teachers", schema = "university")

public class TeachersEntity {
    @Id
    @Column(name = "idteachers")
    private int idteachers;
    private String firstName;
    private String middleName;
    private String lastName;
    private String lfmName;

    @Basic
    @Column(name = "lfmName")
    public String getLfmName() {
        return lfmName;
    }

    public void setLfmName(String lfmName) {
        this.lfmName = lfmName;
    }

    public int getIdteachers() {
        return idteachers;
    }

    public void setIdteachers(int idteachers) {
        this.idteachers = idteachers;
    }

    @Basic
    @Column(name = "firstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "middleName")
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Basic
    @Column(name = "lastName")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @ManyToOne
    @JoinColumn(name = "idchairs")
    private ChairsEntity idchairs;

    public ChairsEntity getIdchairs() {
        return idchairs;
    }

    public void setIdchairs(ChairsEntity idchairs) {
        this.idchairs = idchairs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeachersEntity that = (TeachersEntity) o;

        if (idteachers != that.idteachers) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (middleName != null ? !middleName.equals(that.middleName) : that.middleName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idteachers;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return lastName + " " + firstName + " " + middleName;
    }
}
