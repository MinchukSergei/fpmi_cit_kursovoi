package entities.mark_kateg;

import entities.ExportUniversityObject;
import entities.UniversityObject;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "export_cl_mark_kateg")
@NamedQueries({
        @NamedQuery(name = ExportClMarkKateg.GET_ALL_ID, query = ExportClMarkKateg.GET_ALL_ID_QUERY)
})
public class ExportClMarkKateg implements Serializable, UniversityObject, ExportUniversityObject {
    public static final String GET_ALL_ID = "ExportClMarkKateg.getAll";
    static final String GET_ALL_ID_QUERY = "SELECT e FROM ExportClMarkKateg e";

    @Id
    @Column(name = "id_mark_kateg")
    private short idMarkKateg;

    @Column(name = "id_mark_kateg_cit")
    private short idMarkKategCit;

    public short getIdMarkKateg() {
        return idMarkKateg;
    }

    public void setIdMarkKateg(short idMarkKateg) {
        this.idMarkKateg = idMarkKateg;
    }

    public short getIdMarkKategCit() {
        return idMarkKategCit;
    }

    public void setIdMarkKategCit(short idMarkKategCit) {
        this.idMarkKategCit = idMarkKategCit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExportClMarkKateg that = (ExportClMarkKateg) o;

        if (idMarkKateg != that.idMarkKateg) return false;
        return idMarkKategCit == that.idMarkKategCit;

    }

    @Override
    public int hashCode() {
        int result = (int) idMarkKateg;
        result = 31 * result + (int) idMarkKategCit;
        return result;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public short getId() {
        return getIdMarkKateg();
    }

    @Override
    public void setId(short id) {
        this.idMarkKateg = id;
    }

    @Override
    public void setIdCit(short idCit) {
        this.idMarkKategCit = idCit;
    }

    @Override
    public short getIdCit() {
        return idMarkKategCit;
    }
}
