import dao.impl.DAOClSubjectCITImpl;
import database.SQLServerConnector;
import frames.InitializeFrame;

import java.util.List;


public class Main {

    public static void main(String[] args) {
        SQLServerConnector connector = new SQLServerConnector();
        connector.setUp("hibernateCIT.cfg.xml");
        SQLServerConnector sqlServerConnectorFMPI = new SQLServerConnector();
        SQLServerConnector sqlServerConnectorCIT = new SQLServerConnector();

        sqlServerConnectorFMPI.setUp("hibernateFPMI.cfg.xml");
        sqlServerConnectorCIT.setUp("hibernateCIT.cfg.xml");

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                InitializeFrame frame = new InitializeFrame();
                frame.setLabelTitle("Subjects");
                frame.setSessionFactoryFPMI(sqlServerConnectorFMPI.getSessionFactory());
                frame.setSessionFactoryCIT(sqlServerConnectorCIT.getSessionFactory());
            }
        });
    }
}
