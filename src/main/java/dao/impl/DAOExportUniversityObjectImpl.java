package dao.impl;

import dao.DAOExportUniversityObject;
import entities.subjects.ExportClSubject;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by USER on 03.04.2016.
 */
public class DAOExportUniversityObjectImpl<EXPORT> implements DAOExportUniversityObject<EXPORT> {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void updateAll(List<EXPORT> subjectsId) throws HibernateException {
        Session session = null;

        try {
            session = sessionFactory.openSession();
            for (EXPORT e : subjectsId) {
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

    @Override
    @SuppressWarnings("unchecked")
    public List<EXPORT> getAll(String queryName) throws HibernateException {
        Session session = null;
        List<EXPORT> subjectsIdList = null;
        try {
            session = sessionFactory.openSession();

            Query query = session.getNamedQuery(queryName);
            subjectsIdList = query.list();
        } catch (HibernateException e) {
            throw new HibernateException(e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return subjectsIdList;
    }
}
