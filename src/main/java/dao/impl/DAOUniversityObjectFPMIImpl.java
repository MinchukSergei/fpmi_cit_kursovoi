package dao.impl;

import dao.DAOUniversityObjectFPMI;
import entities.subjects.ClSubjectFPMI;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by USER on 01.04.2016.
 */
public class DAOUniversityObjectFPMIImpl<FPMI> implements DAOUniversityObjectFPMI<FPMI> {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<FPMI> getAll(String queryName) {
        Session session = null;
        List<FPMI> list = null;

        try {
            session = sessionFactory.openSession();
            Query query = session.getNamedQuery(queryName);
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
