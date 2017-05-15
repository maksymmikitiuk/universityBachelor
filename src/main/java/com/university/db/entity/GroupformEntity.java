package com.university.db.entity;

import javax.persistence.*;

/**
 * Created by Антон Микитюк on 17.11.2016.
 */
@Entity
@Table(name = "groupform", schema = "university")
public class GroupformEntity {
    private int idgroupForm;
    private String name;

    @Id
    @Column(name = "idgroupForm")
    public int getIdgroupForm() {
        return idgroupForm;
    }

    public void setIdgroupForm(int idgroupForm) {
        this.idgroupForm = idgroupForm;
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

        GroupformEntity that = (GroupformEntity) o;

        if (idgroupForm != that.idgroupForm) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idgroupForm;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return name;
    }
}
