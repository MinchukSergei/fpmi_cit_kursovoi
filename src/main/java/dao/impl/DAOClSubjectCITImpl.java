package dao.impl;

import dao.DAOClSubjectCIT;
import entities.ClSubjectCIT;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by USER on 01.04.2016.
 */
public class DAOClSubjectCITImpl implements DAOClSubjectCIT {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<ClSubjectCIT> getAll() {
        Session session = null;
        List<ClSubjectCIT> list = null;

        try {
            session = sessionFactory.openSession();
            Query query = session.getNamedQuery(ClSubjectCIT.GET_ALL_SUBJECT_CIT);
            list = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null)
                session.close();
        }
        return list;
    }

    public List<ClSubjectCIT> callSubjectExports() {
        Session session = null;
        List<ClSubjectCIT> list = null;
        try {
            session = sessionFactory.openSession();
            Query query = session.getNamedQuery(ClSubjectCIT.GET_ALL_SUBJECT_CIT_PROC);
            list = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null)
                session.close();
        }
        return list;
    }
}
