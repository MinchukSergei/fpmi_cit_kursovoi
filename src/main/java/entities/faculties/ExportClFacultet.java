package entities.faculties;

import entities.UniversityObject;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "export_cl_facultet")
@NamedQueries({
        @NamedQuery(name = ExportClFacultet.GET_ALL_ID, query = ExportClFacultet.GET_ALL_ID_QUERY)
})
public class ExportClFacultet implements Serializable, UniversityObject {
    public static final String GET_ALL_ID = "ExportClFacultet.getAll";
    static final String GET_ALL_ID_QUERY = "SELECT e FROM ExportClFacultet e";

    @Id
    @Column(name = "id_facultet")
    private short idFacultet;

    @Column(name = "id_facultet_cit")
    private short idFacultetCit;

    public short getIdFacultet() {
        return idFacultet;
    }

    public void setIdFacultet(short idFacultet) {
        this.idFacultet = idFacultet;
    }

    public short getIdFacultetCit() {
        return idFacultetCit;
    }

    public void setIdFacultetCit(short idFacultetCit) {
        this.idFacultetCit = idFacultetCit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExportClFacultet that = (ExportClFacultet) o;

        if (idFacultet != that.idFacultet) return false;
        return idFacultetCit == that.idFacultetCit;

    }

    @Override
    public int hashCode() {
        int result = (int) idFacultet;
        result = 31 * result + (int) idFacultetCit;
        return result;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public short getId() {
        return getIdFacultet();
    }
}
