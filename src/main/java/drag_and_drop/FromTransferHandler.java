package drag_and_drop;

import entities.ClSubjectCIT;

import javax.swing.*;
import java.awt.datatransfer.Transferable;

public class FromTransferHandler extends TransferHandler {
    private JList dragFrom;
    private DefaultListModel<ClSubjectCIT> from;

    public JList getDragFrom() {
        return dragFrom;
    }

    public void setDragFrom(JList dragFrom) {
        this.dragFrom = dragFrom;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getSourceActions(JComponent comp) {
        return MOVE;
    }

    private int index = 0;

    public Transferable createTransferable(JComponent comp) {
        from = (DefaultListModel<ClSubjectCIT>) dragFrom.getModel();
        index = dragFrom.getSelectedIndex();
        if (index < 0 || index >= from.getSize()) {
            return null;
        }

        return new ClSubjectCITTransferable((ClSubjectCIT) dragFrom.getSelectedValue());
    }

    public void exportDone(JComponent comp, Transferable trans, int action) {
        if (action != MOVE) {
            return;
        }

        from.removeElementAt(index);
    }
}