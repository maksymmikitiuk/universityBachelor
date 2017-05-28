package com.university.db.control;

import com.university.db.entity.TeachersEntity;
import javafx.scene.control.ComboBox;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

import static com.university.db.control.DBController.getFactory;

public class TeacherController {

    public TeacherController() {
    }

    public boolean checkTeacher(TeachersEntity teachersEntity) {
        Session session = getFactory().openSession();
        Transaction tx = null;
        List<TeachersEntity> list = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(TeachersEntity.class);
            Conjunction c = Restrictions.conjunction();
            c.add(Restrictions.eq("idchairs", teachersEntity.getIdchairs()));
            c.add(Restrictions.eq("firstName", teachersEntity.getFirstName()));
            c.add(Restrictions.eq("middleName", teachersEntity.getLastName()));
            c.add(Restrictions.eq("lastName", teachersEntity.getMiddleName()));
            criteria.add(c);
            list = criteria.list();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list.isEmpty();
    }

    public List<TeachersEntity> getAllTeacher() {
        Session session = getFactory().openSession();
        Transaction tx = null;
        List<TeachersEntity> list = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(TeachersEntity.class);
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

    public List<TeachersEntity> getTeacherByParameter(String parameter, ComboBox chair) {
        String[] arg = parameter.trim().split(" ");
        Session session = getFactory().openSession();
        Transaction tx = null;
        Criteria criteria = session.createCriteria(TeachersEntity.class);
        List<TeachersEntity> list = new ArrayList<>();

        try {
            tx = session.beginTransaction();

            if (!parameter.isEmpty()) {
                switch (arg.length) {
                    case 1:
                        criteria.add(Restrictions.like("lfmName", '%' + arg[0] + '%', MatchMode.ANYWHERE));
                        break;
                    default:
                        criteria.add(Restrictions.or(Restrictions.and(Restrictions.like("firstName", '%' + arg[0] + '%'),
                                Restrictions.like("lastName", '%' + arg[1] + '%')),
                                Restrictions.and(Restrictions.like("firstName", '%' + arg[1] + '%'),
                                        Restrictions.like("lastName", '%' + arg[0] + '%'))));
                }
            }

            //Фильтр по кафедре
            if (chair.getValue() != null)
                criteria.add(Restrictions.eq("idchairs", chair.getValue()));

        } catch (
                HibernateException e)

        {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally

        {
            list = criteria.list();
            session.close();
        }

        return list;
    }

    public TeachersEntity getTeacherById(int id) {
        Session session = getFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(TeachersEntity.class)
                    .add(Restrictions.eq("idteachers", id));
            if (!criteria.list().isEmpty())
                return (TeachersEntity) criteria.list().get(0);
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return new TeachersEntity();
    }
}
