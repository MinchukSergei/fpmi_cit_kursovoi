package dao;

import entities.check.CheckTableCit;
import entities.mark_type.ClMarkTypeCIT;
import entities.mark_type.ClMarkTypeFPMI;
import entities.subjects.ClSubjectCIT;
import entities.subjects.ClSubjectFPMI;

import java.util.List;

/**
 * Created by USER on 21.04.2016.
 */
public interface DAOCheckTableCit {
    List<CheckTableCit> getAll();
}
