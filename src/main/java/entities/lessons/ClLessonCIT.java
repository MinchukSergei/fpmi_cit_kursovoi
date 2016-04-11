package entities.lessons;

import entities.UniversityObject;
import entities.UniversityObjectProcedure;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by USER on 11.04.2016.
 */
@Entity
@Table(name = "cl_lesson_cit")
@NamedQueries({
        @NamedQuery(name = ClLessonCIT.GET_ALL_LESSON_CIT, query = ClLessonCIT.GET_ALL_LESSON_CIT_QUERY)

})
@NamedNativeQueries({
        @NamedNativeQuery(name = ClLessonCIT.GET_ALL_LESSON_CIT_PROC,
                query = ClLessonCIT.GET_ALL_LESSON_CIT_QUERY_PROC,
                resultClass = ClLessonCIT.class)
})
public class ClLessonCIT implements Serializable, UniversityObject, UniversityObjectProcedure {

    public ClLessonCIT() {
        this.setLessonId((short) -1);
        this.setLessonName(" ");
    }

    public static final String GET_ALL_LESSON_CIT = "ClLessonCIT.getAll";
    static final String GET_ALL_LESSON_CIT_QUERY = "SELECT c FROM ClLessonCIT c";

    public static final String GET_ALL_LESSON_CIT_PROC = "ClLessonCIT.lessonExport";
    static final String GET_ALL_LESSON_CIT_QUERY_PROC = "{ CALL sp_lesson_export }";

    @Id
    @Column(name = "lesson_id", nullable = false)
    private short lessonId;

    @Column(name = "lesson_name", nullable = false, columnDefinition = "char")
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

        ClLessonCIT that = (ClLessonCIT) o;

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

    @Override
    public String getProcedureName() {
        return GET_ALL_LESSON_CIT_PROC;
    }
}
