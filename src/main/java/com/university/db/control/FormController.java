package com.university.db.control;

import com.university.db.entity.DiplomaformEntity;
import com.university.db.entity.QualificationlevelEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

import static com.university.db.control.dbController.getFactory;

/**
 * Created by maksymmikitiuk on 5/7/17.
 */
public class FormController {
    public FormController() {
    }

    public List<DiplomaformEntity> getAllForm() {
        Session session = getFactory().openSession();
        Transaction tx = null;
        List<DiplomaformEntity> list = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(DiplomaformEntity.class);
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

    public List<DiplomaformEntity> getAllFormByQualification(QualificationlevelEntity qualificationlevel) {
        Session session = getFactory().openSession();
        Transaction tx = null;
        List<DiplomaformEntity> list = null;
        if (qualificationlevel != null) {
            try {
                tx = session.beginTransaction();
                Criteria criteria = session.createCriteria(DiplomaformEntity.class)
                        .add(Restrictions.eq("qualification", qualificationlevel));
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
