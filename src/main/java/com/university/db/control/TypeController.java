package com.university.db.control;

import com.university.db.entity.DiplomaformEntity;
import com.university.db.entity.DiplomatypeEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

import static com.university.db.control.DBController.getFactory;

/**
 * Created by maksymmikitiuk on 5/7/17.
 */
public class TypeController {
    public TypeController() {
    }

    public List<DiplomatypeEntity> getAllType() {
        Session session = getFactory().openSession();
        Transaction tx = null;
        List<DiplomatypeEntity> list = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(DiplomatypeEntity.class);
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

    public List<DiplomatypeEntity> getAllTypeByForm(DiplomaformEntity form) {
        Session session = getFactory().openSession();
        Transaction tx = null;
        List<DiplomatypeEntity> list = null;
        if (form != null) {
            try {
                tx = session.beginTransaction();
                Criteria criteria = session.createCriteria(DiplomatypeEntity.class)
                        .add(Restrictions.eq("form", form));
                list = criteria.list();
                return list;
            } catch (HibernateException e) {
                if (tx != null) tx.rollback();
                e.printStackTrace();
            } finally {
                session.close();
            }
        }
        return list;
    }
}
