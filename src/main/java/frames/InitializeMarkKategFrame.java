package frames;


import entities.mark_kateg.ClMarkKategCIT;
import entities.mark_kateg.ClMarkKategFPMI;
import entities.mark_kateg.ExportClMarkKateg;

import javax.swing.*;

/**
 * Created by USER on 11.04.2016.
 */
public class InitializeMarkKategFrame extends InitFrame {

    public InitializeMarkKategFrame(String title) {
        super(title);
        setUpButtonListeners(this);
        this.pack();
    }

    public void setUpButtonListeners(InitializeMarkKategFrame frame) {
        jButtonFillLists.addActionListener(e -> {
            preloadingData(ClMarkKategFPMI.GET_ALL_MARK_KATEG_FPMI,
                    ClMarkKategCIT.GET_ALL_MARK_KATEG_CIT_PROC, ExportClMarkKateg.GET_ALL_ID, ClMarkKategCIT.class);
            frame.repaint();
        });

        jButtonAccordance.addActionListener(e -> {
            int counter = 0;
            if (scrollPaneCIT.getjList().getModel().getSize() != 0) {
                DefaultListModel<ClMarkKategCIT> model =
                        (DefaultListModel<ClMarkKategCIT>) scrollPaneCIT.getjList().getModel();
                for (int i = 0; i < model.getSize(); i++) {
                    if (model.get(i).getId() != -1)
                        counter++;
                }
            }

            if (counter == 0)
                putInAccordance(ClMarkKategCIT.class);
            frame.repaint();
        });

        jButtonSync.addActionListener(e -> {
            synchronize(ExportClMarkKateg.class);
        });
    }
}
