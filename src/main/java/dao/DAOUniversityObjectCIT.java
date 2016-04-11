package dao;

import entities.subjects.ClSubjectCIT;

import java.util.List;

/**
 * Created by USER on 01.04.2016.
 */
public interface DAOUniversityObjectCIT<CIT> {

    List<CIT> getAll();
    List<CIT> callSubjectExports();
}
