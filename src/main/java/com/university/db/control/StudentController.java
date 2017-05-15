package com.university.db.control;

import com.university.db.entity.StudentsEntity;
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

import static com.university.db.control.dbController.getFactory;

public class StudentController {

    public StudentController() {
    }

    public boolean checkStudent(StudentsEntity studentsEntity) {
        Session session = getFactory().openSession();
        Transaction tx = null;
        List<StudentsEntity> list = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(StudentsEntity.class);
            Conjunction c = Restrictions.conjunction();
            c.add(Restrictions.eq("studentidcard", studentsEntity.getStudentidcard()));
            c.add(Restrictions.eq("firstName", studentsEntity.getFirstName()));
            c.add(Restrictions.eq("middleName", studentsEntity.getMiddleName()));
            c.add(Restrictions.eq("lastName", studentsEntity.getLastName()));
            c.add(Restrictions.eq("idgroups", studentsEntity.getIdgroups()));
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

    public List<StudentsEntity> getAllStudent() {
        Session session = getFactory().openSession();
        Transaction tx = null;
        List<StudentsEntity> list = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(StudentsEntity.class);
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

    public List<StudentsEntity> getStudentByParameter(String parameter, ComboBox group) {
        String[] arg = parameter.trim().split(" ");
        Session session = getFactory().openSession();
        Transaction tx = null;
        Criteria criteria = session.createCriteria(StudentsEntity.class);
        List<StudentsEntity> list = new ArrayList<>();

        try {
            tx = session.beginTransaction();

            //Фильтр по номеру
            if (!parameter.isEmpty()) {
                if (arg[0].matches("^.*[0-9].*$"))
                    criteria.add(Restrictions.like("studentidcard", '%' + arg[0] + '%', MatchMode.ANYWHERE));
                else {
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
            }

            //Фильтр по группе
            if (group.getValue() != null)
                criteria.add(Restrictions.eq("idgroups", group.getValue()));

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

    public StudentsEntity getStudentById(int id) {
        Session session = getFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(StudentsEntity.class)
                    .add(Restrictions.eq("idstudents", id));
            if (!criteria.list().isEmpty())
                return (StudentsEntity) criteria.list().get(0);
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return new StudentsEntity();
    }
}
