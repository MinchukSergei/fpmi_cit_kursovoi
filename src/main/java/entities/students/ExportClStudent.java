package entities.students;

import entities.ExportUniversityObject;
import entities.UniversityObject;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "export_cl_student")
@NamedQueries({
        @NamedQuery(name = ExportClStudent.GET_ALL_ID, query = ExportClStudent.GET_ALL_ID_QUERY)
})
public class ExportClStudent implements Serializable, UniversityObject, ExportUniversityObject {
    public static final String GET_ALL_ID = "ExportClStudent.getAll";
    static final String GET_ALL_ID_QUERY = "SELECT e FROM ExportClStudent e";

    @Id
    @Column(name = "id_student")
    private short idStudent;

    @Column(name = "id_student_cit")
    private short idStudentCit;

    public short getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(short idStudent) {
        this.idStudent = idStudent;
    }

    public short getIdStudentCit() {
        return idStudentCit;
    }

    public void setIdStudentCit(short idStudentCit) {
        this.idStudentCit = idStudentCit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExportClStudent that = (ExportClStudent) o;

        if (idStudent != that.idStudent) return false;
        return idStudentCit == that.idStudentCit;

    }

    @Override
    public int hashCode() {
        int result = (int) idStudent;
        result = 31 * result + (int) idStudentCit;
        return result;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public short getId() {
        return getIdStudent();
    }

    @Override
    public void setId(short id) {
        this.idStudent = id;
    }

    @Override
    public void setIdCit(short idCit) {
        this.idStudentCit = idCit;
    }

    @Override
    public short getIdCit() {
        return idStudentCit;
    }
}
