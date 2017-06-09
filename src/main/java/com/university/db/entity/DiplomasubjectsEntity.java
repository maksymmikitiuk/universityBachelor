package com.university.db.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by maksymmikitiuk on 5/7/17.
 */
@Entity
@Table(name = "diplomasubjects", schema = "university")
public class DiplomasubjectsEntity {
    private int iddiplomaSubjects;
    private String subject;
    private Timestamp defencediploma;
    private String tag;
    private BigDecimal plag;
    private TeachersEntity curator;
    private TeachersEntity reviewer;
    private StudentsEntity student;
    private DiplomatypeEntity type;

    @ManyToOne
    @JoinColumn(name = "student_id")
    public StudentsEntity getStudent() {
        return student;
    }

    public void setStudent(StudentsEntity student) {
        this.student = student;
    }

    @ManyToOne
    @JoinColumn(name = "curator_id")
    public TeachersEntity getCurator() {
        return curator;
    }

    public void setCurator(TeachersEntity curator) {
        this.curator = curator;
    }

    @ManyToOne
    @JoinColumn(name = "reviewer_id")
    public TeachersEntity getReviewer() {
        return reviewer;
    }

    public void setReviewer(TeachersEntity reviewer) {
        this.reviewer = reviewer;
    }

    @ManyToOne
    @JoinColumn(name = "type_id")
    public DiplomatypeEntity getType() {
        return type;
    }

    public void setType(DiplomatypeEntity type) {
        this.type = type;
    }


    @Id
    @Column(name = "iddiplomaSubjects")
    public int getIddiplomaSubjects() {
        return iddiplomaSubjects;
    }

    public void setIddiplomaSubjects(int iddiplomaSubjects) {
        this.iddiplomaSubjects = iddiplomaSubjects;
    }

    @Basic
    @Column(name = "subject")
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Basic
    @Column(name = "defencediploma")
    public Timestamp getDefencediploma() {
        return defencediploma;
    }

    public void setDefencediploma(Timestamp defencediploma) {
        this.defencediploma = defencediploma;
    }

    @Basic
    @Column(name = "tag")
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Basic
    @Column(name = "plag", columnDefinition="Decimal(10,2) default '0.00'")
    public BigDecimal getPlag() {
        return plag;
    }

    public void setPlag(BigDecimal plag) {
        this.plag = plag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DiplomasubjectsEntity that = (DiplomasubjectsEntity) o;

        if (iddiplomaSubjects != that.iddiplomaSubjects) return false;
        if (subject != null ? !subject.equals(that.subject) : that.subject != null) return false;
        if (defencediploma != null ? !defencediploma.equals(that.defencediploma) : that.defencediploma != null)
            return false;
        if (tag != null ? !tag.equals(that.tag) : that.tag != null) return false;
        if (plag != null ? !plag.equals(that.plag) : that.plag != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = iddiplomaSubjects;
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (defencediploma != null ? defencediploma.hashCode() : 0);
        result = 31 * result + (tag != null ? tag.hashCode() : 0);
        result = 31 * result + (plag != null ? plag.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return subject;
    }
}
