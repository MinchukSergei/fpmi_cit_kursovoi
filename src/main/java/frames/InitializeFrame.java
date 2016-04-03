package frames;

import drag_and_drop.FromTransferHandler;
import drag_and_drop.ToTransferHandler;
import entities.ClSubjectCIT;
import entities.ClSubjectFPMI;
import entities.ExportClSubject;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import service.ServiceExportClSubject;
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
public class InitializeFrame extends JFrame {
    private int FRAME_WIDTH = 700;
    private int FRAME_HEIGHT = 500;


    private JLabel jLabelObjectName;
    private JList jListFPMI;
    private JList jListCIT;
    private JList jListOTHER;
    private JScrollPane jScrollPaneFPMI;
    private JScrollPane jScrollPaneCIT;
    private JScrollPane jScrollPaneOTHER;
    private JButton jButtonSync;
    private JButton jButtonFillLists;
    private JButton jButtonAccordance;

    private SessionFactory sessionFactoryFPMI;
    private SessionFactory sessionFactoryCIT;

    public SessionFactory getSessionFactoryFPMI() {
        return sessionFactoryFPMI;
    }

    public void setSessionFactoryFPMI(SessionFactory sessionFactoryFPMI) {
        this.sessionFactoryFPMI = sessionFactoryFPMI;
    }

    public SessionFactory getSessionFactoryCIT() {
        return sessionFactoryCIT;
    }

    public void setSessionFactoryCIT(SessionFactory sessionFactoryCIT) {
        this.sessionFactoryCIT = sessionFactoryCIT;
    }

    public InitializeFrame() {
        super();
        this.setTitle("Subject identifying");
        setUpDefault();
        initComponents();
        setUpPane();
        setUpButtonListeners(this);
        setDropModeOnLists();
    }

    private void initComponents() {
        jScrollPaneFPMI = new JScrollPane();
        jListFPMI = new JList();
        jScrollPaneCIT = new JScrollPane();
        jListCIT = new JList();
        jScrollPaneOTHER = new JScrollPane();
        jListOTHER = new JList();
        jButtonSync = new JButton();
        jButtonFillLists = new JButton();
        jButtonAccordance = new JButton();
        jLabelObjectName = new JLabel();
    }

    private void setUpDefault() {
        this.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void setBordersToLists() {
        jScrollPaneCIT.setBorder(BorderFactory.createTitledBorder("CIT"));
        jScrollPaneFPMI.setBorder(BorderFactory.createTitledBorder("FPMI"));
        jScrollPaneOTHER.setBorder(BorderFactory.createTitledBorder("Choose names"));
    }

    private void setDropModeOnLists() {
        FromTransferHandler fromTransferHandler = new FromTransferHandler();
        fromTransferHandler.setDragFrom(jListOTHER);

        jListOTHER.setTransferHandler(fromTransferHandler);
        jListOTHER.setDragEnabled(true);
        jListOTHER.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        ToTransferHandler toTransferHandler = new ToTransferHandler(TransferHandler.MOVE);
        toTransferHandler.setDragFrom(jListCIT);

        jListCIT.setTransferHandler(toTransferHandler);
        jListCIT.setDragEnabled(true);
        jListCIT.setDropMode(DropMode.ON);
        jListCIT.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }

    private void loadDataToFPMIList() {
        ServiceClSubjectFPMIImpl serviceClSubjectFPMI = new ServiceClSubjectFPMIImpl();
        serviceClSubjectFPMI.setSessionFactory(sessionFactoryFPMI);
        List<ClSubjectFPMI> subjNames = serviceClSubjectFPMI.getOrderedSubject();
        setDataToList(subjNames, jListFPMI, jScrollPaneFPMI);
    }

    private void loadDataToCITList() {
        jScrollPaneCIT.setViewportView(jListCIT);
    }

    private void loadDataToOtherList() {
        ServiceClSubjectCITImpl serviceClSubjectCIT = new ServiceClSubjectCITImpl();
        serviceClSubjectCIT.setSessionFactory(sessionFactoryCIT);
        List<ClSubjectCIT> subjNames = serviceClSubjectCIT.getOrderedSubject();

        setDataToList(subjNames, jListOTHER, jScrollPaneOTHER);
    }

    public void setLabelTitle(String name) {
        jLabelObjectName.setText(name);
    }

    public void setUpButtonListeners(InitializeFrame frame) {
        jButtonFillLists.addActionListener(e -> {
            loadDataToFPMIList();
            loadDataToCITList();
            loadDataToOtherList();
            frame.revalidate();
            frame.repaint();
        });

        jButtonAccordance.addActionListener(e -> {
            putInAccordance();
            frame.revalidate();
            frame.repaint();
        });

        jButtonSync.addActionListener(e -> {
            List<ExportClSubject> exportClSubjects = new ArrayList<>();

            DefaultListModel<ClSubjectFPMI> fpmiModel = null;
            DefaultListModel<ClSubjectCIT> citModel = null;
            try {
                fpmiModel = (DefaultListModel<ClSubjectFPMI>) jListFPMI.getModel();
                citModel = (DefaultListModel<ClSubjectCIT>) jListCIT.getModel();
            } catch (ClassCastException exp) {
                JOptionPane.showMessageDialog(this, "Fill and accordance objects");
                return;
            }

            for (int i = 0; i < fpmiModel.getSize(); i++) {
                ExportClSubject exportClSubject = new ExportClSubject();
                exportClSubject.setIdSubject(fpmiModel.getElementAt(i).getIdSubject());
                exportClSubjects.add(exportClSubject);
            }

            for (int i = 0; i < citModel.getSize(); i++) {
                if (citModel.get(i).getIdSubject() == -1) {
                    JOptionPane.showMessageDialog(this, "Put in accordance all objects.");
                    return;
                }
                exportClSubjects.get(i).setIdSubjectCit(citModel.get(i).getIdSubject());
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
        });
    }

    public <T> void setDataToList(List<T> names, JList list, JScrollPane scrollPane) {
        DefaultListModel<T> model = new DefaultListModel<>();
        for (T name : names) {
            model.addElement(name);
        }

        list.setModel(model);
        scrollPane.setViewportView(list);
    }

    public void putInAccordance() {
        DefaultListModel<ClSubjectFPMI> fpmiNamesModel = null;
        DefaultListModel<ClSubjectCIT> otherNamesModel = null;
        try {
            fpmiNamesModel = (DefaultListModel<ClSubjectFPMI>) (jListFPMI.getModel());
            otherNamesModel = (DefaultListModel<ClSubjectCIT>) (jListOTHER.getModel());
        } catch (ClassCastException e) {
            JOptionPane.showMessageDialog(this, "Fill lists.");
            return;
        }

        List<ClSubjectFPMI> fpmiList = new ArrayList<>();
        List<ClSubjectCIT> otherList = new ArrayList<>();
        List<ClSubjectCIT> citList = new ArrayList<>();

        for (int i = 0; i < fpmiNamesModel.size(); i++) {
            fpmiList.add(fpmiNamesModel.get(i));
        }

        for (int i = 0; i < otherNamesModel.size(); i++) {
            otherList.add(otherNamesModel.get(i));
        }

        for (int i = 0; i < fpmiList.size(); i++) {
            boolean exist = false;
            for (int j = 0; j < otherList.size(); j++) {
                if (fpmiList.get(i).getSubjectName().equals(otherList.get(j).getSubjectName())) {
                    exist = true;
                    citList.add(otherList.get(j));
                    otherList.remove(j);
                }
            }
            if (!exist) {
                citList.add(new ClSubjectCIT());
            }
        }

        setDataToList(citList, jListCIT, jScrollPaneCIT);
        setDataToList(otherList, jListOTHER, jScrollPaneOTHER);
    }


    private void setUpPane() {
        setBordersToLists();

        jButtonFillLists.setText("Fill lists");
        jButtonSync.setText("Synchronize");
        jButtonAccordance.setText("Put in accordance");

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
                                                                .addComponent(jScrollPaneFPMI, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(jScrollPaneCIT, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jScrollPaneOTHER, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                                .addComponent(jScrollPaneFPMI, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                                                .addComponent(jScrollPaneCIT))
                                        .addComponent(jScrollPaneOTHER, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButtonSync)
                                        .addComponent(jButtonFillLists)
                                        .addComponent(jButtonAccordance))
                                .addContainerGap())
        );

        this.pack();
    }
}
