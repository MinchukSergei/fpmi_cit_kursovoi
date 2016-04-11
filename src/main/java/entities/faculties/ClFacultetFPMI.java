package entities.faculties;

import entities.UniversityObject;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by USER on 11.04.2016.
 */
@Entity
@Table(name = "cl_facultet")
@NamedQueries({
        @NamedQuery(name = ClFacultetFPMI.GET_ALL_FACULTET_FPMI,
                query = ClFacultetFPMI.GET_ALL_FACULTET_FPMI_QUERY)

})
public class ClFacultetFPMI implements Serializable, UniversityObject {
    public static final String GET_ALL_FACULTET_FPMI = "ClFacultetFPMI.getAll";
    static final String GET_ALL_FACULTET_FPMI_QUERY = "SELECT c FROM ClFacultetFPMI c";

    @Id
    @Column(name = "facultet_id", nullable = false)
    private short facultetId;

    @Column(name = "facultet_short", nullable = false, columnDefinition = "char")
    private String facultetName;

    public short getFacultetId() {
        return facultetId;
    }

    public void setFacultetId(short facultetId) {
        this.facultetId = facultetId;
    }

    public String getFacultetName() {
        return facultetName;
    }

    public void setFacultetName(String facultetName) {
        this.facultetName = facultetName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClFacultetFPMI that = (ClFacultetFPMI) o;

        if (facultetId != that.facultetId) return false;
        return facultetName != null ? facultetName.equals(that.facultetName) : that.facultetName == null;

    }

    @Override
    public int hashCode() {
        int result = (int) facultetId;
        result = 31 * result + (facultetName != null ? facultetName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return facultetName;
    }

    public String toStr() {
        return facultetName + String.valueOf(facultetId);
    }

    @Override
    public String getName() {
        return getFacultetName();
    }

    @Override
    public short getId() {
        return getFacultetId();
    }

}
