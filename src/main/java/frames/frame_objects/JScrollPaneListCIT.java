package frames.frame_objects;

import drag_and_drop.FromToTransferHandler;
import drag_and_drop.FromTransferHandler;

import javax.swing.*;

/**
 * Created by USER on 07.04.2016.
 */
public class JScrollPaneListCIT extends JScrollPaneListDB {
    public JScrollPaneListCIT(String borderName) {
        super(borderName);
        setFromToTransferHandler();
    }

    private void setFromToTransferHandler() {
        FromToTransferHandler toTransferHandler = new FromToTransferHandler(TransferHandler.MOVE);
        toTransferHandler.setDragFrom(jList);

        jList.setTransferHandler(toTransferHandler);
        jList.setDragEnabled(true);
        jList.setDropMode(DropMode.ON);
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
}
