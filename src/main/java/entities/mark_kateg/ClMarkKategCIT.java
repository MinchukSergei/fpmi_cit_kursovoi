package entities.mark_kateg;

import entities.UniversityObject;
import entities.UniversityObjectProcedure;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by USER on 11.04.2016.
 */
@Entity
@Table(name = "cl_mark_kateg_cit")

@NamedNativeQueries({
        @NamedNativeQuery(name = ClMarkKategCIT.GET_ALL_MARK_KATEG_CIT_PROC,
                query = ClMarkKategCIT.GET_ALL_MARK_KATEG_CIT_QUERY_PROC,
                resultClass = ClMarkKategCIT.class)
})
public class ClMarkKategCIT implements Serializable, UniversityObject, UniversityObjectProcedure {

    public ClMarkKategCIT() {
        this.setMarkKategId((short) -1);
        this.setMarkKategName(" ");
    }

    public static final String GET_ALL_MARK_KATEG_CIT_PROC = "ClMarkKategCIT.markKategExport";
    static final String GET_ALL_MARK_KATEG_CIT_QUERY_PROC = "{ CALL sp_mark_kateg_export }";

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

        ClMarkKategCIT that = (ClMarkKategCIT) o;

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

    @Override
    public String getProcedureName() {
        return GET_ALL_MARK_KATEG_CIT_PROC;
    }
}
