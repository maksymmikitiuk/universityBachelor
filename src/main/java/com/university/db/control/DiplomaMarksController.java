package com.university.db.control;

import com.university.db.entity.DiplomamarksEntity;
import com.university.db.entity.DiplomasubjectsEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

import static com.university.db.control.DBController.getFactory;

/**
 * Created by maksymmikitiuk on 5/14/17.
 */
public class DiplomaMarksController {
    public DiplomaMarksController() {
    }

    public DiplomamarksEntity getFinish(DiplomasubjectsEntity diploma) {
        Session session = getFactory().openSession();
        Transaction tx = null;
        List<DiplomamarksEntity> list = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(DiplomamarksEntity.class)
                    .add(Restrictions.eq("id_diploma", diploma))
                    .add(Restrictions.eq("typeOwner.id", 0));
            list = criteria.list();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return (list.size() > 0) ? list.get(0) : new DiplomamarksEntity();
    }

    public List<DiplomamarksEntity> getDiplomaMarksById(DiplomasubjectsEntity diploma) {
        Session session = getFactory().openSession();
        Transaction tx = null;
        List<DiplomamarksEntity> list = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(DiplomamarksEntity.class)
                    .add(Restrictions.eq("id_diploma", diploma))
                    .add(Restrictions.ne("typeOwner.id", 0));
            list = criteria.list();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

    public Boolean isFind(int id) {
        Session session = getFactory().openSession();
        Transaction tx = null;
        List<DiplomamarksEntity> list = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(DiplomamarksEntity.class)
                    .add(Restrictions.eq("iddiplomaMarks", id));
            list = criteria.list();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return !list.isEmpty();
    }
}
