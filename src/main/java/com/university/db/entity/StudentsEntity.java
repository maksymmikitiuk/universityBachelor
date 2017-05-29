package com.university.db.entity;

import javax.persistence.*;

/**
 * Created by Антон Микитюк on 17.11.2016.
 */
@Entity
@Table(name = "students", schema = "university", catalog = "")
public class StudentsEntity {

    @Id
    @Column(name = "idstudents")
    private int idstudents;
    private String studentidcard;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String phone;
    private String lfmName;

    @ManyToOne
    @JoinColumn(name = "idgroups")
    private GroupsEntity idgroups;

    @Basic
    @Column(name = "lfmName")
    public String getLfmiddleName() {
        return lfmName;
    }

    public void setLfmiddleName(String lfmName) {
        this.lfmName = lfmName;
    }

    public int getIdstudents() {
        return idstudents;
    }

    public void setIdstudents(int idstudents) {
        this.idstudents = idstudents;
    }

    @Basic
    @Column(name = "studentidcard")
    public String getStudentidcard() {
        return studentidcard;
    }

    public void setStudentidcard(String studentidcard) {
        this.studentidcard = studentidcard;
    }

    @Basic
    @Column(name = "firstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String fName) {
        this.firstName = fName;
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

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public GroupsEntity getIdgroups() {
        return idgroups;
    }

    public void setIdgroups(GroupsEntity idgroups) {
        this.idgroups = idgroups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentsEntity that = (StudentsEntity) o;

        if (idstudents != that.idstudents) return false;
        if (idgroups != that.idgroups) return false;
        if (studentidcard != null ? !studentidcard.equals(that.studentidcard) : that.studentidcard != null)
            return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (middleName != null ? !middleName.equals(that.middleName) : that.middleName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idstudents;
        result = 31 * result + (studentidcard != null ? studentidcard.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return lastName + " " + firstName + " " + middleName;
    }
}
