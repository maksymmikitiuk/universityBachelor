package com.university.db.control;

import com.university.db.entity.UsersEntity;
import com.university.security.GeneratePassword;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

import static com.university.db.control.DBController.currentUser;
import static com.university.db.control.DBController.getFactory;

public class UserController {

    public UserController() {
    }

    public UsersEntity getCurrentUserInformation(String username) {
        Session session = getFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List<UsersEntity> list = session.createCriteria(UsersEntity.class)
                    .add(Restrictions.eq("username", username)).list();
            return list.get(0);
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return new UsersEntity();
    }

    public UsersEntity getUserById(int id) {
        Session session = getFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List<UsersEntity> list = session.createCriteria(UsersEntity.class)
                    .add(Restrictions.eq("idusers", id))
                    .add(Restrictions.ne("active", 0)).list();
            return list.get(0);
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return new UsersEntity();
    }

    public List<UsersEntity> getAllUser() {
        Session session = getFactory().openSession();
        Transaction tx = null;
        List<UsersEntity> list = new ArrayList<>();
        try {
            tx = session.beginTransaction();
            list = session.createCriteria(UsersEntity.class).list();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return list;
    }

    public List<UsersEntity> getAllUserByParameter(String parameter) {
        String[] arg = parameter.trim().split(" ");
        Session session = getFactory().openSession();
        Transaction tx = null;
        Criteria criteria = session.createCriteria(UsersEntity.class);
        List<UsersEntity> list = new ArrayList<>();

        try {
            tx = session.beginTransaction();

            //Фильтр по номеру
            if (!parameter.isEmpty()) {
                switch (arg.length) {
                    case 1:
                        criteria.add(Restrictions.or(Restrictions.like("lastName", '%' + arg[0] + '%', MatchMode.ANYWHERE),
                                Restrictions.like("firstName", '%' + arg[0] + '%', MatchMode.ANYWHERE)))
                                .add(Restrictions.ne("active", 0));
                        break;
                    default:
                        criteria.add(Restrictions.or(Restrictions.and(Restrictions.like("firstName", '%' + arg[0] + '%'),
                                Restrictions.like("lastName", '%' + arg[1] + '%')),
                                Restrictions.and(Restrictions.like("firstName", '%' + arg[1] + '%'),
                                        Restrictions.like("lastName", '%' + arg[0] + '%'))))
                                .add(Restrictions.ne("active", 0));
                }
            }
        } catch (
                HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally

        {
            list = criteria.list();
            session.close();
        }

        return list;
    }

    public boolean passwordAuthentication(String iPassword, String login) {
        GeneratePassword generatePassword = new GeneratePassword();
        return generatePassword.comparePassword(generatePassword.generatedSecuredPasswordHash(iPassword, login), getUserPassword(login));
    }

    public static String getUserPassword(String username) {
        Session session = getFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List<String> userEntityList = session.createQuery("select password from UsersEntity where username = ? AND active = 1").setParameter(0, username).list();
            for (String user : userEntityList) {
                tx.commit();
                return user.trim();
            }
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public static boolean validationUser(String username) {
        Session session = getFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List<UsersEntity> list = session.createQuery("from UsersEntity as u where u.username = :username").setParameter("username", username).list();
            for (UsersEntity userEntity : list) {
                tx.commit();
                return false;
            }
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return true;
    }

    public void updateCurrentUser() {
        currentUser = getUserById(currentUser.getIdusers());
    }

    public boolean isActive(String username) {
        Session session = getFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List<UsersEntity> list = session.createQuery("from UsersEntity as u where u.username = :username and u.active = 1").setParameter("username", username).list();
            for (UsersEntity userEntity : list) {
                tx.commit();
                return true;
            }
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }
}
