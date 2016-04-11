package dao;

import entities.faculties.ExportClFacultet;
import entities.subjects.ExportClSubject;
import org.hibernate.HibernateException;

import java.util.List;

/**
 * Created by USER on 03.04.2016.
 */
public interface DAOExportUniversityObject<EXPORT> {

    void updateAll(List<EXPORT> subjectsId) throws HibernateException;
    List<EXPORT> getAll() throws HibernateException;


}
