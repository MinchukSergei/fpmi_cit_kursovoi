package drag_and_drop;

import entities.UniversityObject;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.io.StringReader;

/**
 * Created by USER on 02.04.2016.
 */
public class FromToTransferHandler<CIT> extends TransferHandler {
    private int action;
    private int index = 0;
    boolean self = false;

    private JList dragFrom;
    private DefaultListModel<CIT> from;

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
    @SuppressWarnings("unchecked")
    protected Transferable createTransferable(JComponent c) {
        self = true;
        from = (DefaultListModel<CIT>) dragFrom.getModel();
        index = dragFrom.getSelectedIndex();
        if (index < 0 || index >= from.getSize()) {
            return null;
        }

        return new ClSubjectCITTransferable(dragFrom.getSelectedValue());
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

    public FromToTransferHandler(int action) {
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

    @SuppressWarnings("unchecked")
    public boolean importData(TransferHandler.TransferSupport support) {
        // if we cannot handle the import, say so
        if (!canImport(support)) {
            return false;
        }

        // fetch the drop location
        JList.DropLocation dl = (JList.DropLocation) support.getDropLocation();

        int index = dl.getIndex();

        // fetch the data and bail if this fails
        CIT data;
        try {
            data = (CIT) support.getTransferable().getTransferData(DataFlavor.imageFlavor);
        } catch (UnsupportedFlavorException e) {
            return false;
        } catch (java.io.IOException e) {
            return false;
        }

        JList list = (JList) support.getComponent();
        DefaultListModel model = (DefaultListModel) list.getModel();
        if (self) {
            CIT a = (CIT) model.get(this.index);
            CIT b = (CIT) model.get(index);
            a = returnFirst(b, b = a);
            model.setElementAt(a, this.index);
            model.setElementAt(b, index);
        } else if (model.get(index).equals(" ")) {
            model.insertElementAt(data, index);
            model.removeElementAt(index + 1);
        } else {
            model.insertElementAt(data, index);
            insertToNearest(model, index);
        }

        Rectangle rect = list.getCellBounds(index, index);
        list.scrollRectToVisible(rect);
        list.setSelectedIndex(index);
        list.requestFocusInWindow();


        return true;
    }

    private void insertToNearest(DefaultListModel model, int index) {
        if (!findDown(model, index))
            findUp(model, index);
    }

    private boolean findDown(DefaultListModel model, int index) {
        for (int i = index; i < model.getSize(); i++) {
            if (((UniversityObject) model.get(i)).getName().equals(" ")) {
                model.removeElementAt(i);
                return true;
            }
        }
        return false;
    }

    private void findUp(DefaultListModel model, int index) {
        for (int i = index; i >= 0; i--) {
            if (((UniversityObject) model.get(i)).getName().equals(" ")) {
                model.removeElementAt(i);
                break;
            }
        }
    }

    private CIT returnFirst(CIT o1, CIT o2) {
        return o1;
    }
}
