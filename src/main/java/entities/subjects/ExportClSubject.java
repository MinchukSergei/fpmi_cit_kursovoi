package entities.subjects;

import entities.ExportUniversityObject;
import entities.UniversityObject;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by USER on 03.04.2016.
 */
@Entity
@Table(name = "export_cl_subject")
@NamedQueries({
        @NamedQuery(name = ExportClSubject.GET_ALL_ID, query = ExportClSubject.GET_ALL_ID_QUERY)
})
public class ExportClSubject implements Serializable, UniversityObject, ExportUniversityObject {
    public static final String GET_ALL_ID = "ExportClSubject.getAll";
    static final String GET_ALL_ID_QUERY = "SELECT e FROM ExportClSubject e";

    @Id
    @Column(name = "id_subject")
    private short idSubject;

    @Column(name = "id_subject_cit")
    private short idSubjectCit;

    public short getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(short idSubject) {
        this.idSubject = idSubject;
    }

    public short getIdSubjectCit() {
        return idSubjectCit;
    }

    public void setIdSubjectCit(short idSubjectCit) {
        this.idSubjectCit = idSubjectCit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExportClSubject that = (ExportClSubject) o;

        if (idSubject != that.idSubject) return false;
        return idSubjectCit == that.idSubjectCit;
    }

    @Override
    public int hashCode() {
        int result = (int) idSubject;
        result = 31 * result + (int) idSubjectCit;
        return result;
    }

    @Override
    public String toString() {
        return "ExportClSubject{" +
                "idSubject=" + idSubject +
                ", idSubjectCit=" + idSubjectCit +
                '}';
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public short getId() {
        return getIdSubject();
    }

    @Override
    public void setId(short id) {
        this.idSubject = id;
    }

    @Override
    public void setIdCit(short idCit) {
        this.idSubjectCit = idCit;
    }

    @Override
    public short getIdCit() {
        return idSubjectCit;
    }
}
