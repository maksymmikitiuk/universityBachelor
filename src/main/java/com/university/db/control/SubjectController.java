package com.university.db.control;

import com.university.db.entity.DiplomasubjectsEntity;
import com.university.db.entity.StudentsEntity;
import javafx.scene.control.ComboBox;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

import static com.university.db.control.dbController.getFactory;

/**
 * Created by maksymmikitiuk on 4/23/17.
 */
public class SubjectController {
    public SubjectController() {
    }

    public List<DiplomasubjectsEntity> getAllSubject() {
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

    public List<DiplomasubjectsEntity> getSubjectByParameter(String parameter) {
        Session session = getFactory().openSession();
        Transaction tx = null;
        List<DiplomasubjectsEntity> list = new ArrayList<>();

        try {
            Criteria criteria = session.createCriteria(DiplomasubjectsEntity.class);
            if (!parameter.isEmpty()) {
                criteria.add(Restrictions.like("subject", '%' + parameter + '%', MatchMode.ANYWHERE));
                list = criteria.list();
            }
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            return list;
        }
    }
}
