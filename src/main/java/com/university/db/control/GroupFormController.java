package com.university.db.control;

import com.university.db.entity.GroupformEntity;
import com.university.db.entity.GrouptypeEntity;
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
public class GroupFormController {
    public GroupFormController() {
    }

    public List<com.university.db.entity.GroupformEntity> getAllGroupForm() {
        Session session = getFactory().openSession();
        Transaction tx = null;
        List<com.university.db.entity.GroupformEntity> list = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(com.university.db.entity.GroupformEntity.class);
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

    public com.university.db.entity.GroupformEntity getGroupFormById(int id) {
        Session session = getFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(com.university.db.entity.GroupformEntity.class)
                    .add(Restrictions.eq("idgroupForm", id));
            if (!criteria.list().isEmpty())
                return (com.university.db.entity.GroupformEntity) criteria.list().get(0);
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return new com.university.db.entity.GroupformEntity();
    }

    public GrouptypeEntity getGroupType(GroupformEntity groupForm) {
        Session session = getFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            int i = 1;
            List list = session.createQuery("FROM GrouptypeEntity where idgroupType  IN(SELECT " +
                    "q.groupTypeIdgroupType from GroupformHasGrouptypeEntity as  q where q.groupFormIdgroupForm = :p)")
                    .setParameter("p", groupForm.getIdgroupForm()).list();
            //return list;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return new GrouptypeEntity();
    }
}
