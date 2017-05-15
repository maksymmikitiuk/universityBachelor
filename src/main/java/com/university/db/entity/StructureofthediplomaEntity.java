package com.university.db.entity;

import javax.persistence.*;

/**
 * Created by Антон Микитюк on 17.11.2016.
 */
@Entity
@Table(name = "structureofthediploma", schema = "university", catalog = "")
@IdClass(StructureofthediplomaEntityPK.class)
public class StructureofthediplomaEntity {
    private int idstructureOfTheDiploma;
    private int iddocumentType;
    private int iddiplomaType;

    @Id
    @Column(name = "idstructureOfTheDiploma")
    public int getIdstructureOfTheDiploma() {
        return idstructureOfTheDiploma;
    }

    public void setIdstructureOfTheDiploma(int idstructureOfTheDiploma) {
        this.idstructureOfTheDiploma = idstructureOfTheDiploma;
    }

    @Id
    @Column(name = "iddocumentType")
    public int getIddocumentType() {
        return iddocumentType;
    }

    public void setIddocumentType(int iddocumentType) {
        this.iddocumentType = iddocumentType;
    }

    @Id
    @Column(name = "iddiplomaType")
    public int getIddiplomaType() {
        return iddiplomaType;
    }

    public void setIddiplomaType(int iddiplomaType) {
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
        result = 31 * result + iddocumentType;
        result = 31 * result + iddiplomaType;
        return result;
    }
}
