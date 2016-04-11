package entities.mark_kateg;

import entities.UniversityObject;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by USER on 11.04.2016.
 */
@Entity
@Table(name = "cl_mark_kateg")
@NamedQueries({
        @NamedQuery(name = ClMarkKategFPMI.GET_ALL_MARK_KATEG_FPMI,
                query = ClMarkKategFPMI.GET_ALL_MARK_KATEG_FPMI_QUERY)

})
public class ClMarkKategFPMI implements Serializable, UniversityObject {
    public static final String GET_ALL_MARK_KATEG_FPMI = "ClMarkKategFPMI.getAll";
    static final String GET_ALL_MARK_KATEG_FPMI_QUERY = "SELECT c FROM ClMarkKategFPMI c";

    @Id
    @Column(name = "mark_kateg_id", nullable = false)
    private short markKategId;

    @Column(name = "mark_kateg_name", nullable = false, columnDefinition = "char")
    private String markKategName;

    public short getMarkKategId() {
        return markKategId;
    }

    public void setMarkKategId(short markKategId) {
        this.markKategId = markKategId;
    }

    public String getMarkKategName() {
        return markKategName;
    }

    public void setMarkKategName(String markKategName) {
        this.markKategName = markKategName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClMarkKategFPMI that = (ClMarkKategFPMI) o;

        if (markKategId != that.markKategId) return false;
        return markKategName != null ? markKategName.equals(that.markKategName) : that.markKategName == null;

    }

    @Override
    public int hashCode() {
        int result = (int) markKategId;
        result = 31 * result + (markKategName != null ? markKategName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return markKategName;
    }

    public String toStr() {
        return markKategName + String.valueOf(markKategId);
    }

    @Override
    public String getName() {
        return getMarkKategName();
    }

    @Override
    public short getId() {
        return getMarkKategId();
    }

}
