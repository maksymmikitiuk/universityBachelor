package com.university.db.control;

import com.sun.org.apache.xpath.internal.operations.Or;
import com.university.db.entity.MarksEntity;
import com.university.db.entity.QualificationlevelEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

import static com.university.db.control.dbController.getFactory;

/**
 * Created by maksymmikitiuk on 5/14/17.
 */
public class MarksController {
    public MarksController() {
    }

    public List<MarksEntity> getAllMarks() {
        Session session = getFactory().openSession();
        Transaction tx = null;
        List<MarksEntity> list = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(MarksEntity.class);
            list = criteria.list();
            return list;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

    public MarksEntity getMarksByPoints(int point) {
        Session session = getFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(MarksEntity.class)
                    .add(Restrictions.ge("pointsto", point))
                    .add(Restrictions.le("pointsfrom", point));
            if (!criteria.list().isEmpty())
                return (MarksEntity) criteria.list().get(0);
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return new MarksEntity();
    }
}
