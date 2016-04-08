package entities;




import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by USER on 31.03.2016.
 */


@Entity
@Table(name = "cl_subject_cit")
@NamedQueries({
        @NamedQuery(name = ClSubjectCIT.GET_ALL_SUBJECT_CIT, query = ClSubjectCIT.GET_ALL_SUBJECT_CIT_QUERY)

})
@NamedNativeQueries({
        @NamedNativeQuery(name = ClSubjectCIT.GET_ALL_SUBJECT_CIT_PROC,
                query = ClSubjectCIT.GET_ALL_SUBJECT_CIT_QUERY_PROC,
                resultClass = ClSubjectCIT.class)
})
public class ClSubjectCIT implements Serializable, UniversityObject {

    public ClSubjectCIT() {
        this.setIdSubject((short) -1);
        this.setSubjectName(" ");
    }

    public static final String GET_ALL_SUBJECT_CIT = "ClSubjectCIT.getAll";
    static final String GET_ALL_SUBJECT_CIT_QUERY = "SELECT c FROM ClSubjectCIT c";

    public static final String GET_ALL_SUBJECT_CIT_PROC = "ClSubjectCIT.subjectExport";
    static final String GET_ALL_SUBJECT_CIT_QUERY_PROC = "{ CALL sp_subject_export }";

    @Id
    @Column(name = "id_subject", nullable = false)
    private short idSubject;

    @Column(name = "subject_name", nullable = false, columnDefinition = "char")
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

        ClSubjectCIT that = (ClSubjectCIT) o;

        if (idSubject != that.idSubject) return false;
        return subjectName != null ? subjectName.equals(that.subjectName) : that.subjectName == null;

    }

    @Override
    public int hashCode() {
        int result = idSubject;
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

    @Override
    public String getName() {
        return getSubjectName();
    }

    @Override
    public short getId() {
        return getIdSubject();
    }
}
