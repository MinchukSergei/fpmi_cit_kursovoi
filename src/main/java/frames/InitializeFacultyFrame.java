package frames;

import entities.faculties.ClFacultetCIT;
import entities.faculties.ClFacultetFPMI;
import entities.faculties.ExportClFacultet;

import javax.swing.*;

/**
 * Created by USER on 11.04.2016.
 */
public class InitializeFacultyFrame extends InitFrame{
    public InitializeFacultyFrame(String title) {
        super(title);
        setUpButtonListeners(this);
        this.pack();
    }

    public void setUpButtonListeners(InitializeFacultyFrame frame) {
        jButtonFillLists.addActionListener(e -> {
            preloadingData(ClFacultetFPMI.GET_ALL_FACULTET_FPMI,
                    ClFacultetCIT.GET_ALL_FACULTET_CIT_PROC, ExportClFacultet.GET_ALL_ID, ClFacultetCIT.class);
            frame.repaint();
        });

        jButtonAccordance.addActionListener(e -> {
            int counter = 0;
            if (scrollPaneCIT.getjList().getModel().getSize() != 0) {
                DefaultListModel<ClFacultetCIT> model =
                        (DefaultListModel<ClFacultetCIT>) scrollPaneCIT.getjList().getModel();
                for (int i = 0; i < model.getSize(); i++) {
                    if (model.get(i).getId() != -1)
                        counter++;
                }
            }

            if (counter == 0)
                putInAccordance(ClFacultetCIT.class);
            frame.repaint();
        });

        jButtonSync.addActionListener(e -> {
            synchronize(ExportClFacultet.class);
        });
    }
}
