package frames;

import entities.specializations.ClSpecializationCIT;
import entities.specializations.ClSpecializationFPMI;
import entities.specializations.ExportClSpecialization;

import javax.swing.*;

/**
 * Created by USER on 11.04.2016.
 */
public class InitializeSpecializationFrame extends InitFrame {
    public InitializeSpecializationFrame(String title) {
        super(title);
        setUpButtonListeners(this);
        this.pack();
    }


    public void setUpButtonListeners(InitializeSpecializationFrame frame) {
        jButtonFillLists.addActionListener(e -> {
            preloadingData(ClSpecializationFPMI.GET_ALL_SPECIALIZATION_FPMI,
                    ClSpecializationCIT.GET_ALL_SPECIALIZATION_CIT_PROC,
                    ExportClSpecialization.GET_ALL_ID, ClSpecializationCIT.class);
            frame.repaint();
        });

        jButtonAccordance.addActionListener(e -> {
            int counter = 0;
            if (scrollPaneCIT.getjList().getModel().getSize() != 0) {
                DefaultListModel<ClSpecializationCIT> model =
                        (DefaultListModel<ClSpecializationCIT>) scrollPaneCIT.getjList().getModel();
                for (int i = 0; i < model.getSize(); i++) {
                    if (model.get(i).getId() != -1)
                        counter++;
                }
            }

            if (counter == 0)
                putInAccordance(ClSpecializationCIT.class);
            frame.repaint();
        });

        jButtonSync.addActionListener(e -> {
            synchronize(ExportClSpecialization.class);
        });
    }
}
