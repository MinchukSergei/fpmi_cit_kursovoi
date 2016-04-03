package dao.impl;

import dao.DAOExportClSubject;
import entities.ExportClSubject;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by USER on 03.04.2016.
 */
public class DAOExportClSubjectImpl implements DAOExportClSubject {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void updateAll(List<ExportClSubject> subjectsId) throws HibernateException {
        Session session = null;

        try {
            session = sessionFactory.openSession();
            for (ExportClSubject e : subjectsId) {
                session.merge(e);
            }
            session.flush();
        } catch (HibernateException e) {
            throw new HibernateException(e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
