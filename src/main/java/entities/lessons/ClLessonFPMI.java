package entities.lessons;

import entities.UniversityObject;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by USER on 11.04.2016.
 */
@Entity
@Table(name = "cl_lesson")
@NamedQueries({
        @NamedQuery(name = ClLessonFPMI.GET_ALL_LESSON_FPMI, query = ClLessonFPMI.GET_ALL_LESSON_FPMI_QUERY)

})
public class ClLessonFPMI implements Serializable, UniversityObject {
    public static final String GET_ALL_LESSON_FPMI = "ClLessonFMPI.getAll";
    static final String GET_ALL_LESSON_FPMI_QUERY = "SELECT c FROM ClLessonFPMI c";

    @Id
    @Column(name = "lesson_id")
    private short lessonId;

    @Column(name = "lesson_name", columnDefinition = "char")
    private String lessonName;

    public short getLessonId() {
        return lessonId;
    }

    public void setLessonId(short lessonId) {
        this.lessonId = lessonId;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClLessonFPMI that = (ClLessonFPMI) o;

        if (lessonId != that.lessonId) return false;
        return lessonName != null ? lessonName.equals(that.lessonName) : that.lessonName == null;

    }

    @Override
    public int hashCode() {
        int result = (int) lessonId;
        result = 31 * result + (lessonName != null ? lessonName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return lessonName;
    }

    public String toStr() {
        return lessonName + String.valueOf(lessonId);
    }

    @Override
    public String getName() {
        return getLessonName();
    }

    @Override
    public short getId() {
        return getLessonId();
    }

}
