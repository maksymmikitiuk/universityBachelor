package com.university.db.entity;

import javax.persistence.*;

/**
 * Created by Антон Микитюк on 17.11.2016.
 */
@Entity
@Table(name = "documenttype", schema = "university", catalog = "")
public class DocumenttypeEntity {
    private int iddocumentType;
    private String typename;

    @Id
    @Column(name = "iddocumentType")
    public int getIddocumentType() {
        return iddocumentType;
    }

    public void setIddocumentType(int iddocumentType) {
        this.iddocumentType = iddocumentType;
    }

    @Basic
    @Column(name = "typename")
    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DocumenttypeEntity that = (DocumenttypeEntity) o;

        if (iddocumentType != that.iddocumentType) return false;
        if (typename != null ? !typename.equals(that.typename) : that.typename != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = iddocumentType;
        result = 31 * result + (typename != null ? typename.hashCode() : 0);
        return result;
    }
}
