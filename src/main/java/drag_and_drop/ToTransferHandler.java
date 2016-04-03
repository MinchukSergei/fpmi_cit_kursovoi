package drag_and_drop;

import entities.ClSubjectCIT;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.io.StringReader;

/**
 * Created by USER on 02.04.2016.
 */
public class ToTransferHandler extends TransferHandler {
    int action;
    private int index = 0;
    boolean self = false;

    private JList dragFrom;
    private DefaultListModel<ClSubjectCIT> from;

    public JList getDragFrom() {
        return dragFrom;
    }

    public void setDragFrom(JList dragFrom) {
        this.dragFrom = dragFrom;
    }

    public int getSourceActions(JComponent comp) {
        return MOVE;
    }



    @Override
    protected Transferable createTransferable(JComponent c) {
        self = true;
        from = (DefaultListModel<ClSubjectCIT>) dragFrom.getModel();
        index = dragFrom.getSelectedIndex();
        if (index < 0 || index >= from.getSize()) {
            return null;
        }

        return new ClSubjectCITTransferable((ClSubjectCIT) dragFrom.getSelectedValue());
    }

    @Override
    protected void exportDone(JComponent source, Transferable data, int action) {
        if (action != MOVE) {
            return;
        }

        if (!self) {
            from.removeElementAt(index);
        } else {
            self = false;
        }
    }

    public ToTransferHandler(int action) {
        this.action = action;
    }

    public boolean canImport(TransferHandler.TransferSupport support) {
        // for the demo, we will only support drops (not clipboard paste)
        if (!support.isDrop()) {
            return false;
        }

        // we only import Strings
        if (!support.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            return false;
        }

        // check if the source actions contain the desired action -
        // either copy or move, depending on what was specified when
        // this instance was created
        boolean actionSupported = (action & support.getSourceDropActions()) == action;
        if (actionSupported) {
            support.setDropAction(action);
            return true;
        }

        // the desired action is not supported, so reject the transfer
        return false;
    }

    public boolean importData(TransferHandler.TransferSupport support) {
        // if we cannot handle the import, say so
        if (!canImport(support)) {
            return false;
        }

        // fetch the drop location
        JList.DropLocation dl = (JList.DropLocation) support.getDropLocation();

        int index = dl.getIndex();

        // fetch the data and bail if this fails
        ClSubjectCIT data;
        try {
            data = (ClSubjectCIT) support.getTransferable().getTransferData(DataFlavor.imageFlavor);
        } catch (UnsupportedFlavorException e) {
            return false;
        } catch (java.io.IOException e) {
            return false;
        }

        JList list = (JList) support.getComponent();
        DefaultListModel model = (DefaultListModel) list.getModel();
        if (self) {
            ClSubjectCIT a = (ClSubjectCIT) model.get(this.index);
            ClSubjectCIT b = (ClSubjectCIT) model.get(index);
            a = returnFirst(b, b = a);
            model.setElementAt(a, this.index);
            model.setElementAt(b, index);
        } else if (model.get(index).equals(" ")) {
            model.insertElementAt(data, index);
            model.removeElementAt(index + 1);
        } else {
            model.insertElementAt(data, index);
            boolean last = false;
            for (int i = index; i < model.getSize(); i++) {
                if (((ClSubjectCIT)model.get(i)).getSubjectName().equals(" ")) {
                    model.removeElementAt(i);
                    last = true;
                    break;
                }
            }
            if (!last) {
                for (int i = index; i >= 0; i--) {
                    if (((ClSubjectCIT)model.get(i)).getSubjectName().equals(" ")) {
                        model.removeElementAt(i);
                        break;
                    }
                }
            }
        }

        Rectangle rect = list.getCellBounds(index, index);
        list.scrollRectToVisible(rect);
        list.setSelectedIndex(index);
        list.requestFocusInWindow();



        return true;
    }

    private ClSubjectCIT returnFirst(ClSubjectCIT o1, ClSubjectCIT o2) {
        return o1;
    }
}
