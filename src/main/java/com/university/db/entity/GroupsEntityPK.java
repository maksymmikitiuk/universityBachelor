package com.university.db.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Антон Микитюк on 17.11.2016.
 */
public class GroupsEntityPK implements Serializable {
    private int idgroups;
    private int idchairs;
    private int idqualificationLevel;
    private int idgroupForm;

    @Column(name = "idgroups")
    @Id
    public int getIdgroups() {
        return idgroups;
    }

    public void setIdgroups(int idgroups) {
        this.idgroups = idgroups;
    }

    @Column(name = "idchairs")
    @Id
    public int getIdchairs() {
        return idchairs;
    }

    public void setIdchairs(int idchairs) {
        this.idchairs = idchairs;
    }

    @Column(name = "idqualificationLevel")
    @Id
    public int getIdqualificationLevel() {
        return idqualificationLevel;
    }

    public void setIdqualificationLevel(int idqualificationLevel) {
        this.idqualificationLevel = idqualificationLevel;
    }

    @Column(name = "idgroupForm")
    @Id
    public int getIdgroupForm() {
        return idgroupForm;
    }

    public void setIdgroupForm(int idgroupForm) {
        this.idgroupForm = idgroupForm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupsEntityPK that = (GroupsEntityPK) o;

        if (idgroups != that.idgroups) return false;
        if (idchairs != that.idchairs) return false;
        if (idqualificationLevel != that.idqualificationLevel) return false;
        if (idgroupForm != that.idgroupForm) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idgroups;
        result = 31 * result + idchairs;
        result = 31 * result + idqualificationLevel;
        result = 31 * result + idgroupForm;
        return result;
    }
}
