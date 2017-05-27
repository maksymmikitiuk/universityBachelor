package com.university.db.control;

import com.university.db.entity.DiplomasubjectsEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

import static com.university.db.control.DBController.getFactory;

public class DiplomaSubjectController {
    public DiplomaSubjectController() {
    }

    public DiplomasubjectsEntity getDiplomaSubjectById(int id) {
        Session session = getFactory().openSession();
        Transaction tx = null;
        List<DiplomasubjectsEntity> list = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(DiplomasubjectsEntity.class)
                    .add(Restrictions.eq("type_id", id));
            list = criteria.list();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return (list.isEmpty()) ? new DiplomasubjectsEntity() : list.get(0);
    }

    public List<DiplomasubjectsEntity> getAllDiplomaSubject() {
        Session session = getFactory().openSession();
        Transaction tx = null;
        List<DiplomasubjectsEntity> list = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(DiplomasubjectsEntity.class);
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
