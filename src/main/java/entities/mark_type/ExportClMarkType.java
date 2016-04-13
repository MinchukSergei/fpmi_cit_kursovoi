package entities.mark_type;

import entities.ExportUniversityObject;
import entities.UniversityObject;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "export_cl_mark_type")
@NamedQueries({
        @NamedQuery(name = ExportClMarkType.GET_ALL_ID,
                query = ExportClMarkType.GET_ALL_ID_QUERY)
})
public class ExportClMarkType implements Serializable, UniversityObject, ExportUniversityObject {
    public static final String GET_ALL_ID = "ExportClMarkType.getAll";
    static final String GET_ALL_ID_QUERY = "SELECT e FROM ExportClMarkType e";

    @Id
    @Column(name = "id_mark_type")
    private short idMarkType;

    @Column(name = "id_mark_type_cit")
    private short idMarkTypeCit;

    public short getIdMarkType() {
        return idMarkType;
    }

    public void setIdMarkType(short idMarkType) {
        this.idMarkType = idMarkType;
    }

    public short getIdMarkTypeCit() {
        return idMarkTypeCit;
    }

    public void setIdMarkTypeCit(short idMarkTypeCit) {
        this.idMarkTypeCit = idMarkTypeCit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExportClMarkType that = (ExportClMarkType) o;

        if (idMarkType != that.idMarkType) return false;
        return idMarkTypeCit == that.idMarkTypeCit;

    }

    @Override
    public int hashCode() {
        int result = (int) idMarkType;
        result = 31 * result + (int) idMarkTypeCit;
        return result;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public short getId() {
        return getIdMarkType();
    }

    @Override
    public void setId(short id) {
        this.idMarkType = id;
    }

    @Override
    public void setIdCit(short idCit) {
        this.idMarkTypeCit = idCit;
    }

    @Override
    public short getIdCit() {
        return idMarkTypeCit;
    }
}
