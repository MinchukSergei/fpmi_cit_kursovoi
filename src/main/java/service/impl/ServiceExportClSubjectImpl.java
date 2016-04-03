package service.impl;

import dao.DAOExportClSubject;
import dao.impl.DAOClSubjectFPMIImpl;
import dao.impl.DAOExportClSubjectImpl;
import entities.ExportClSubject;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import service.ServiceExportClSubject;

import java.util.List;

/**
 * Created by USER on 03.04.2016.
 */
public class ServiceExportClSubjectImpl implements ServiceExportClSubject {
    private DAOExportClSubjectImpl daoExportClSubject;

    public ServiceExportClSubjectImpl() {
        this.daoExportClSubject = new DAOExportClSubjectImpl();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        daoExportClSubject.setSessionFactory(sessionFactory);
    }

    @Override
    public void updateAll(List<ExportClSubject> subjectsId) throws HibernateException {
        daoExportClSubject.updateAll(subjectsId);
    }
}
