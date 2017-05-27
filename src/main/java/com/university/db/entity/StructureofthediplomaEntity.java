package com.university.db.entity;

import javax.persistence.*;

@Entity
@Table(name = "structureofthediploma", schema = "university")
public class StructureofthediplomaEntity {
    private int idstructureOfTheDiploma;
    private DocumenttypeEntity iddocumentType;
    private DiplomatypeEntity iddiplomaType;

    @Id
    @Column(name = "idstructureOfTheDiploma")
    public int getIdstructureOfTheDiploma() {
        return idstructureOfTheDiploma;
    }

    public void setIdstructureOfTheDiploma(int idstructureOfTheDiploma) {
        this.idstructureOfTheDiploma = idstructureOfTheDiploma;
    }

    @ManyToOne
    @JoinColumn(name = "iddocumentType")
    public DocumenttypeEntity getIddocumentType() {
        return iddocumentType;
    }

    public void setIddocumentType(DocumenttypeEntity iddocumentType) {
        this.iddocumentType = iddocumentType;
    }

    @ManyToOne
    @JoinColumn(name = "iddiplomaType")
    public DiplomatypeEntity getIddiplomaType() {
        return iddiplomaType;
    }

    public void setIddiplomaType(DiplomatypeEntity iddiplomaType) {
        this.iddiplomaType = iddiplomaType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StructureofthediplomaEntity that = (StructureofthediplomaEntity) o;

        if (idstructureOfTheDiploma != that.idstructureOfTheDiploma) return false;
        if (iddocumentType != that.iddocumentType) return false;
        if (iddiplomaType != that.iddiplomaType) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idstructureOfTheDiploma;
        return result;
    }
}
