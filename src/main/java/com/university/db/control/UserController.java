package com.university.db.control;

import com.university.db.entity.UsersEntity;
import com.university.security.generatePassword;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

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
                    .add(Restrictions.eq("idusers", id)).list();
            return list.get(0);
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return new UsersEntity();
    }

    public boolean passwordAuthentication(String iPassword, String login) {
        generatePassword generatePassword = new generatePassword();
        return generatePassword.comparePassword(generatePassword.generatedSecuredPasswordHash(iPassword, login), getUserPassword(login));
    }

    public static String getUserPassword(String username) {
        Session session = getFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List<String> userEntityList = session.createQuery("select password from UsersEntity where username = ?").setParameter(0, username).list();
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

    public void updateCurrentUser(){
        currentUser = getUserById(currentUser.getIdusers());
    }
}
