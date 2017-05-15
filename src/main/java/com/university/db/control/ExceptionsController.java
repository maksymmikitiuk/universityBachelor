package com.university.db.control;

import com.university.db.entity.ЕxceptionsEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

import static com.university.db.control.dbController.getFactory;

/**
 * Created by maksymmikitiuk on 5/1/17.
 */
public class ExceptionsController {
    public ExceptionsController() {
    }

    public List<ЕxceptionsEntity> getAllЕxception() {
        Session session = getFactory().openSession();
        Transaction tx = null;
        List<ЕxceptionsEntity> list = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(ЕxceptionsEntity.class);
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

    public boolean getЕxception(String value) {
        Session session = getFactory().openSession();
        Transaction tx = null;
        List<ЕxceptionsEntity> list = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(ЕxceptionsEntity.class);
            criteria.add(Restrictions.eq("value", value));
            list = criteria.list();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list.isEmpty();
    }
}
