package com.university.db.control;

import com.university.db.entity.DictionaryEntity;
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
public class DictionaryController {
    public DictionaryController() {
    }

    public String getWord(String param){
        Session session = getFactory().openSession();
        Transaction tx = null;
        List<DictionaryEntity> list = null;
            try {
                tx = session.beginTransaction();
                Criteria criteria = session.createCriteria(DictionaryEntity.class)
                        .add(Restrictions.eq("word", param));
                list = criteria.list();
                return (list.isEmpty())?param:list.get(0).getNormal();
            } catch (HibernateException e) {
                if (tx != null) tx.rollback();
                e.printStackTrace();
            } finally {
                session.close();
            }
        return param;
    }
}
