package com.university.db.control;

import com.university.db.entity.ChairsEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

import static com.university.db.control.DBController.getFactory;

/**
 * Created by maksymmikitiuk on 4/8/17.
 */
public class ChairController {
    public ChairController() {
    }

    public List<ChairsEntity> getAllChair(){
        Session session = getFactory().openSession();
        Transaction tx = null;
        List<ChairsEntity> list = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(ChairsEntity.class);
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

    public ChairsEntity getChairById(int id) {
        Session session = getFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(ChairsEntity.class)
                    .add(Restrictions.eq("idchairs", id));
            if (!criteria.list().isEmpty())
                return (ChairsEntity) criteria.list().get(0);
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return new ChairsEntity();
    }
}
