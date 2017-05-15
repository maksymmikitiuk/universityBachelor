package com.university.db.entity;

import javax.persistence.*;

/**
 * Created by Антон Микитюк on 17.11.2016.
 */
@Entity
@Table(name = "marks", schema = "university")
public class MarksEntity {
    private int idmarks;
    private String nationalscale;
    private Integer nationalscalenumber;
    private Integer pointsfrom;
    private Integer pointsto;
    private String ects;

    @Id
    @Column(name = "idmarks")
    public int getIdmarks() {
        return idmarks;
    }

    public void setIdmarks(int idmarks) {
        this.idmarks = idmarks;
    }

    @Basic
    @Column(name = "nationalscale", columnDefinition = "VARCHAR(45) DEFAULT ''")
    public String getNationalscale() {
        return nationalscale;
    }

    public void setNationalscale(String nationalscale) {
        this.nationalscale = nationalscale;
    }

    @Basic
    @Column(name = "nationalscalenumber")
    public Integer getNationalscalenumber() {
        return nationalscalenumber;
    }

    public void setNationalscalenumber(Integer nationalscalenumber) {
        this.nationalscalenumber = nationalscalenumber;
    }

    @Basic
    @Column(name = "pointsfrom")
    public Integer getPointsfrom() {
        return pointsfrom;
    }

    public void setPointsfrom(Integer pointsfrom) {
        this.pointsfrom = pointsfrom;
    }

    @Basic
    @Column(name = "pointsto")
    public Integer getPointsto() {
        return pointsto;
    }

    public void setPointsto(Integer pointsto) {
        this.pointsto = pointsto;
    }

    @Basic
    @Column(name = "ects", columnDefinition = "VARCHAR(2) DEFAULT ''")
    public String getEcts() {
        return ects;
    }

    public void setEcts(String ects) {
        this.ects = ects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MarksEntity that = (MarksEntity) o;

        if (idmarks != that.idmarks) return false;
        if (nationalscale != null ? !nationalscale.equals(that.nationalscale) : that.nationalscale != null)
            return false;
        if (nationalscalenumber != null ? !nationalscalenumber.equals(that.nationalscalenumber) : that.nationalscalenumber != null)
            return false;
        if (pointsfrom != null ? !pointsfrom.equals(that.pointsfrom) : that.pointsfrom != null) return false;
        if (pointsto != null ? !pointsto.equals(that.pointsto) : that.pointsto != null) return false;
        if (ects != null ? !ects.equals(that.ects) : that.ects != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idmarks;
        result = 31 * result + (nationalscale != null ? nationalscale.hashCode() : 0);
        result = 31 * result + (nationalscalenumber != null ? nationalscalenumber.hashCode() : 0);
        result = 31 * result + (pointsfrom != null ? pointsfrom.hashCode() : 0);
        result = 31 * result + (pointsto != null ? pointsto.hashCode() : 0);
        result = 31 * result + (ects != null ? ects.hashCode() : 0);
        return result;
    }
}
