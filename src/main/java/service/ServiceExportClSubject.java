package service;

import entities.ExportClSubject;
import org.hibernate.HibernateException;

import java.util.List;

/**
 * Created by USER on 03.04.2016.
 */
public interface ServiceExportClSubject {
    void updateAll(List<ExportClSubject> subjectsId) throws HibernateException;
}
