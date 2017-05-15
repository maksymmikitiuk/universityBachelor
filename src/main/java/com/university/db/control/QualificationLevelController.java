package com.university.db.control;

import com.university.db.entity.QualificationlevelEntity;
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
public class QualificationLevelController {
    public QualificationLevelController() {
    }

    public List<QualificationlevelEntity> getAllQualificationLevel() {
        Session session = getFactory().openSession();
        Transaction tx = null;
        List<QualificationlevelEntity> list = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(QualificationlevelEntity.class);
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

    public QualificationlevelEntity getChairById(int id) {
        Session session = getFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(QualificationlevelEntity.class)
                    .add(Restrictions.eq("idqualificationLevel", id));
            if (!criteria.list().isEmpty())
                return (QualificationlevelEntity) criteria.list().get(0);
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return new QualificationlevelEntity();
    }
}
