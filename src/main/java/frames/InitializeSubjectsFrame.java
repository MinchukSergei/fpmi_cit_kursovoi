package frames;

import entities.*;
import entities.subjects.ClSubjectCIT;
import entities.subjects.ClSubjectFPMI;
import entities.subjects.ExportClSubject;
import org.hibernate.HibernateException;
import service.impl.ServiceUniversityObjectCITImpl;
import service.impl.ServiceUniversityObjectFPMIImpl;
import service.impl.ServiceExportUniversityObjectImpl;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by USER on 01.04.2016.
 */
public class InitializeSubjectsFrame extends InitFrame {
    public InitializeSubjectsFrame(String title) {
        super(title);

        setUpButtonListeners(this);
        this.pack();
    }

    public void setUpButtonListeners(InitializeSubjectsFrame frame) {
        jButtonFillLists.addActionListener(e -> {
            preloadingData(ClSubjectFPMI.GET_ALL_SUBJECT_FPMI,
                    ClSubjectCIT.GET_ALL_SUBJECT_CIT_PROC, ExportClSubject.GET_ALL_ID, ClSubjectCIT.class);
//            loadDataToFPMIList();
//            loadDataToOtherList();
            frame.repaint();
        });

        jButtonAccordance.addActionListener(e -> {
            int counter = 0;
            if (scrollPaneCIT.getjList().getModel().getSize() != 0) {
                DefaultListModel<ClSubjectCIT> model =
                        (DefaultListModel<ClSubjectCIT>) scrollPaneCIT.getjList().getModel();
                for (int i = 0; i < model.getSize(); i++) {
                    if (model.get(i).getId() != -1)
                        counter++;
                }
            }

            if (counter == 0)
                putInAccordance(ClSubjectCIT.class);
            frame.repaint();
        });

        jButtonSync.addActionListener(e -> {
            synchronize(ExportClSubject.class);
        });
    }

}
