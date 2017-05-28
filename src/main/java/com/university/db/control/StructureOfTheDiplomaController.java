package com.university.db.control;

import com.university.db.entity.DiplomatypeEntity;
import com.university.db.entity.DocumenttypeEntity;
import com.university.db.entity.StructureofthediplomaEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

import static com.university.db.control.DBController.getFactory;

/**
 * Created by maksymmikitiuk on 5/27/17.
 */
public class StructureOfTheDiplomaController {
    public StructureOfTheDiplomaController() {
    }

    public List<DocumenttypeEntity> getStructure(DiplomatypeEntity type) {
        Session session = getFactory().openSession();
        Transaction tx = null;
        List<DocumenttypeEntity> list = new ArrayList<>();
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(StructureofthediplomaEntity.class)
                    .add(Restrictions.eq("iddiplomaType", type)).setProjection(Projections.property("iddocumentType"));
            list = criteria.list();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }
}
