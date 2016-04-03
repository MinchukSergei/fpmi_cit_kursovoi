package service.impl;

import dao.impl.DAOClSubjectCITImpl;
import dao.impl.DAOClSubjectFPMIImpl;
import entities.ClSubjectCIT;
import entities.ClSubjectFPMI;
import org.hibernate.SessionFactory;
import org.hibernate.mapping.Collection;
import service.ServiceClSubjectFPMI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by USER on 02.04.2016.
 */
public class ServiceClSubjectFPMIImpl implements ServiceClSubjectFPMI {

    private DAOClSubjectFPMIImpl daoClSubjectFPMI;

    public ServiceClSubjectFPMIImpl() {
        this.daoClSubjectFPMI = new DAOClSubjectFPMIImpl();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        daoClSubjectFPMI.setSessionFactory(sessionFactory);
    }

    @Override
    public List<ClSubjectFPMI> getAll() {
        return daoClSubjectFPMI.getAll();
    }

    public List<String> getOrderedSubjectNames() {
        List<ClSubjectFPMI> subjectCITs = daoClSubjectFPMI.getAll();
        List<String> names = new ArrayList<>();
        for (ClSubjectFPMI subj : subjectCITs) {
            names.add(subj.getSubjectName().trim());
        }
        Collections.sort(names, (String::compareTo));
        return names;
    }

    public List<ClSubjectFPMI> getOrderedSubject() {
        List<ClSubjectFPMI> subjectFPMIs = daoClSubjectFPMI.getAll();
        Collections.sort(subjectFPMIs, ((o1, o2) -> o1.getSubjectName().compareTo(o2.getSubjectName())));
        return subjectFPMIs;
    }
}
