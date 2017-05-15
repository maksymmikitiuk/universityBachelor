package com.university.db.control;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

import static com.university.db.control.dbController.getFactory;

/**
 * Created by maksymmikitiuk on 4/8/17.
 */
public class GroupTypeController {
    public GroupTypeController() {
    }

    public List<com.university.db.entity.GrouptypeEntity> getAllGroupType(){
        Session session = getFactory().openSession();
        Transaction tx = null;
        List<com.university.db.entity.GrouptypeEntity> list = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(com.university.db.entity.GrouptypeEntity.class);
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

    public com.university.db.entity.GrouptypeEntity getGroupTypeById(int id) {
        Session session = getFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(com.university.db.entity.GrouptypeEntity.class)
                    .add(Restrictions.eq("idgroupType", id));
            if (!criteria.list().isEmpty())
                return (com.university.db.entity.GrouptypeEntity) criteria.list().get(0);
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return new com.university.db.entity.GrouptypeEntity();
    }
}
