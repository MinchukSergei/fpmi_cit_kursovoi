package dao.impl;

import dao.DAOCheckTableCit;
import entities.check.CheckTable;
import entities.check.CheckTableCit;
import entities.mark_type.ClMarkTypeCIT;
import entities.subjects.ClSubjectCIT;
import entities.subjects.ClSubjectFPMI;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by USER on 21.04.2016.
 */
public class DAOCheckTableCitImpl implements DAOCheckTableCit {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<CheckTableCit> getAll() {
        Session session = null;
        List<CheckTableCit> check = null;
        try {
            session = sessionFactory.openSession();
            check = session.getNamedQuery(CheckTableCit.RETRY_GET_ALL_PROC).list();
            session.flush();
        } catch (HibernateException e) {
            throw new HibernateException(e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return check;
    }


}
