package service;

import entities.subjects.ClSubjectCIT;

import java.util.List;

/**
 * Created by USER on 02.04.2016.
 */
public interface ServiceUniversityObjectCIT {
    List<ClSubjectCIT> getAll();

    List<ClSubjectCIT> callSubjectExport();


}
