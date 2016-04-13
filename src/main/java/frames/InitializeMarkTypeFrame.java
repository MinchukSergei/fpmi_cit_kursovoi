package frames;

import entities.mark_type.ClMarkTypeCIT;
import entities.mark_type.ClMarkTypeFPMI;
import entities.mark_type.ExportClMarkType;

import javax.swing.*;

/**
 * Created by USER on 11.04.2016.
 */
public class InitializeMarkTypeFrame extends InitFrame {
    public InitializeMarkTypeFrame(String title) {
        super(title);
        setUpButtonListeners(this);
        this.pack();
    }

    public void setUpButtonListeners(InitializeMarkTypeFrame frame) {
        jButtonFillLists.addActionListener(e -> {
            preloadingData(ClMarkTypeFPMI.GET_ALL_MARK_TYPE_FPMI,
                    ClMarkTypeCIT.GET_ALL_MARK_TYPE_CIT_PROC, ExportClMarkType.GET_ALL_ID, ClMarkTypeCIT.class);
            frame.repaint();
        });

        jButtonAccordance.addActionListener(e -> {
            int counter = 0;
            if (scrollPaneCIT.getjList().getModel().getSize() != 0) {
                DefaultListModel<ClMarkTypeCIT> model =
                        (DefaultListModel<ClMarkTypeCIT>) scrollPaneCIT.getjList().getModel();
                for (int i = 0; i < model.getSize(); i++) {
                    if (model.get(i).getId() != -1)
                        counter++;
                }
            }

            if (counter == 0)
                putInAccordance(ClMarkTypeCIT.class);
            frame.repaint();
        });

        jButtonSync.addActionListener(e -> {
            synchronize(ExportClMarkType.class);
        });
    }
}
