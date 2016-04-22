package frames;

import dao.impl.DAOCheckTableCitImpl;
import dao.impl.DAOCheckTableImpl;
import entities.check.CheckTable;
import entities.check.CheckTableCit;
import entities.check.CompareCheckTables;
import entities.faculties.ClFacultetCIT;
import entities.mark_type.ClMarkTypeCIT;
import entities.subjects.ClSubjectCIT;
import frames.frame_objects.renderers.CheckTableCitRenderer;
import frames.frame_objects.renderers.CheckTableRenderer;
import org.hibernate.SessionFactory;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 21.04.2016.
 */
public class CompareFrame extends InitFrame {
    private DAOCheckTableImpl checkTableDAO;
    private DAOCheckTableCitImpl checkTableCitDAO;

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

    }

    private void disableButton(JButton button) {
        button.setText("");
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
    }

    private void setUpButtonListeners(CompareFrame frame) {
        jButtonFillLists.addActionListener(e -> {
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
}
