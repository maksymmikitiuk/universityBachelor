package com.university.db.control;

import com.university.db.entity.GroupsEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

import static com.university.db.control.DBController.getFactory;

/**
 * Created by maksymmikitiuk on 3/28/17.
 */
public class GroupController {
    public GroupController() {
    }

    public List<GroupsEntity> getAllGroup(){
        Session session = getFactory().openSession();
        Transaction tx = null;
        List<GroupsEntity> list = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(GroupsEntity.class);
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

    public GroupsEntity getGroupById(int id) {
        Session session = getFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(GroupsEntity.class)
                    .add(Restrictions.eq("idgroups", id));
            if (!criteria.list().isEmpty())
                return (GroupsEntity) criteria.list().get(0);
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return new GroupsEntity();
    }
}
