package service;

import dao.DAOClSubjectCIT;
import entities.ClSubjectCIT;

import java.util.List;

/**
 * Created by USER on 02.04.2016.
 */
public interface ServiceClSubjectCIT {
    List<ClSubjectCIT> getAll();

    List<ClSubjectCIT> callSubjectExport();


}
