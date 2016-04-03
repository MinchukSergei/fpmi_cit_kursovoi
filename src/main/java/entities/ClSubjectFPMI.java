package entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by USER on 31.03.2016.
 */

@Entity
@Table(name = "cl_subject")
@NamedQueries({
        @NamedQuery(name = ClSubjectFPMI.GET_ALL_SUBJECT_FPMI, query = ClSubjectFPMI.GET_ALL_SUBJECT_FPMI_QUERY)

})
public class ClSubjectFPMI implements Serializable {

    public static final String GET_ALL_SUBJECT_FPMI = "ClSubjectFMPI.getAll";
    static final String GET_ALL_SUBJECT_FPMI_QUERY = "SELECT c FROM ClSubjectFPMI c";

    @Id
    @Column(name = "id_subject")
    private short idSubject;

    @Column(name = "subject_name", columnDefinition = "char")
    private String subjectName;

    public short getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(short idSubject) {
        this.idSubject = idSubject;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClSubjectFPMI that = (ClSubjectFPMI) o;

        if (idSubject != that.idSubject) return false;
        return subjectName != null ? subjectName.equals(that.subjectName) : that.subjectName == null;

    }

    @Override
    public int hashCode() {
        int result = (int) idSubject;
        result = 31 * result + (subjectName != null ? subjectName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return subjectName;
    }

    public String toStr() {
        return subjectName + String.valueOf(idSubject);
    }
}
