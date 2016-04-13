package entities.specializations;

import entities.ExportUniversityObject;
import entities.UniversityObject;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "export_cl_specialization")
@NamedQueries({
        @NamedQuery(name = ExportClSpecialization.GET_ALL_ID, query = ExportClSpecialization.GET_ALL_ID_QUERY)
})
public class ExportClSpecialization implements Serializable, UniversityObject, ExportUniversityObject{
    public static final String GET_ALL_ID = "ExportClSpecialization.getAll";
    static final String GET_ALL_ID_QUERY = "SELECT e FROM ExportClSpecialization e";

    @Id
    @Column(name = "id_spec")
    private short idSpec;

    @Column(name = "id_spec_cit")
    private short idSpecCit;

    public short getIdSpec() {
        return idSpec;
    }

    public void setIdSpec(short idSpec) {
        this.idSpec = idSpec;
    }

    public short getIdSpecCit() {
        return idSpecCit;
    }

    public void setIdSpecCit(short idSpecCit) {
        this.idSpecCit = idSpecCit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExportClSpecialization that = (ExportClSpecialization) o;

        if (idSpec != that.idSpec) return false;
        return idSpecCit == that.idSpecCit;

    }

    @Override
    public int hashCode() {
        int result = (int) idSpec;
        result = 31 * result + (int) idSpecCit;
        return result;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public short getId() {
        return getIdSpec();
    }

    @Override
    public void setId(short id) {
        this.idSpec = id;
    }

    @Override
    public void setIdCit(short idCit) {
        this.idSpecCit = idCit;
    }

    @Override
    public short getIdCit() {
        return idSpecCit;
    }
}
