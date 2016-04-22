package entities.check;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by USER on 17.04.2016.
 */
@Entity
@Table(name = "check_table_cit")

@NamedNativeQueries({
        @NamedNativeQuery(name = CheckTableCit.RETRY_GET_ALL_PROC, query = CheckTableCit.RETRY_GET_ALL_PROC_QUERY,
        resultClass = CheckTableCit.class)
})
public class CheckTableCit implements Serializable {
    public CheckTableCit() {
        this.id = -1;
        this.nz = "";
        this.course = -1;
        this.famrus = "";
        this.imrus = "";
        this.mark = -1;
        this.subject = -1;
    }

    public final static String RETRY_GET_ALL_PROC = "CheckTableCit.getAll";
    static final String RETRY_GET_ALL_PROC_QUERY = "{ CALL sp_export_check_table }";

    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "nz", nullable = false, columnDefinition = "char")
    private String nz;

    @Column(name = "famrus", nullable = false, columnDefinition = "char")
    private String famrus;

    @Column(name = "imrus", nullable = false, columnDefinition = "char")
    private String imrus;

    @Column(name = "course", nullable = false)
    private short course;

    @Column(name = "subject", nullable = false)
    private short subject;

    @Column(name = "mark", nullable = false)
    private short mark;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNz() {
        return nz;
    }

    public void setNz(String nz) {
        this.nz = nz;
    }

    public String getFamrus() {
        return famrus;
    }

    public void setFamrus(String famrus) {
        this.famrus = famrus;
    }

    public String getImrus() {
        return imrus;
    }

    public void setImrus(String imrus) {
        this.imrus = imrus;
    }

    public short getCourse() {
        return course;
    }

    public void setCourse(short course) {
        this.course = course;
    }

    public short getSubject() {
        return subject;
    }

    public void setSubject(short subject) {
        this.subject = subject;
    }

    public short getMark() {
        return mark;
    }

    public void setMark(short mark) {
        this.mark = mark;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CheckTableCit that = (CheckTableCit) o;

        if (id != that.id) return false;
        if (course != that.course) return false;
        if (subject != that.subject) return false;
        if (mark != that.mark) return false;
        if (nz != null ? !nz.equals(that.nz) : that.nz != null) return false;
        if (famrus != null ? !famrus.equals(that.famrus) : that.famrus != null) return false;
        return imrus != null ? imrus.equals(that.imrus) : that.imrus == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nz != null ? nz.hashCode() : 0);
        result = 31 * result + (famrus != null ? famrus.hashCode() : 0);
        result = 31 * result + (imrus != null ? imrus.hashCode() : 0);
        result = 31 * result + (int) course;
        result = 31 * result + (int) subject;
        result = 31 * result + (int) mark;
        return result;
    }

    @Override
    public String toString() {
        return nz.trim() + ' ' +
                famrus.trim() + ' ' +
                imrus.trim() + ' ' +
                course +
                subject + ' ' +
                mark;
    }
}
