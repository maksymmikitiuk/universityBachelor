package com.university.db.control;

import com.university.db.entity.UserroleEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

import static com.university.db.control.DBController.getFactory;

/**
 * Created by maksymmikitiuk on 5/31/17.
 */
public class RoleController {
    public RoleController() {
    }

    public UserroleEntity getUser() {
        Session session = getFactory().openSession();
        Transaction tx = null;
        List<UserroleEntity> list = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(UserroleEntity.class)
                    .add(Restrictions.eq("role", "USER"));
            list = criteria.list();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list.get(0);
    }

    public List<UserroleEntity> getAllRole() {
        Session session = getFactory().openSession();
        Transaction tx = null;
        List<UserroleEntity> list = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(UserroleEntity.class);
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
}
