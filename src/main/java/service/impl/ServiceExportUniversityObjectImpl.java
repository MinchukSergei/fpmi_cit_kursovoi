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
public class ServiceExportUniversityObjectImpl implements ServiceExportUniversityObject {
    private DAOExportUniversityObjectImpl daoExportClSubject;

    public ServiceExportUniversityObjectImpl() {
        this.daoExportClSubject = new DAOExportUniversityObjectImpl();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        daoExportClSubject.setSessionFactory(sessionFactory);
    }

    @Override
    public void updateAll(List<ExportClSubject> subjectsId) throws HibernateException {
        daoExportClSubject.updateAll(subjectsId);
    }

    @Override
    public List<ExportClSubject> getAll() throws HibernateException {
        return daoExportClSubject.getAll();
    }
}
