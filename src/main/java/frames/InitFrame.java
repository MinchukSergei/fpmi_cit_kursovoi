package frames;

import entities.ExportUniversityObject;
import entities.UniversityObject;
import frames.frame_objects.JScrollPaneListCIT;
import frames.frame_objects.JScrollPaneListDB;
import frames.frame_objects.JScrollPaneListFPMI;
import frames.frame_objects.JScrollPaneListOther;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import service.impl.ServiceExportUniversityObjectImpl;
import service.impl.ServiceUniversityObjectCITImpl;
import service.impl.ServiceUniversityObjectFPMIImpl;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Created by USER on 07.04.2016.
 */
public class InitFrame extends JFrame {
    private int FRAME_WIDTH = 700;
    private int FRAME_HEIGHT = 500;

    private JLabel jLabelObjectName;

    protected JScrollPaneListCIT scrollPaneCIT;
    protected JScrollPaneListFPMI scrollPaneFPMI;
    protected JScrollPaneListOther scrollPaneOther;

    protected JButton jButtonSync;
    protected JButton jButtonFillLists;
    protected JButton jButtonAccordance;

    protected SessionFactory sessionFactoryFPMI;
    protected SessionFactory sessionFactoryCIT;

    protected ServiceExportUniversityObjectImpl serviceExportObject;
    protected ServiceUniversityObjectCITImpl serviceObjectCIT;
    protected ServiceUniversityObjectFPMIImpl serviceObjectFPMI = new ServiceUniversityObjectFPMIImpl();




    public InitFrame(String title) {
        super(title);
        initComponents();
        setUpDefault();
        setUpPane(getCurrHeight(), getCurrWidth());
        this.pack();
    }

    private void initComponents() {
        jLabelObjectName = new JLabel();

        scrollPaneCIT = new JScrollPaneListCIT("CIT");
        scrollPaneFPMI = new JScrollPaneListFPMI("FPMI");
        scrollPaneOther = new JScrollPaneListOther("Choose names");

        jButtonSync = new JButton("Synchronize");
        jButtonFillLists = new JButton("Fill lists");
        jButtonAccordance = new JButton("Put in accordance");

        serviceExportObject = new ServiceExportUniversityObjectImpl();
        serviceObjectCIT = new ServiceUniversityObjectCITImpl();
        serviceObjectFPMI = new ServiceUniversityObjectFPMIImpl();
    }

    public SessionFactory getSessionFactoryFPMI() {
        return sessionFactoryFPMI;
    }

    public void setSessionFactoryFPMI(SessionFactory sessionFactoryFPMI) {
        this.sessionFactoryFPMI = sessionFactoryFPMI;
        serviceExportObject.setSessionFactory(sessionFactoryFPMI);
        serviceObjectFPMI.setSessionFactory(sessionFactoryFPMI);
    }

    public SessionFactory getSessionFactoryCIT() {
        return sessionFactoryCIT;
    }

    public void setSessionFactoryCIT(SessionFactory sessionFactoryCIT) {
        this.sessionFactoryCIT = sessionFactoryCIT;
        serviceObjectCIT.setSessionFactory(sessionFactoryCIT);
    }


    public void setObjectName(String name) {
        jLabelObjectName.setText(name);
    }

    private void setUpDefault() {
        this.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void validate() {
        super.validate();
        setUpPane(getCurrWidth(), getCurrHeight());
    }

    private int getCurrHeight() {
        int minScrollHeight = 200;
        if (this.getContentPane().getSize().getHeight() < minScrollHeight)
            return minScrollHeight;
        return (int) (this.getContentPane().getSize().getHeight() - minScrollHeight / 2);
    }

    private int getCurrWidth() {
        int minScrollWidth = 100;
        if (Math.round(this.getContentPane().getSize().getWidth() / 3) - 15 < minScrollWidth)
            return minScrollWidth;
        return (int) (Math.round(this.getContentPane().getSize().getWidth() / 3.) - 15);
    }

    public <T> void setDataToList(java.util.List<T> names, JScrollPaneListDB paneListDB) {
        DefaultListModel<T> model = new DefaultListModel<>();
        for (T name : names) {
            model.addElement(name);
        }

        paneListDB.getjList().setModel(model);
    }

    protected <FPMI, CIT, EXPORT> void preloadingData(String fpmiQuery, String citQuery, String exportQuery,
                                                      Class<CIT> clazz) {
        java.util.List<CIT> citList = serviceObjectCIT.callProcedureExport(citQuery);
        java.util.List<EXPORT> exportList = serviceExportObject.getAll(exportQuery);
        java.util.List<FPMI> finalFpmiList = serviceObjectFPMI.getAll(fpmiQuery);

        java.util.List<CIT> finalCitList = new ArrayList<>();

        boolean exist;
        for (int i = 0; i < finalFpmiList.size(); i++) {
            exist = false;
            for (int j = 0; j < exportList.size(); j++) {
                if (((UniversityObject)finalFpmiList.get(i)).getId()
                        == ((UniversityObject)exportList.get(j)).getId()) {
                    exist = true;
                    finalCitList.add(findById(citList, ((ExportUniversityObject)exportList.get(j)).getIdCit(), clazz));
                    removeById(citList, ((ExportUniversityObject)exportList.get(j)).getIdCit());
                }
            }
            if (!exist) {
                try {
                    finalCitList.add(clazz.newInstance());
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        setDataToList(finalFpmiList, scrollPaneFPMI);
        setDataToList(finalCitList, scrollPaneCIT);
        setDataToList(citList, scrollPaneOther);
    }

    private <CIT> void removeById(java.util.List<CIT> list, short id) {
        for (CIT subj : list) {
            if (((UniversityObject)subj).getId() == id) {
                list.remove(subj);
                return;
            }
        }
    }

    private <CIT> CIT findById(java.util.List<CIT> list, short id, Class<CIT> clazz) {
        for (CIT subj : list) {
            if (((UniversityObject)subj).getId() == id) {
                return subj;
            }
        }

        CIT elem = null;
        try {
            elem = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return elem;
    }

    @SuppressWarnings("unchecked")
    protected  <FPMI, CIT, EXPORT> void synchronize(Class<EXPORT> clazz) {
        java.util.List<EXPORT> exportClSubjects = new ArrayList<>();

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
            EXPORT elem = null;
            try {
                elem = clazz.newInstance();
                ((ExportUniversityObject)elem).setId(((UniversityObject)fpmiModel.getElementAt(i)).getId());
                exportClSubjects.add(elem);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < citModel.getSize(); i++) {
//            if (((UniversityObject)citModel.get(i)).getId() == -1) {
//                JOptionPane.showMessageDialog(this, "Put in accordance all objects.");
//                return;
//            }
            ((ExportUniversityObject)exportClSubjects.get(i)).setIdCit(((UniversityObject)citModel.get(i)).getId());
        }

        try {
            serviceExportObject.updateAll(exportClSubjects);
        } catch (HibernateException exp) {
            JOptionPane.showMessageDialog(this, exp.getMessage());
            return;
        }
        JOptionPane.showMessageDialog(this, "All object successfully identifying.");
    }



    @SuppressWarnings("unchecked")
    public <FPMI, CIT> void putInAccordance(Class<CIT> clazz) {
        DefaultListModel<FPMI> fpmiNamesModel;
        DefaultListModel<CIT> otherNamesModel;

        try {
            fpmiNamesModel = (DefaultListModel<FPMI>) (scrollPaneFPMI.getjList().getModel());
            otherNamesModel = (DefaultListModel<CIT>) (scrollPaneOther.getjList().getModel());
        } catch (ClassCastException e) {
            JOptionPane.showMessageDialog(this, "Fill lists.");
            return;
        }

        java.util.List<FPMI> fpmiList = new ArrayList<>();
        java.util.List<CIT> otherList = new ArrayList<>();
        java.util.List<CIT> citList = new ArrayList<>();

        for (int i = 0; i < fpmiNamesModel.size(); i++) {
            fpmiList.add(fpmiNamesModel.get(i));
        }

        for (int i = 0; i < otherNamesModel.size(); i++) {
            otherList.add(otherNamesModel.get(i));
        }

        for (int i = 0; i < fpmiList.size(); i++) {
            boolean exist = false;
            for (int j = 0; j < otherList.size(); j++) {
                if (((UniversityObject)fpmiList.get(i)).getName()
                        .equals(((UniversityObject)otherList.get(j)).getName())) {
                    exist = true;
                    citList.add(otherList.get(j));
                    otherList.remove(j);
                }
            }
            if (!exist) {
                try {
                    CIT elem = clazz.newInstance();
                    citList.add(elem);
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        setDataToList(citList, scrollPaneCIT);
        setDataToList(otherList, scrollPaneOther);
    }

    private void setUpPane(int scrollPaneWidth, int scrollPaneHeight) {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(jButtonFillLists)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButtonAccordance)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButtonSync))
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(scrollPaneFPMI, javax.swing.GroupLayout.PREFERRED_SIZE, scrollPaneWidth, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(scrollPaneCIT, javax.swing.GroupLayout.PREFERRED_SIZE, scrollPaneWidth, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(scrollPaneOther, javax.swing.GroupLayout.PREFERRED_SIZE, scrollPaneWidth, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(jLabelObjectName))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabelObjectName)
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(scrollPaneFPMI, javax.swing.GroupLayout.DEFAULT_SIZE, scrollPaneHeight, Short.MAX_VALUE)
                                                .addComponent(scrollPaneCIT, javax.swing.GroupLayout.DEFAULT_SIZE, scrollPaneHeight, Short.MAX_VALUE))
                                        .addComponent(scrollPaneOther, javax.swing.GroupLayout.PREFERRED_SIZE, scrollPaneHeight, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButtonSync)
                                        .addComponent(jButtonFillLists)
                                        .addComponent(jButtonAccordance))
                                .addContainerGap())
        );
    }
}
