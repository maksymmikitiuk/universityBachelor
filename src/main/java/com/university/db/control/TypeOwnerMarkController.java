package com.university.db.control;

import com.university.db.entity.TypeOwnerMarkEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

import static com.university.db.control.dbController.getFactory;

/**
 * Created by maksymmikitiuk on 5/25/17.
 */
public class TypeOwnerMarkController {
    public TypeOwnerMarkController() {
    }

    public List<TypeOwnerMarkEntity> getAllType() {
        Session session = getFactory().openSession();
        Transaction tx = null;
        List<TypeOwnerMarkEntity> list = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(TypeOwnerMarkEntity.class).add(
                    Restrictions.ne("type", "FINISH")
            );
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
