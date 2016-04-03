package dao;

import entities.ClSubjectCIT;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by USER on 01.04.2016.
 */
public interface DAOClSubjectCIT {

    List<ClSubjectCIT> getAll();

    List<ClSubjectCIT> callSubjectExports();
}
