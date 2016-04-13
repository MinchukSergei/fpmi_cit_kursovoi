package service;

import entities.subjects.ExportClSubject;
import org.hibernate.HibernateException;

import java.util.List;

/**
 * Created by USER on 03.04.2016.
 */
public interface ServiceExportUniversityObject<EXPORT> {
    void updateAll(List<EXPORT> subjectsId) throws HibernateException;
    List<EXPORT> getAll(String queryName) throws HibernateException;
}
