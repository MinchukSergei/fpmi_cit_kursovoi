package frames;

import entities.students.ClStudentCIT;
import entities.students.ClStudentFPMI;
import entities.students.ExportClStudent;

import javax.swing.*;

/**
 * Created by USER on 11.04.2016.
 */
public class InitializeStudentsFrame extends InitFrame {

    public InitializeStudentsFrame(String title) {
        super(title);
        setUpButtonListeners(this);
        this.pack();
    }

    public void setUpButtonListeners(InitializeStudentsFrame frame) {
        jButtonFillLists.addActionListener(e -> {
            preloadingData(ClStudentFPMI.GET_ALL_STUDENT_FPMI,
                    ClStudentCIT.GET_ALL_STUDENT_CIT_PROC, ExportClStudent.GET_ALL_ID, ClStudentCIT.class);
            frame.repaint();
        });

        jButtonAccordance.addActionListener(e -> {
            int counter = 0;
            if (scrollPaneCIT.getjList().getModel().getSize() != 0) {
                DefaultListModel<ClStudentCIT> model =
                        (DefaultListModel<ClStudentCIT>) scrollPaneCIT.getjList().getModel();
                for (int i = 0; i < model.getSize(); i++) {
                    if (model.get(i).getId() != -1)
                        counter++;
                }
            }

            if (counter == 0)
                putInAccordance(ClStudentCIT.class);
            frame.repaint();
        });

        jButtonSync.addActionListener(e -> {
            synchronize(ExportClStudent.class);
        });
    }
}
