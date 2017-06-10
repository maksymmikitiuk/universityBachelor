package com.university.db.entity;

import javax.persistence.*;

/**
 * Created by maksymmikitiuk on 5/31/17.
 */
@Entity
@Table(name = "settings", schema = "university")
public class SettingsEntity {
    private String path;
    private int plagiat;

    @Basic
    @Column(name = "plagiat")
    public int getPlagiat() {
        return plagiat;
    }

    public void setPlagiat(int plagiat) {
        this.plagiat = plagiat;
    }

    @Basic
    @Column(name = "PATH")
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

        SettingsEntity that = (SettingsEntity) o;

        if (path != null ? !path.equals(that.path) : that.path != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return path != null ? path.hashCode() : 0;
    }

    private String id;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
