package com.university.db.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Антон Микитюк on 17.11.2016.
 */
public class StructureofthediplomaEntityPK implements Serializable {
    private int idstructureOfTheDiploma;
    private int iddocumentType;
    private int iddiplomaType;

    @Column(name = "idstructureOfTheDiploma")
    @Id
    public int getIdstructureOfTheDiploma() {
        return idstructureOfTheDiploma;
    }

    public void setIdstructureOfTheDiploma(int idstructureOfTheDiploma) {
        this.idstructureOfTheDiploma = idstructureOfTheDiploma;
    }

    @Column(name = "iddocumentType")
    @Id
    public int getIddocumentType() {
        return iddocumentType;
    }

    public void setIddocumentType(int iddocumentType) {
        this.iddocumentType = iddocumentType;
    }

    @Column(name = "iddiplomaType")
    @Id
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

        StructureofthediplomaEntityPK that = (StructureofthediplomaEntityPK) o;

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
