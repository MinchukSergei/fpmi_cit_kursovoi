package frames.frame_objects.renderers;

import entities.check.CheckTableCit;
import entities.mark_type.ClMarkTypeCIT;
import entities.subjects.ClSubjectCIT;
import frames.CompareFrame;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by USER on 21.04.2016.
 */
public class CheckTableCitRenderer extends JLabel implements ListCellRenderer<CheckTableCit> {
    private List<ClSubjectCIT> subjectList;
    private List<ClMarkTypeCIT> markTypeList;

    public CheckTableCitRenderer(List<ClSubjectCIT> subjectList, List<ClMarkTypeCIT> markTypeList) {
        this.subjectList = subjectList;
        this.markTypeList = markTypeList;
        setOpaque(true);
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
        if (checkTable.getId() == -1) {
            text = " ";
        } else {
            String mark = getNameMarkById(checkTable.getMark());
            String subject = getNameSubjectById(checkTable.getSubject());

            text = checkTable.getNz().trim() + ' ' +
                    checkTable.getFamrus().trim() + ' ' +
                    checkTable.getImrus().trim() + ' ' +
                    checkTable.getCourse() + ' ' +
                    subject.trim() + ' ' +
                    mark.trim();
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
}
