package entities.mark_type;

import entities.UniversityObject;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cl_mark_type")
@NamedQueries({
        @NamedQuery(name = ClMarkTypeFPMI.GET_ALL_MARK_TYPE_FPMI,
                query = ClMarkTypeFPMI.GET_ALL_MARK_TYPE_FPMI_QUERY)

})
public class ClMarkTypeFPMI implements Serializable, UniversityObject {
    public static final String GET_ALL_MARK_TYPE_FPMI = "ClMarkTypeFPMI.getAll";
    static final String GET_ALL_MARK_TYPE_FPMI_QUERY = "SELECT c FROM ClMarkTypeFPMI c";

    @Id
    @Column(name = "mark_type_id", nullable = false)
    private short markTypeId;

    @Column(name = "mark_type_name", nullable = false, columnDefinition = "char")
    private String markTypeName;

    public short getMarkTypeId() {
        return markTypeId;
    }

    public void setMarkTypeId(short markTypeId) {
        this.markTypeId = markTypeId;
    }

    public String getMarkTypeName() {
        return markTypeName;
    }

    public void setMarkTypeName(String markTypeName) {
        this.markTypeName = markTypeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClMarkTypeFPMI that = (ClMarkTypeFPMI) o;

        if (markTypeId != that.markTypeId) return false;
        return markTypeName != null ? markTypeName.equals(that.markTypeName) : that.markTypeName == null;

    }

    @Override
    public int hashCode() {
        int result = (int) markTypeId;
        result = 31 * result + (markTypeName != null ? markTypeName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return markTypeName;
    }

    public String toStr() {
        return markTypeName + String.valueOf(markTypeId);
    }

    @Override
    public String getName() {
        return getMarkTypeName();
    }

    @Override
    public short getId() {
        return getMarkTypeId();
    }

}
