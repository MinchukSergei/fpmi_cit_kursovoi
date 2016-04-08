package drag_and_drop;

import entities.ClSubjectCIT;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.io.StringReader;

/**
 * Created by USER on 02.04.2016.
 */
public class ClSubjectCITTransferable<CIT> implements Transferable {
    private CIT data;

    public ClSubjectCITTransferable(CIT data) {
        this.data = data;
    }

    private static final DataFlavor[] flavors = {
            DataFlavor.stringFlavor,
            DataFlavor.imageFlavor
    };

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return flavors.clone();
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return true;
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        return data;
    }
}
