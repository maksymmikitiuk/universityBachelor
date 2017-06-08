package com.university.db.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Антон Микитюк on 17.11.2016.
 */
@Entity
@Table(name = "groups", schema = "university")
public class GroupsEntity {
    @Id
    @Column(name = "idgroups")
    private int idgroups;
    private String abbreviation;
    private String name;
    private Date yearofentering;


//    @OneToMany(mappedBy = "idgroups")
//    private List<StudentsEntity> students;

    public int getIdgroups() {
        return idgroups;
    }

    public void setIdgroups(int idgroups) {
        this.idgroups = idgroups;
    }

    @Basic
    @Column(name = "abbreviation")
    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "yearofentering")
    public Date getYearofentering() {
        return yearofentering;
    }

    public void setYearofentering(Date yearofentering) {
        this.yearofentering = yearofentering;
    }

    @ManyToOne
    @JoinColumn(name = "idchairs")
    private ChairsEntity idchairs;

    public ChairsEntity getIdchairs() {
        return idchairs;
    }

    public void setIdchairs(ChairsEntity idchairs) {
        this.idchairs = idchairs;
    }

    @ManyToOne
    @JoinColumn(name = "idqualificationLevel")
    private QualificationlevelEntity idqualificationLevel;

    public QualificationlevelEntity getIdqualificationLevel() {
        return idqualificationLevel;
    }

    public void setIdqualificationLevel(QualificationlevelEntity idqualificationLevel) {
        this.idqualificationLevel = idqualificationLevel;
    }

    @ManyToOne
    @JoinColumn(name = "idgroupType")
    private GrouptypeEntity idgroupType;

    public GrouptypeEntity getIdgroupType() {
        return idgroupType;
    }

    public void setIdgroupType(GrouptypeEntity idgroupType) {
        this.idgroupType = idgroupType;
    }

    @ManyToOne
    @JoinColumn(name = "idgroupForm")
    private GroupformEntity idgroupForm;

    public GroupformEntity getIdgroupForm() {
        return idgroupForm;
    }

    public void setIdgroupForm(GroupformEntity idgroupForm) {
        this.idgroupForm = idgroupForm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupsEntity that = (GroupsEntity) o;

        if (idgroups != that.idgroups) return false;
        if (idchairs != that.idchairs) return false;
        if (idqualificationLevel != that.idqualificationLevel) return false;
        if (abbreviation != null ? !abbreviation.equals(that.abbreviation) : that.abbreviation != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (yearofentering != null ? !yearofentering.equals(that.yearofentering) : that.yearofentering != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idgroups;
        result = 31 * result + (abbreviation != null ? abbreviation.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (yearofentering != null ? yearofentering.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return abbreviation;
    }
}
