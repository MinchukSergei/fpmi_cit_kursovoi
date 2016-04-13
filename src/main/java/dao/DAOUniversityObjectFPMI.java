package dao;

import entities.subjects.ClSubjectFPMI;

import java.util.List;

/**
 * Created by USER on 01.04.2016.
 */
public interface DAOUniversityObjectFPMI<FPMI> {

    List<FPMI> getAll(String queryName);
}
