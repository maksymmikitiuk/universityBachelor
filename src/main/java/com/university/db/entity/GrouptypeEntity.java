package com.university.db.entity;

import javax.persistence.*;

/**
 * Created by Антон Микитюк on 17.11.2016.
 */
@Entity
@Table(name = "grouptype", schema = "university")
public class GrouptypeEntity {
    private int idgroupType;
    private String name;

    @Id
    @Column(name = "idgroupType")
    public int getIdgroupType() {
        return idgroupType;
    }

    public void setIdgroupType(int idgroupType) {
        this.idgroupType = idgroupType;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GrouptypeEntity that = (GrouptypeEntity) o;

        if (idgroupType != that.idgroupType) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idgroupType;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return name;
    }
}
