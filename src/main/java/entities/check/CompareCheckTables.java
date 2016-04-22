package entities.check;

import dao.DAOCheckTable;
import dao.impl.DAOCheckTableCitImpl;
import dao.impl.DAOCheckTableImpl;
import dao.impl.DAOExportUniversityObjectImpl;
import entities.ExportUniversityObject;
import entities.UniversityObject;
import entities.mark_type.ClMarkTypeFPMI;
import entities.mark_type.ExportClMarkType;
import entities.subjects.ClSubjectFPMI;
import entities.subjects.ExportClSubject;
import org.hibernate.SessionFactory;

/**
 * Created by USER on 21.04.2016.
 */
public class CompareCheckTables {
    private DAOCheckTableImpl daoCheckTable;
    private DAOCheckTableCitImpl daoCheckTableCit;
    private SessionFactory sessionFactoryFPMI;
    private SessionFactory sessionFactoryCIT;
    private DAOExportUniversityObjectImpl<ExportClSubject> exportSubjects;
    private DAOExportUniversityObjectImpl<ExportClMarkType> exportMarks;

    public CompareCheckTables() {
        daoCheckTable = new DAOCheckTableImpl();
        daoCheckTableCit = new DAOCheckTableCitImpl();
        exportMarks = new DAOExportUniversityObjectImpl<>();
        exportSubjects = new DAOExportUniversityObjectImpl<>();
    }

    public SessionFactory getSessionFactoryFPMI() {
        return sessionFactoryFPMI;
    }

    public void setSessionFactoryFPMI(SessionFactory sessionFactoryFPMI) {
        this.sessionFactoryFPMI = sessionFactoryFPMI;
        daoCheckTable.setSessionFactory(sessionFactoryFPMI);
        exportSubjects.setSessionFactory(sessionFactoryFPMI);
        exportMarks.setSessionFactory(sessionFactoryFPMI);
    }

    public SessionFactory getSessionFactoryCIT() {
        return sessionFactoryCIT;
    }

    public void setSessionFactoryCIT(SessionFactory sessionFactoryCIT) {
        this.sessionFactoryCIT = sessionFactoryCIT;
        daoCheckTableCit.setSessionFactory(sessionFactoryCIT);
    }

    public boolean compare(CheckTable checkTable, CheckTableCit checkTableCit) {
        if (!checkTable.getNz().trim().equals(checkTableCit.getNz().trim())) return false;
        if (!checkTable.getFamrus().trim().equals(checkTableCit.getFamrus().trim())) return false;
        if (!checkTable.getImrus().trim().equals(checkTableCit.getImrus().trim())) return false;
        if (checkTable.getCourse() != checkTableCit.getCourse()) return false;
        short idSubjFPMI = checkTable.getSubject();
        short idSubjCIT = checkTableCit.getSubject();
        short idMarkFPMI = checkTable.getMark();
        short idMarkCIT = checkTableCit.getMark();
        if (exportSubjects.getCITObjectByFPMI(ExportClSubject.class, idSubjFPMI).getIdCit() != idSubjCIT) return false;
        if (exportMarks.getCITObjectByFPMI(ExportClMarkType.class, idMarkFPMI).getIdCit() != idMarkCIT) return false;
        return true;
    }
}
