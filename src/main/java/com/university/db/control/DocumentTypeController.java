package com.university.db.control;

import com.university.db.entity.DocumenttypeEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

import static com.university.db.control.DBController.getFactory;

/**
 * Created by maksymmikitiuk on 6/10/17.
 */
public class DocumentTypeController {
    public DocumentTypeController() {
    }

    public DocumenttypeEntity getDocumentByID(int id){
        Session session = getFactory().openSession();
        Transaction tx = null;
        List<DocumenttypeEntity> list = null;
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(DocumenttypeEntity.class)
                    .add(Restrictions.eq("iddocumentType", id));
            list = criteria.list();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return (list.size() > 0)?list.get(0):null;
    }
}
