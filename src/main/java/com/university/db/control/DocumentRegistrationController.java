package com.university.db.control;

import com.university.db.entity.DiplomasubjectsEntity;
import com.university.db.entity.DocumentregistrationEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

import static com.university.db.control.dbController.getFactory;

/**
 * Created by maksymmikitiuk on 5/27/17.
 */
public class DocumentRegistrationController {
    public DocumentRegistrationController() {
    }

    public List<DocumentregistrationEntity> getFile() {
        Session session = getFactory().openSession();
        Transaction tx = null;
        List<DocumentregistrationEntity> list = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(DocumentregistrationEntity.class);
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

    public List<DocumentregistrationEntity> getFileByDiploma(DiplomasubjectsEntity diploma) {
        Session session = getFactory().openSession();
        Transaction tx = null;
        List<DocumentregistrationEntity> list = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(DocumentregistrationEntity.class).add(
                    Restrictions.eq("iddiplomaSubjects", diploma)
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
