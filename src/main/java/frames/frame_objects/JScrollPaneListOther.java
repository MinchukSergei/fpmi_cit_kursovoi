package frames.frame_objects;

import drag_and_drop.FromTransferHandler;

import javax.swing.*;

/**
 * Created by USER on 07.04.2016.
 */
public class JScrollPaneListOther extends JScrollPaneListDB {

    public JScrollPaneListOther(String borderName) {
        super(borderName);
        setFromTransferHandler();
    }

    private void setFromTransferHandler() {
        FromTransferHandler fromTransferHandler = new FromTransferHandler();
        fromTransferHandler.setDragFrom(jList);

        jList.setTransferHandler(fromTransferHandler);
        jList.setDragEnabled(true);
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
}
