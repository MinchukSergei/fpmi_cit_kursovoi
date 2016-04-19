package frames;

import dao.impl.DAOCheckTableImpl;
import entities.check.CheckTable;
import org.hibernate.SessionFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 17.04.2016.
 */
public class CheckTableFrame extends JFrame {
    private int FRAME_WIDTH = 700;
    private int FRAME_HEIGHT = 500;

    private JButton jButtonRetry;
    private JButton jButtonShowAll;
    private JButton jButtonSelectAll;
    private JScrollPane jScrollPaneCheckTable;
    private JTable jTableCheckTable;
    private List<Integer> checkTableId;

    private DAOCheckTableImpl daoCheckTable;
    protected SessionFactory sessionFactoryFPMI;
    protected SessionFactory sessionFactoryCIT;

    private boolean isSelectedAll;

    public CheckTableFrame(String title) throws HeadlessException {
        super(title);
        initComponents();
        setUpDefault();
        setUpPane();
        setUpButtonListeners(this);
    }

    private void initComponents() {
        jScrollPaneCheckTable = new JScrollPane();
        jTableCheckTable = new JTable();
        jButtonRetry = new JButton("Retry");
        jButtonShowAll = new JButton("Show all");
        jButtonSelectAll = new JButton("Select all");
        daoCheckTable = new DAOCheckTableImpl();
        checkTableId = new ArrayList<>();
        isSelectedAll = false;
    }

    private void setUpDefault() {
        this.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    public void setUpButtonListeners(CheckTableFrame frame) {
        this.jButtonShowAll.addActionListener(e -> {
            fillTable();
            frame.repaint();
        });

        this.jButtonSelectAll.addActionListener(e -> {
            DefaultTableModel model = (DefaultTableModel) jTableCheckTable.getModel();
            if (isSelectedAll)
                isSelectedAll = false;
            else
                isSelectedAll = true;
            for (int i = 0; i < model.getRowCount(); i++) {
                model.setValueAt(isSelectedAll, i, 0);
            }
        });

        this.jButtonRetry.addActionListener(e -> {
            DefaultTableModel model = (DefaultTableModel) jTableCheckTable.getModel();
            if (model != null) {
                for (int i = 0; i < model.getRowCount(); i++) {
                    if ((Boolean) model.getValueAt(i, 0))
                        daoCheckTable.retrySetMark(checkTableId.get(i));
                }
                fillTable();
                frame.repaint();
            }
            //checkTableId.forEach(System.out::println);
        });
    }

    private void fillTable() {
        Object[] titleNames = {"Select", "№ markbook", "Name", "Surname", "Course", "Subject", "Mark", "Message"};

        List<CheckTable> checkTable = daoCheckTable.getAll();
        Object[][] data = new Object[checkTable.size()][titleNames.length];

        int i = 0;
        checkTableId.clear();
        for (CheckTable t : checkTable) {
            data[i] = setTableRow(t, titleNames.length);
            checkTableId.add(t.getId());
            i++;
        }
        DefaultTableModel model = new DefaultTableModel(data, titleNames);
        jTableCheckTable = new JTable(model) {
            private static final long serialVersionUID = 1L;

            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return Boolean.class;
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                        return String.class;
                    default:
                        return String.class;
                }
            }
        };
        jTableCheckTable.setPreferredScrollableViewportSize(jTableCheckTable.getPreferredSize());
        jScrollPaneCheckTable.setViewportView(jTableCheckTable);
    }

    private Object[] setTableRow(CheckTable table, int titleLength) {
        Object[] tableRow = new Object[titleLength];
        int i = 0;
        tableRow[i++] = Boolean.FALSE;
        tableRow[i++] = table.getNz().trim();
        tableRow[i++] = table.getImrus().trim();
        tableRow[i++] = table.getFamrus().trim();
        tableRow[i++] = String.valueOf(table.getCourse()).trim();
        tableRow[i++] = daoCheckTable.getSubjectById(table.getSubject()).getSubjectName().trim();
        tableRow[i++] = daoCheckTable.getMarkById(table.getMark()).getMarkTypeName().trim();
        tableRow[i++] = table.getMessage().trim();
        return tableRow;
    }

    private void setUpPane() {

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(jScrollPaneCheckTable, GroupLayout.DEFAULT_SIZE, 698, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(jButtonSelectAll)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButtonShowAll)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jButtonRetry)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPaneCheckTable, GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButtonRetry)
                                        .addComponent(jButtonShowAll)
                                        .addComponent(jButtonSelectAll))
                                .addContainerGap())
        );

        pack();
    }

    public SessionFactory getSessionFactoryFPMI() {
        return sessionFactoryFPMI;
    }

    public void setSessionFactoryFPMI(SessionFactory sessionFactoryFPMI) {
        this.sessionFactoryFPMI = sessionFactoryFPMI;
        daoCheckTable.setSessionFactory(this.sessionFactoryFPMI);
    }

    public SessionFactory getSessionFactoryCIT() {
        return sessionFactoryCIT;
    }

    public void setSessionFactoryCIT(SessionFactory sessionFactoryCIT) {
        this.sessionFactoryCIT = sessionFactoryCIT;
    }
}
