package service;

import entities.subjects.ClSubjectCIT;

import java.util.List;

/**
 * Created by USER on 02.04.2016.
 */
public interface ServiceUniversityObjectCIT<CIT> {
    List<CIT> getAll(String queryName);
    List<CIT> callProcedureExport(String queryName);
}
