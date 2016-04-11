package service;

import entities.subjects.ExportClSubject;
import org.hibernate.HibernateException;

import java.util.List;

/**
 * Created by USER on 03.04.2016.
 */
public interface ServiceExportUniversityObject {
    void updateAll(List<ExportClSubject> subjectsId) throws HibernateException;
    List<ExportClSubject> getAll() throws HibernateException;
}
