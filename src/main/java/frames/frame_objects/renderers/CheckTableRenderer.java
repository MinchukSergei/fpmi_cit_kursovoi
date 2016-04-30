package frames.frame_objects.renderers;

import entities.check.CheckTable;
import frames.CompareFrame;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by USER on 21.04.2016.
 */
public class CheckTableRenderer extends JLabel implements ListCellRenderer<CheckTable> {
    private int NZLength = 7;
    private int FAMLength = 20;
    private int IMLength = 20;
    private int COURCELength = 1;
    private int SUBJECTLength = 20;
    private int MARKLength = 20;
    private int SEMLength = 2;
    private int DATELength = 10;
    private int VEDOMLength = 10;

    private CompareFrame frame;

    public CheckTableRenderer(CompareFrame frame) {
        this.frame = frame;
        setOpaque(true);
        this.setFont(new Font("monospaced", Font.PLAIN, 13));
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends CheckTable> list, CheckTable checkTable,
                                                  int index, boolean isSelected, boolean cellHasFocus) {

        String text;
        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        if (checkTable.getId() == -1) {
            text = " ";
        } else {
            String mark = frame.getCheckTableDAO().getMarkById(checkTable.getMark()).getName();
            String subject = frame.getCheckTableDAO().getSubjectById(checkTable.getSubject()).getName();

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
