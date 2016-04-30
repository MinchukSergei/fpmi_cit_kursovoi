package frames.frame_objects.renderers;

import entities.check.CheckTableCit;
import entities.mark_type.ClMarkTypeCIT;
import entities.subjects.ClSubjectCIT;
import frames.CompareFrame;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by USER on 21.04.2016.
 */
public class CheckTableCitRenderer extends JLabel implements ListCellRenderer<CheckTableCit> {
    private List<ClSubjectCIT> subjectList;
    private List<ClMarkTypeCIT> markTypeList;

    private int NZLength = 7;
    private int FAMLength = 20;
    private int IMLength = 20;
    private int COURCELength = 1;
    private int SUBJECTLength = 20;
    private int MARKLength = 20;
    private int SEMLength = 2;
    private int DATELength = 10;
    private int VEDOMLength = 10;

    public CheckTableCitRenderer(List<ClSubjectCIT> subjectList, List<ClMarkTypeCIT> markTypeList) {
        this.subjectList = subjectList;
        this.markTypeList = markTypeList;
        setOpaque(true);
        this.setFont( new Font("monospaced", Font.PLAIN, 13) );
    }

    private String getNameSubjectById(short id) {
        for (ClSubjectCIT c : subjectList) {
            if (c.getId() == id)
                return c.getName();
        }
        return "";
    }

    private String getNameMarkById(short id) {
        for (ClMarkTypeCIT c : markTypeList) {
            if (c.getId() == id)
                return c.getName();
        }
        return "";
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends CheckTableCit> list, CheckTableCit checkTable,
                                                  int index, boolean isSelected, boolean cellHasFocus) {
        String text;
        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        if (checkTable.getId() == -1) {
            text = " ";
        } else {
            String mark = getNameMarkById(checkTable.getMark());
            String subject = getNameSubjectById(checkTable.getSubject());

            text = fixedLengthString(checkTable.getNz().trim(), NZLength) + ' ' +
                    fixedLengthString(checkTable.getFamrus().trim(), FAMLength) + ' ' +
                    fixedLengthString(checkTable.getImrus().trim(), IMLength) + ' ' +
                    fixedLengthString(String.valueOf(checkTable.getCourse()), COURCELength) + ' ' +
                    fixedLengthString(subject.trim(), SUBJECTLength) + ' ' +
                    fixedLengthString(mark.trim(), MARKLength) + ' ' +
                    fixedLengthString(String.valueOf(checkTable.getSemestr()), SEMLength) + ' ' +
                    fixedLengthString(formatter.format(checkTable.getDate()), DATELength) + ' ' +
                    fixedLengthString(checkTable.getVedomost(), VEDOMLength);
        }

        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        this.setText(text);

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        return this;
    }

    public static String fixedLengthString(String string, int length) {
        return String.format("%-" + length + "s", string);
    }
}
