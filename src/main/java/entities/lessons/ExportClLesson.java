package entities.lessons;

import entities.UniversityObject;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "export_cl_lesson")
@NamedQueries({
        @NamedQuery(name = ExportClLesson.GET_ALL_ID, query = ExportClLesson.GET_ALL_ID_QUERY)
})
public class ExportClLesson implements Serializable, UniversityObject {
    public static final String GET_ALL_ID = "ExportClLesson.getAll";
    static final String GET_ALL_ID_QUERY = "SELECT e FROM ExportClLesson e";

    @Id
    @Column(name = "id_lesson")
    private short idLesson;

    @Column(name = "id_lesson_cit")
    private short idLessonCit;

    public short getIdLesson() {
        return idLesson;
    }

    public void setIdLesson(short idLesson) {
        this.idLesson = idLesson;
    }

    public short getIdLessonCit() {
        return idLessonCit;
    }

    public void setIdLessonCit(short idLessonCit) {
        this.idLessonCit = idLessonCit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExportClLesson that = (ExportClLesson) o;

        if (idLesson != that.idLesson) return false;
        return idLessonCit == that.idLessonCit;

    }

    @Override
    public int hashCode() {
        int result = (int) idLesson;
        result = 31 * result + (int) idLessonCit;
        return result;
    }

    @Override
    public String toString() {
        return "ExportClLesson{" +
                "idLesson=" + idLesson +
                ", idLessonCit=" + idLessonCit +
                '}';
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public short getId() {
        return getIdLesson();
    }
}
