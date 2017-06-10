package com.university.db.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Антон Микитюк on 17.11.2016.
 */
@Entity
@Table(name = "documentregistration", schema = "university")
public class DocumentregistrationEntity {
    private int iddocumentRegistration;
    private Timestamp documentregistration;
    private UsersEntity idusers;
    private DiplomasubjectsEntity iddiplomaSubjects;
    private String path;
    private DocumenttypeEntity id_type;

    @ManyToOne
    @JoinColumn(name = "id_type")
    public DocumenttypeEntity getId_type() {
        return id_type;
    }

    public void setId_type(DocumenttypeEntity id_type) {
        this.id_type = id_type;
    }

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
    public Timestamp getDocumentregistration() {
        return documentregistration;
    }

    public void setDocumentregistration(Timestamp documentregistration) {
        this.documentregistration = documentregistration;
    }

    @ManyToOne
    @JoinColumn(name = "idusers")
    public UsersEntity getIdusers() {
        return idusers;
    }

    public void setIdusers(UsersEntity idusers) {
        this.idusers = idusers;
    }

    @ManyToOne
    @JoinColumn(name = "iddiplomaSubjects", columnDefinition = "INT(11) default null")
    public DiplomasubjectsEntity getIddiplomaSubjects() {
        return iddiplomaSubjects;
    }

    public void setIddiplomaSubjects(DiplomasubjectsEntity iddiplomaSubjects) {
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
        result = 31 * result + (path != null ? path.hashCode() : 0);
        return result;
    }
}
