package service;

import entities.subjects.ClSubjectFPMI;

import java.util.List;

/**
 * Created by USER on 02.04.2016.
 */
public interface ServiceUniversityObjectFPMI<FPMI> {
    List<FPMI> getAll(String queryName);
}
