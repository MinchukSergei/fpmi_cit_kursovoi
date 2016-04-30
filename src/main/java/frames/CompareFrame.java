package frames;

import com.sun.deploy.util.ArrayUtil;
import dao.impl.DAOCheckTableCitImpl;
import dao.impl.DAOCheckTableImpl;
import entities.check.CheckTable;
import entities.check.CheckTableCit;
import entities.check.CompareCheckTables;
import entities.mark_type.ClMarkTypeCIT;
import entities.subjects.ClSubjectCIT;
import frames.frame_objects.renderers.CheckTableCitRenderer;
import frames.frame_objects.renderers.CheckTableRenderer;
import javafx.util.Pair;
import org.hibernate.SessionFactory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Created by USER on 21.04.2016.
 */
public class CompareFrame extends InitFrame {
    private DAOCheckTableImpl checkTableDAO;
    private DAOCheckTableCitImpl checkTableCitDAO;

    private JCheckBox jCheckBoxHidden;
    private JCheckBox jCheckBoxLight;

    private JComboBox jComboBoxCourse;
    private JComboBox jComboBoxSpec;
    private JComboBox jComboBoxGroup;

    private JLabel jLabelCourse;
    private JLabel jLabelSpec;
    private JLabel jLabelGroup;

    private List<Pair<CheckTable, CheckTableCit>> hiddenStudents;

    public DAOCheckTableImpl getCheckTableDAO() {
        return checkTableDAO;
    }

    public void setCheckTableDAO(DAOCheckTableImpl checkTableDAO) {
        this.checkTableDAO = checkTableDAO;
    }

    public DAOCheckTableCitImpl getCheckTableCitDAO() {
        return checkTableCitDAO;
    }

    public void setCheckTableCitDAO(DAOCheckTableCitImpl checkTableCitDAO) {
        this.checkTableCitDAO = checkTableCitDAO;
    }


    public CompareFrame(String title) {
        super(title);
        forbidDragAndDrop();
        disableButton(jButtonSync);
        setUpButtonListeners(this);
        initFilterComponents();
        addFilter();
        this.repaint();
    }

    private void initFilterComponents() {
        jCheckBoxHidden = new JCheckBox("Hidden overlap");
        jComboBoxCourse = new JComboBox();
        jComboBoxSpec = new JComboBox();
        jComboBoxGroup = new JComboBox();
        jLabelCourse = new JLabel("Course");
        jLabelSpec = new JLabel("Spec");
        jLabelGroup = new JLabel("Group");
        jCheckBoxLight = new JCheckBox("Light non-overlap");
    }

    public SessionFactory getSessionFactoryFPMI() {
        return sessionFactoryFPMI;
    }

    public void setSessionFactoryFPMI(SessionFactory sessionFactoryFPMI) {
        super.setSessionFactoryFPMI(sessionFactoryFPMI);
        checkTableDAO.setSessionFactory(sessionFactoryFPMI);
    }

    public SessionFactory getSessionFactoryCIT() {
        return sessionFactoryCIT;
    }

    public void setSessionFactoryCIT(SessionFactory sessionFactoryCIT) {
        super.setSessionFactoryCIT(sessionFactoryCIT);
        checkTableCitDAO.setSessionFactory(sessionFactoryCIT);
    }

    private void setCellRenderer() {
        List<ClSubjectCIT> subjects = serviceObjectCIT.getAll(ClSubjectCIT.GET_ALL_SUBJECT_CIT_PROC);
        List<ClMarkTypeCIT> marks = serviceObjectCIT.getAll(ClMarkTypeCIT.GET_ALL_MARK_TYPE_CIT_PROC);
        this.scrollPaneFPMI.getjList().setCellRenderer(new CheckTableRenderer(this));
        this.scrollPaneCIT.getjList().setCellRenderer(new CheckTableCitRenderer(subjects, marks));
        this.scrollPaneOther.getjList().setCellRenderer(new CheckTableCitRenderer(subjects, marks));
        this.scrollPaneOther.getjList().setModel(new DefaultListModel<CheckTableCit>());
        this.scrollPaneCIT.getjList().setModel(new DefaultListModel<CheckTableCit>());
        this.scrollPaneFPMI.getjList().setModel(new DefaultListModel<CheckTable>());
    }

    private void setJComboBoxModels() {
        jComboBoxCourse.setModel(new DefaultComboBoxModel(new String[]{"All", "1", "2", "3", "4"}));
        jComboBoxSpec.setModel(new DefaultComboBoxModel(new String[]{"All", "Item 1", "Item 2", "Item 3", "Item 4"}));
        jComboBoxGroup.setModel(new DefaultComboBoxModel(new String[]{"All", "Item 1", "Item 2", "Item 3", "Item 4"}));

        jCheckBoxLight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultListModel<CheckTable> modelFpmi = null;
                DefaultListModel<CheckTableCit> modelCit = null;
                try {
                    modelFpmi = (DefaultListModel<CheckTable>) scrollPaneFPMI.getjList().getModel();
                    modelCit = (DefaultListModel<CheckTableCit>) scrollPaneCIT.getjList().getModel();
                } catch (ClassCastException e1) {
                    JOptionPane.showMessageDialog(CompareFrame.this, "Put in accordance");
                    return;
                }

                if (jCheckBoxLight.isSelected()) {
                    ArrayList<Integer> selectedIndexes = new ArrayList<>();
                    for (int i = 0; i < modelCit.size(); i++) {
                        if (modelCit.get(i).getId() == -1) {
                            selectedIndexes.add(i);
                        }
                    }
                    scrollPaneFPMI.getjList().setSelectedIndices(listToArray(selectedIndexes));
                } else {
                    scrollPaneFPMI.getjList().setSelectedIndices(new int[0]);
                }
            }
        });

        jComboBoxCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(jComboBoxCourse.getSelectedIndex());
            }
        });

        hiddenStudents = new ArrayList<>();
        jCheckBoxHidden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultListModel<CheckTable> modelFpmi = null;
                DefaultListModel<CheckTableCit> modelCit = null;
                try {
                    modelFpmi = (DefaultListModel<CheckTable>) scrollPaneFPMI.getjList().getModel();
                    modelCit = (DefaultListModel<CheckTableCit>) scrollPaneCIT.getjList().getModel();
                } catch (ClassCastException e1) {
                    JOptionPane.showMessageDialog(CompareFrame.this, "Fill lists");
                    return;
                }

                List<Integer> indexesToRemove = new ArrayList<Integer>();
                if (jCheckBoxHidden.isSelected()) {
                    for (int i = 0; i < modelCit.getSize(); i++) {
                        CheckTableCit cit = modelCit.get(i);
                        if (cit.getId() != -1) {
                            hiddenStudents.add(new Pair<>(modelFpmi.get(i), modelCit.get(i)));
                            indexesToRemove.add(i);
                        }
                    }
                    Collections.reverse(indexesToRemove);
                    for (Integer i : indexesToRemove) {
                        modelCit.remove(i);
                        modelFpmi.remove(i);
                    }
                    indexesToRemove.clear();
                } else {
                    for (int i = 0; i < hiddenStudents.size(); i++) {
                        Pair<CheckTable, CheckTableCit> studentPair = hiddenStudents.get(i);
                        modelFpmi.addElement(studentPair.getKey());
                        modelCit.addElement(studentPair.getValue());
                    }
                    hiddenStudents.clear();
                }
            }
        });
    }

    private void addFilter() {
        setJComboBoxModels();
        JPanel jPanelFilter = new JPanel();
        jPanelFilter.setBorder(new EmptyBorder(0, 20, 0, 20));
        GroupLayout jPanelFilterLayout = new GroupLayout(jPanelFilter);
        jPanelFilter.setLayout(jPanelFilterLayout);
        jPanelFilterLayout.setHorizontalGroup(
                jPanelFilterLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, jPanelFilterLayout.createSequentialGroup()
                                .addGroup(jPanelFilterLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jComboBoxCourse, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabelCourse))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelFilterLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jComboBoxSpec, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabelSpec))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelFilterLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabelGroup)
                                        .addComponent(jComboBoxGroup, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jCheckBoxLight)
                                .addGap(18, 18, 18)
                                .addComponent(jCheckBoxHidden))
        );
        jPanelFilterLayout.setVerticalGroup(
                jPanelFilterLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelFilterLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(jPanelFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jCheckBoxHidden)
                                        .addComponent(jCheckBoxLight))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(GroupLayout.Alignment.TRAILING, jPanelFilterLayout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanelFilterLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelCourse)
                                        .addComponent(jLabelSpec)
                                        .addComponent(jLabelGroup))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelFilterLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jComboBoxCourse, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jComboBoxSpec, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jComboBoxGroup, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );
        this.add(jPanelFilter, BorderLayout.NORTH);
    }

    protected void initComponents() {
        super.initComponents();
        checkTableDAO = new DAOCheckTableImpl();
        checkTableCitDAO = new DAOCheckTableCitImpl();
    }

    private void forbidDragAndDrop() {
        this.scrollPaneCIT.getjList().setDragEnabled(false);
        this.scrollPaneCIT.getjList().setTransferHandler(null);
        this.scrollPaneFPMI.getjList().setDragEnabled(false);
        this.scrollPaneFPMI.getjList().setTransferHandler(null);
        this.scrollPaneOther.getjList().setDragEnabled(false);
        this.scrollPaneOther.getjList().setTransferHandler(null);
        this.scrollPaneFPMI.getjList().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        this.scrollPaneFPMI.getjList().setEnabled(false);
    }

    private void disableButton(JButton button) {
        button.setText("");
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
    }

    private void setUpButtonListeners(CompareFrame frame) {
        jButtonFillLists.addActionListener(e -> {
            hiddenStudents.clear();
            setCellRenderer();

            List<CheckTable> listFpmi = checkTableDAO.getAll();
            setDataToList(listFpmi, scrollPaneFPMI);

            List<CheckTableCit> listCit = checkTableCitDAO.getAll();
            setDataToList(listCit, scrollPaneOther);
            //frame.repaint();
        });

        jButtonAccordance.addActionListener(e -> {
            DefaultListModel<CheckTable> modelFpmi = null;
            DefaultListModel<CheckTableCit> modelOther = null;
            try {
                modelFpmi = (DefaultListModel<CheckTable>) scrollPaneFPMI.getjList().getModel();
                modelOther = (DefaultListModel<CheckTableCit>) scrollPaneOther.getjList().getModel();
                scrollPaneFPMI.getjList().setSelectionBackground(Color.yellow);
            } catch (ClassCastException e1) {
                JOptionPane.showMessageDialog(this, "Fill lists");
                return;
            }


            CompareCheckTables compareCheckTables = new CompareCheckTables();
            compareCheckTables.setSessionFactoryCIT(sessionFactoryCIT);
            compareCheckTables.setSessionFactoryFPMI(sessionFactoryFPMI);

            List<CheckTableCit> citList = new ArrayList<>();

            if (scrollPaneCIT.getjList().getModel().getSize() == 0) {
                for (int i = 0; i < modelFpmi.getSize(); i++) {
                    boolean find = false;
                    for (int j = 0; j < modelOther.getSize(); j++) {
                        if (compareCheckTables.compare(modelFpmi.get(i), modelOther.get(j))) {
                            citList.add(modelOther.get(j));
                            modelOther.remove(j);
                            find = true;
                        }
                    }
                    if (!find) {
                        citList.add(new CheckTableCit());
                    }
                }

                setDataToList(citList, scrollPaneCIT);
            }
            frame.repaint();
        });
    }

    private int[] listToArray(List<Integer> list) {
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}
