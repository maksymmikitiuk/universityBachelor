package com.university.db.control;

import com.university.db.entity.SettingsEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import static com.university.db.control.DBController.getFactory;

/**
 * Created by maksymmikitiuk on 5/31/17.
 */
public class SettingsController {
    public SettingsController() {
    }

    public SettingsEntity getSettings() {
        Session session = getFactory().openSession();
        Transaction tx = null;
        List<SettingsEntity> list = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(SettingsEntity.class);
            list = criteria.list();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list.get(0);
    }
}
