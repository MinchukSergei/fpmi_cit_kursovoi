package drag_and_drop;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 * Created by USER on 02.04.2016.
 */
public class ClUniversityObjectCITTransferable<CIT> implements Transferable {
    private CIT data;

    public ClUniversityObjectCITTransferable(CIT data) {
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
