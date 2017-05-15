package com.university.db.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Антон Микитюк on 17.11.2016.
 */
@Entity
@Table(name = "documentregistration", schema = "university", catalog = "")
@IdClass(DocumentregistrationEntityPK.class)
public class DocumentregistrationEntity {
    private int iddocumentRegistration;
    private Date documentregistration;
    private int idusers;
    private int iddiplomaSubjects;
    private String path;

    @Id
    @Column(name = "iddocumentRegistration")
    public int getIddocumentRegistration() {
        return iddocumentRegistration;
    }

    public void setIddocumentRegistration(int iddocumentRegistration) {
        this.iddocumentRegistration = iddocumentRegistration;
    }

    @Basic
    @Column(name = "documentregistration")
    public Date getDocumentregistration() {
        return documentregistration;
    }

    public void setDocumentregistration(Date documentregistration) {
        this.documentregistration = documentregistration;
    }

    @Id
    @Column(name = "idusers")
    public int getIdusers() {
        return idusers;
    }

    public void setIdusers(int idusers) {
        this.idusers = idusers;
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
    @Column(name = "path")
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DocumentregistrationEntity that = (DocumentregistrationEntity) o;

        if (iddocumentRegistration != that.iddocumentRegistration) return false;
        if (idusers != that.idusers) return false;
        if (iddiplomaSubjects != that.iddiplomaSubjects) return false;
        if (documentregistration != null ? !documentregistration.equals(that.documentregistration) : that.documentregistration != null)
            return false;
        if (path != null ? !path.equals(that.path) : that.path != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = iddocumentRegistration;
        result = 31 * result + (documentregistration != null ? documentregistration.hashCode() : 0);
        result = 31 * result + idusers;
        result = 31 * result + iddiplomaSubjects;
        result = 31 * result + (path != null ? path.hashCode() : 0);
        return result;
    }
}
