package service.impl;

import dao.DAOClSubjectCIT;
import dao.impl.DAOClSubjectCITImpl;
import entities.ClSubjectCIT;
import org.hibernate.SessionFactory;
import org.hibernate.mapping.Collection;
import service.ServiceClSubjectCIT;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by USER on 02.04.2016.
 */
public class ServiceClSubjectCITImpl implements ServiceClSubjectCIT {
    private DAOClSubjectCITImpl daoClSubjectCIT;

    public ServiceClSubjectCITImpl() {
        this.daoClSubjectCIT = new DAOClSubjectCITImpl();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        daoClSubjectCIT.setSessionFactory(sessionFactory);
    }

    @Override
    public List<ClSubjectCIT> getAll() {
        return daoClSubjectCIT.getAll();
    }

    @Override
    public List<ClSubjectCIT> callSubjectExport() {
        return daoClSubjectCIT.callSubjectExports();
    }

    public List<String> getOrderedSubjectName() {
        List<ClSubjectCIT> subjectCITs = daoClSubjectCIT.callSubjectExports();
        List<String> names = new ArrayList<>();
        for (ClSubjectCIT subj: subjectCITs) {
            names.add(subj.getSubjectName().trim());
        }
        Collections.sort(names, String::compareTo);
        return names;
    }

    public List<ClSubjectCIT> getOrderedSubject() {
        List<ClSubjectCIT> subjectCITs = daoClSubjectCIT.callSubjectExports();
        Collections.sort(subjectCITs, ((o1, o2) -> o1.getSubjectName().compareTo(o2.getSubjectName())));
        return subjectCITs;
    }
}
