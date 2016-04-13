package service.impl;

import dao.impl.DAOExportUniversityObjectImpl;
import entities.subjects.ExportClSubject;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import service.ServiceExportUniversityObject;

import java.util.List;

/**
 * Created by USER on 03.04.2016.
 */
public class ServiceExportUniversityObjectImpl<EXPORT> implements ServiceExportUniversityObject<EXPORT> {
    private DAOExportUniversityObjectImpl daoExportClSubject;

    public ServiceExportUniversityObjectImpl() {
        this.daoExportClSubject = new DAOExportUniversityObjectImpl();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        daoExportClSubject.setSessionFactory(sessionFactory);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void updateAll(List<EXPORT> subjectsId) throws HibernateException {
        daoExportClSubject.updateAll(subjectsId);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<EXPORT> getAll(String queryName) throws HibernateException {
        return daoExportClSubject.getAll(queryName);
    }
}
