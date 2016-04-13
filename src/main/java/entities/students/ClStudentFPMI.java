package entities.students;

import entities.UniversityObject;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by USER on 11.04.2016.
 */
@Entity
@Table(name = "cl_student")
@NamedQueries({
        @NamedQuery(name = ClStudentFPMI.GET_ALL_STUDENT_FPMI,
                query = ClStudentFPMI.GET_ALL_STUDENT_FPMI_QUERY)

})
public class ClStudentFPMI implements Serializable, UniversityObject {
    public static final String GET_ALL_STUDENT_FPMI = "ClStudentFPMI.getAll";
    static final String GET_ALL_STUDENT_FPMI_QUERY = "SELECT c FROM ClStudentFPMI c";

    @Id
    @Column(name = "student_id", nullable = false)
    private short studentId;

    @Column(name = "nz", nullable = false, columnDefinition = "char")
    private String nZ;

    @Column(name = "famrus", nullable = false, columnDefinition = "char")
    private String famRus;

    @Column(name = "imrus", nullable = false, columnDefinition = "char")
    private String imRus;

    @Column(name = "course", nullable = false)
    private short course;

    public short getStudentId() {
        return studentId;
    }

    public void setStudentId(short studentId) {
        this.studentId = studentId;
    }

    public String getnZ() {
        return nZ;
    }

    public void setnZ(String nZ) {
        this.nZ = nZ;
    }

    public String getFamRus() {
        return famRus;
    }

    public void setFamRus(String famRus) {
        this.famRus = famRus;
    }

    public String getImRus() {
        return imRus;
    }

    public void setImRus(String imRus) {
        this.imRus = imRus;
    }

    public short getCourse() {
        return course;
    }

    public void setCourse(short course) {
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClStudentFPMI that = (ClStudentFPMI) o;

        if (studentId != that.studentId) return false;
        if (course != that.course) return false;
        if (nZ != null ? !nZ.equals(that.nZ) : that.nZ != null) return false;
        if (famRus != null ? !famRus.equals(that.famRus) : that.famRus != null) return false;
        return imRus != null ? imRus.equals(that.imRus) : that.imRus == null;

    }

    @Override
    public int hashCode() {
        int result = (int) studentId;
        result = 31 * result + (nZ != null ? nZ.hashCode() : 0);
        result = 31 * result + (famRus != null ? famRus.hashCode() : 0);
        result = 31 * result + (imRus != null ? imRus.hashCode() : 0);
        result = 31 * result + (int) course;
        return result;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %d", nZ.trim(), famRus.trim(), imRus.trim(), course);
    }

    public String toStr() {
        return nZ + String.valueOf(studentId);
    }

    @Override
    public String getName() {
        return getnZ();
    }

    @Override
    public short getId() {
        return getStudentId();
    }

}
