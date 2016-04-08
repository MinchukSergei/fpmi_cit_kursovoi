package frames;

import drag_and_drop.FromTransferHandler;
import drag_and_drop.FromToTransferHandler;
import entities.*;
import frames.frame_objects.JScrollPaneListDB;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import service.impl.ServiceClSubjectCITImpl;
import service.impl.ServiceClSubjectFPMIImpl;
import service.impl.ServiceExportClSubjectImpl;

import javax.swing.*;
import java.awt.*;
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

    private void loadDataToFPMIList() {
        ServiceClSubjectFPMIImpl serviceClSubjectFPMI = new ServiceClSubjectFPMIImpl();
        serviceClSubjectFPMI.setSessionFactory(sessionFactoryFPMI);
        List<ClSubjectFPMI> subjNames = serviceClSubjectFPMI.getOrderedSubject();
        setDataToList(subjNames, scrollPaneFPMI);
    }

    private void loadDataToOtherList() {
        ServiceClSubjectCITImpl serviceClSubjectCIT = new ServiceClSubjectCITImpl();
        serviceClSubjectCIT.setSessionFactory(sessionFactoryCIT);
        List<ClSubjectCIT> subjNames = serviceClSubjectCIT.getOrderedSubject();
        setDataToList(subjNames, scrollPaneOther);
    }


    public void setUpButtonListeners(InitializeSubjectsFrame frame) {
        jButtonFillLists.addActionListener(e -> {
            loadDataToFPMIList();
            loadDataToOtherList();
            frame.repaint();
        });

        jButtonAccordance.addActionListener(e -> {
            putInAccordance(ClSubjectCIT.class);
            frame.repaint();
        });

        jButtonSync.addActionListener(e -> {
            synchronize();
        });
    }

    @SuppressWarnings("unchecked")
    private <FPMI, CIT> void synchronize() {
        List<ExportClSubject> exportClSubjects = new ArrayList<>();

        DefaultListModel<FPMI> fpmiModel;
        DefaultListModel<CIT> citModel;
        try {
            fpmiModel = (DefaultListModel<FPMI>) scrollPaneFPMI.getjList().getModel();
            citModel = (DefaultListModel<CIT>) scrollPaneCIT.getjList().getModel();
        } catch (ClassCastException exp) {
            JOptionPane.showMessageDialog(this, "Fill and accordance objects");
            return;
        }

        for (int i = 0; i < fpmiModel.getSize(); i++) {
            ExportClSubject exportClSubject = new ExportClSubject();
            exportClSubject.setIdSubject(((UniversityObject)fpmiModel.getElementAt(i)).getId());
            exportClSubjects.add(exportClSubject);
        }

        for (int i = 0; i < citModel.getSize(); i++) {
            if (((UniversityObject)citModel.get(i)).getId() == -1) {
                JOptionPane.showMessageDialog(this, "Put in accordance all objects.");
                return;
            }
            exportClSubjects.get(i).setIdSubjectCit(((UniversityObject)citModel.get(i)).getId());
        }

        ServiceExportClSubjectImpl serviceExportClSubject = new ServiceExportClSubjectImpl();
        serviceExportClSubject.setSessionFactory(sessionFactoryFPMI);

        try {
            serviceExportClSubject.updateAll(exportClSubjects);
        } catch (HibernateException exp) {
            JOptionPane.showMessageDialog(this, exp.getMessage());
            return;
        }
        JOptionPane.showMessageDialog(this, "All object successfully identifying.");
    }
}
