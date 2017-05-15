package com.university.db.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Антон Микитюк on 17.11.2016.
 */
public class DocumentregistrationEntityPK implements Serializable {
    private int iddocumentRegistration;
    private int idusers;
    private int iddiplomaSubjects;

    @Column(name = "iddocumentRegistration")
    @Id
    public int getIddocumentRegistration() {
        return iddocumentRegistration;
    }

    public void setIddocumentRegistration(int iddocumentRegistration) {
        this.iddocumentRegistration = iddocumentRegistration;
    }

    @Column(name = "idusers")
    @Id
    public int getIdusers() {
        return idusers;
    }

    public void setIdusers(int idusers) {
        this.idusers = idusers;
    }

    @Column(name = "iddiplomaSubjects")
    @Id
    public int getIddiplomaSubjects() {
        return iddiplomaSubjects;
    }

    public void setIddiplomaSubjects(int iddiplomaSubjects) {
        this.iddiplomaSubjects = iddiplomaSubjects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DocumentregistrationEntityPK that = (DocumentregistrationEntityPK) o;

        if (iddocumentRegistration != that.iddocumentRegistration) return false;
        if (idusers != that.idusers) return false;
        if (iddiplomaSubjects != that.iddiplomaSubjects) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = iddocumentRegistration;
        result = 31 * result + idusers;
        result = 31 * result + iddiplomaSubjects;
        return result;
    }
}
