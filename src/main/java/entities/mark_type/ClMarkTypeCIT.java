package entities.mark_type;

import entities.UniversityObject;
import entities.UniversityObjectProcedure;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by USER on 11.04.2016.
 */
@Entity
@Table(name = "cl_mark_type_cit")

@NamedNativeQueries({
        @NamedNativeQuery(name = ClMarkTypeCIT.GET_ALL_MARK_TYPE_CIT_PROC,
                query = ClMarkTypeCIT.GET_ALL_MARK_TYPE_CIT_QUERY_PROC,
                resultClass = ClMarkTypeCIT.class)
})
public class ClMarkTypeCIT implements Serializable, UniversityObject, UniversityObjectProcedure {

    public ClMarkTypeCIT() {
        this.setMarkTypeId((short) -1);
        this.setMarkTypeName(" ");
    }

    public static final String GET_ALL_MARK_TYPE_CIT_PROC = "ClMarkTypeCIT.markTypeExport";
    static final String GET_ALL_MARK_TYPE_CIT_QUERY_PROC = "{ CALL sp_mark_type_export }";

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

        ClMarkTypeCIT that = (ClMarkTypeCIT) o;

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

    @Override
    public String getProcedureName() {
        return GET_ALL_MARK_TYPE_CIT_PROC;
    }
}
