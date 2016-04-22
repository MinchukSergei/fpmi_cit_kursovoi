package dao;

import entities.check.CheckTable;
import entities.mark_type.ClMarkTypeFPMI;
import entities.subjects.ClSubjectFPMI;

import java.util.List;

/**
 * Created by USER on 17.04.2016.
 */
public interface DAOCheckTable {
    List<CheckTable> getAll();
    void retrySetMark(int checkTableId);
    ClMarkTypeFPMI getMarkById(short markId);
    ClSubjectFPMI getSubjectById(short subjId);
}
