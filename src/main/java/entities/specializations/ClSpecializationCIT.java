package entities.specializations;

import entities.UniversityObject;
import entities.UniversityObjectProcedure;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by USER on 11.04.2016.
 */
@Entity
@Table(name = "cl_specialization_cit")

@NamedNativeQueries({
        @NamedNativeQuery(name = ClSpecializationCIT.GET_ALL_SPECIALIZATION_CIT_PROC,
                query = ClSpecializationCIT.GET_ALL_SPECIALIZATION_CIT_QUERY_PROC,
                resultClass = ClSpecializationCIT.class)
})
public class ClSpecializationCIT implements Serializable, UniversityObject, UniversityObjectProcedure {

    public ClSpecializationCIT() {
        this.setSpecId((short) -1);
        this.setSpecName(" ");
    }

    public static final String GET_ALL_SPECIALIZATION_CIT_PROC = "ClSpecializationCIT.specializationExport";
    static final String GET_ALL_SPECIALIZATION_CIT_QUERY_PROC = "{ CALL sp_specialization_export }";

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

        ClSpecializationCIT that = (ClSpecializationCIT) o;

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

    @Override
    public String getProcedureName() {
        return GET_ALL_SPECIALIZATION_CIT_PROC;
    }
}
