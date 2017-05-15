package com.university.db.entity;

import javax.persistence.*;

/**
 * Created by maksymmikitiuk on 5/7/17.
 */
@Entity
@Table(name = "dictionary", schema = "university", catalog = "")
public class DictionaryEntity {
    private int id;
    private String word;
    private String normal;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "word")
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Basic
    @Column(name = "normal")
    public String getNormal() {
        return normal;
    }

    public void setNormal(String normal) {
        this.normal = normal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DictionaryEntity that = (DictionaryEntity) o;

        if (id != that.id) return false;
        if (word != null ? !word.equals(that.word) : that.word != null) return false;
        if (normal != null ? !normal.equals(that.normal) : that.normal != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (word != null ? word.hashCode() : 0);
        result = 31 * result + (normal != null ? normal.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "word='" + word + ", normal=" + normal;
    }
}
