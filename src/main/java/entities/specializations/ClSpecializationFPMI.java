package entities.specializations;

import entities.UniversityObject;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by USER on 11.04.2016.
 */
@Entity
@Table(name = "cl_specialization")
@NamedQueries({
        @NamedQuery(name = ClSpecializationFPMI.GET_ALL_SPECIALIZATION_FPMI,
                query = ClSpecializationFPMI.GET_ALL_SPECIALIZATION_FPMI_QUERY)

})
public class ClSpecializationFPMI implements Serializable, UniversityObject {
    public static final String GET_ALL_SPECIALIZATION_FPMI = "ClSpecializationFPMI.getAll";
    static final String GET_ALL_SPECIALIZATION_FPMI_QUERY = "SELECT c FROM ClSpecializationFPMI c";

    @Id
    @Column(name = "spec_id", nullable = false)
    private short specId;

    @Column(name = "spec_char", nullable = false, columnDefinition = "char")
    private String specName;

    public short getSpecId() {
        return specId;
    }

    public void setSpecId(short specId) {
        this.specId = specId;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClSpecializationFPMI that = (ClSpecializationFPMI) o;

        if (specId != that.specId) return false;
        return specName != null ? specName.equals(that.specName) : that.specName == null;

    }

    @Override
    public int hashCode() {
        int result = (int) specId;
        result = 31 * result + (specName != null ? specName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return specName;
    }

    public String toStr() {
        return specName + String.valueOf(specId);
    }

    @Override
    public String getName() {
        return getSpecName();
    }

    @Override
    public short getId() {
        return getSpecId();
    }

}
