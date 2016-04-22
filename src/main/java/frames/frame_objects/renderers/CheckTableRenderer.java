package frames.frame_objects.renderers;

import entities.check.CheckTable;
import frames.CompareFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Created by USER on 21.04.2016.
 */
public class CheckTableRenderer extends JLabel implements ListCellRenderer<CheckTable> {
    private CompareFrame frame;

    public CheckTableRenderer(CompareFrame frame) {
        this.frame = frame;
        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends CheckTable> list, CheckTable checkTable,
                                                  int index, boolean isSelected, boolean cellHasFocus) {

        String text;
        if (checkTable.getId() == -1) {
            text = " ";
        } else {
            String mark = frame.getCheckTableDAO().getMarkById(checkTable.getMark()).getName();
            String subject = frame.getCheckTableDAO().getSubjectById(checkTable.getSubject()).getName();

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
