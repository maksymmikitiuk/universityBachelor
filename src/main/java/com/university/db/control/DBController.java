package com.university.db.control;

import com.university.db.entity.UsersEntity;
import javafx.stage.Stage;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class DBController {

    private static SessionFactory factory;
    public static Stage mainStage;
    public static UsersEntity currentUser;

    public DBController() {
    }

    public static SessionFactory getFactory() {
        return factory;
    }

    public static Stage getStage(){
        return mainStage;
    }

    public static void setStage(Stage s){
        mainStage = s;
    }

    public static Stage getMainStage() {
        return mainStage;
    }

    public static void getSession() {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public boolean create(Object object) {
        Session session = getFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            session.save(object);
            tx.commit();

            return true;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    public boolean delete(Object object) {
        Session session = getFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(object);
            tx.commit();
            return true;

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

        public boolean update(Object object){
        Session session = getFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            session.update(object);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }

    }

}
