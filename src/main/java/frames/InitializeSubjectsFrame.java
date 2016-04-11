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

    private void loadDataToFPMIList() {
        ServiceUniversityObjectFPMIImpl serviceClSubjectFPMI = new ServiceUniversityObjectFPMIImpl();
        serviceClSubjectFPMI.setSessionFactory(sessionFactoryFPMI);
        List<ClSubjectFPMI> subjNames = serviceClSubjectFPMI.getOrderedSubject();
        setDataToList(subjNames, scrollPaneFPMI);
    }

    private void loadDataToOtherList() {
        ServiceUniversityObjectCITImpl serviceClSubjectCIT = new ServiceUniversityObjectCITImpl();
        serviceClSubjectCIT.setSessionFactory(sessionFactoryCIT);
        List<ClSubjectCIT> subjNames = serviceClSubjectCIT.getOrderedSubject();
        setDataToList(subjNames, scrollPaneOther);
    }

    private void preloadingData() {
        ServiceExportUniversityObjectImpl serviceExportClSubject = new ServiceExportUniversityObjectImpl();
        serviceExportClSubject.setSessionFactory(sessionFactoryFPMI);

        ServiceUniversityObjectFPMIImpl serviceClSubjectFPMI = new ServiceUniversityObjectFPMIImpl();
        serviceClSubjectFPMI.setSessionFactory(sessionFactoryFPMI);

        ServiceUniversityObjectCITImpl serviceClSubjectCIT = new ServiceUniversityObjectCITImpl();
        serviceClSubjectCIT.setSessionFactory(sessionFactoryCIT);



        List<ClSubjectCIT> citSubject = serviceClSubjectCIT.callSubjectExport();

        List<ExportClSubject> exportList = serviceExportClSubject.getAll();

        List<ClSubjectFPMI> finalFpmiSubject = serviceClSubjectFPMI.getAll();
        List<ClSubjectCIT> finalCitSubject = new ArrayList<>();

        boolean exist;
        for (int i = 0; i < finalFpmiSubject.size(); i++) {
            exist = false;
            for (int j = 0; j < exportList.size(); j++) {
                if (finalFpmiSubject.get(i).getId() == exportList.get(j).getId()) {
                    exist = true;
                    finalCitSubject.add(findById(citSubject, exportList.get(j).getIdSubjectCit()));
                    removeById(citSubject, exportList.get(j).getIdSubjectCit());
                }
            }
            if (!exist) {
                finalCitSubject.add(new ClSubjectCIT());
            }
        }

        setDataToList(finalFpmiSubject, scrollPaneFPMI);
        setDataToList(finalCitSubject, scrollPaneCIT);
        setDataToList(citSubject, scrollPaneOther);
    }

    private void removeById(List<ClSubjectCIT> list, short id) {
        for (ClSubjectCIT subj : list) {
            if (subj.getIdSubject() == id) {
                list.remove(subj);
                return;
            }
        }
    }

    private ClSubjectCIT findById(List<ClSubjectCIT> list, short id) {
        for (ClSubjectCIT subj : list) {
            if (subj.getId() == id) {
                return subj;
            }
        }
        return new ClSubjectCIT();
    }

    public void setUpButtonListeners(InitializeSubjectsFrame frame) {
        jButtonFillLists.addActionListener(e -> {
            preloadingData();
//            loadDataToFPMIList();
//            loadDataToOtherList();
            frame.repaint();
        });

        jButtonAccordance.addActionListener(e -> {
            int counter = 0;
            if (scrollPaneCIT.getjList().getModel().getSize() != 0) {
                DefaultListModel<ClSubjectCIT> model = (DefaultListModel<ClSubjectCIT>) scrollPaneCIT.getjList().getModel();
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
//            if (((UniversityObject)citModel.get(i)).getId() == -1) {
//                JOptionPane.showMessageDialog(this, "Put in accordance all objects.");
//                return;
//            }
            exportClSubjects.get(i).setIdSubjectCit(((UniversityObject)citModel.get(i)).getId());
        }

        ServiceExportUniversityObjectImpl serviceExportClSubject = new ServiceExportUniversityObjectImpl();
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
