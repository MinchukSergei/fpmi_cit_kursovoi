package dao.impl;

import dao.DAOCheckTable;
import entities.check.CheckTable;
import entities.mark_type.ClMarkTypeFPMI;
import entities.students.ClStudentFPMI;
import entities.subjects.ClSubjectFPMI;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by USER on 17.04.2016.
 */
public class DAOCheckTableImpl implements DAOCheckTable {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<CheckTable> getAll() {
        Session session = null;
        List<CheckTable> check = null;
        try {
            session = sessionFactory.openSession();
            check = session.getNamedQuery(CheckTable.GET_ALL).list();
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

    public ClSubjectFPMI getSubjectById(short subjId) {
        Session session = null;
        ClSubjectFPMI subject = null;
        try {
            session = sessionFactory.openSession();
            subject = session.get(ClSubjectFPMI.class, new Short(subjId));
            session.flush();
        } catch (HibernateException e) {
            throw new HibernateException(e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return subject;
    }

    public ClMarkTypeFPMI getMarkById(short markId) {
        Session session = null;
        ClMarkTypeFPMI mark = null;
        try {
            session = sessionFactory.openSession();
            mark = session.get(ClMarkTypeFPMI.class, new Short(markId));
            session.flush();
        } catch (HibernateException e) {
            throw new HibernateException(e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return mark;
    }
}
