package frames.frame_objects;

import javax.swing.*;
import java.awt.*;

/**
 * Created by USER on 07.04.2016.
 */
public class JScrollPaneListDB extends JScrollPane {
    protected JList jList;

    public JScrollPaneListDB(String borderName) {
        super();
        this.setBorder(BorderFactory.createTitledBorder(borderName));
        this.jList = new JList();
        this.jList.setCellRenderer(getRenderer());
        this.setViewportView(jList);
    }

    private ListCellRenderer<? super String> getRenderer() {
        return new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list,
                                                          Object value, int index, boolean isSelected,
                                                          boolean cellHasFocus) {
                JLabel listCellRendererComponent = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                listCellRendererComponent.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
                return listCellRendererComponent;
            }
        };
    }

    public JList getjList() {
        return jList;
    }

    public void setjList(JList jList) {
        this.jList = jList;
    }
}
