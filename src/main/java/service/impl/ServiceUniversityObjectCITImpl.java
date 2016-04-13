package service.impl;

import dao.impl.DAOUniversityObjectCITImpl;
import entities.subjects.ClSubjectCIT;
import org.hibernate.SessionFactory;
import service.ServiceUniversityObjectCIT;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by USER on 02.04.2016.
 */
public class ServiceUniversityObjectCITImpl<CIT> implements ServiceUniversityObjectCIT<CIT> {
    private DAOUniversityObjectCITImpl daoClSubjectCIT;

    public ServiceUniversityObjectCITImpl() {
        this.daoClSubjectCIT = new DAOUniversityObjectCITImpl();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        daoClSubjectCIT.setSessionFactory(sessionFactory);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<CIT> getAll(String queryName) {
        return daoClSubjectCIT.getAll(queryName);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<CIT> callProcedureExport(String queryName) {
        return daoClSubjectCIT.callProcedureExports(queryName);
    }

//    public List<String> getOrderedSubjectName() {
//        List<ClSubjectCIT> subjectCITs = daoClSubjectCIT.callSubjectExports();
//        List<String> names = new ArrayList<>();
//        for (ClSubjectCIT subj: subjectCITs) {
//            names.add(subj.getSubjectName().trim());
//        }
//        Collections.sort(names, String::compareTo);
//        return names;
//    }
//
//    public List<ClSubjectCIT> getOrderedSubject() {
//        List<ClSubjectCIT> subjectCITs = daoClSubjectCIT.callSubjectExports();
//        Collections.sort(subjectCITs, ((o1, o2) -> o1.getSubjectName().compareTo(o2.getSubjectName())));
//        return subjectCITs;
//    }
}
