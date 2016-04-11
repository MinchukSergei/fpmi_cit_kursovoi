package dao.impl;

import dao.DAOUniversityObjectCIT;
import entities.UniversityObject;
import entities.UniversityObjectProcedure;
import entities.lessons.ClLessonCIT;
import entities.subjects.ClSubjectCIT;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by USER on 01.04.2016.
 */
public class DAOUniversityObjectCITImpl<CIT> implements DAOUniversityObjectCIT<CIT> {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<CIT> getAll() {
        Session session = null;
        List<CIT> list = null;

        try {
            session = sessionFactory.openSession();
            Query query = session.getNamedQuery(ClLessonCIT.GET_ALL_LESSON_CIT);
            list = query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null)
                session.close();
        }
        return list;
    }

    public List<CIT> callSubjectExports() {
        Session session = null;
        List<CIT> list = null;
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
