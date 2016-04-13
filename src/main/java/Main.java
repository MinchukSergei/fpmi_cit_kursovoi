import database.SQLServerConnector;
import frames.*;


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
                InitializeSubjectsFrame frame = new InitializeSubjectsFrame("Subjects");
                frame.setSessionFactoryFPMI(sqlServerConnectorFMPI.getSessionFactory());
                frame.setSessionFactoryCIT(sqlServerConnectorCIT.getSessionFactory());

                InitializeLessonsFrame frame1 = new InitializeLessonsFrame("Lessons");
                frame1.setSessionFactoryFPMI(sqlServerConnectorFMPI.getSessionFactory());
                frame1.setSessionFactoryCIT(sqlServerConnectorCIT.getSessionFactory());


                InitializeFacultyFrame frame2 = new InitializeFacultyFrame("Faculty");
                frame2.setSessionFactoryFPMI(sqlServerConnectorFMPI.getSessionFactory());
                frame2.setSessionFactoryCIT(sqlServerConnectorCIT.getSessionFactory());

                InitializeMarkKategFrame frame3 = new InitializeMarkKategFrame("Mark kateg");
                frame3.setSessionFactoryFPMI(sqlServerConnectorFMPI.getSessionFactory());
                frame3.setSessionFactoryCIT(sqlServerConnectorCIT.getSessionFactory());

                InitializeMarkTypeFrame frame4 = new InitializeMarkTypeFrame("Mark type");
                frame4.setSessionFactoryFPMI(sqlServerConnectorFMPI.getSessionFactory());
                frame4.setSessionFactoryCIT(sqlServerConnectorCIT.getSessionFactory());

                InitializeSpecializationFrame frame5 = new InitializeSpecializationFrame("Spec");
                frame5.setSessionFactoryFPMI(sqlServerConnectorFMPI.getSessionFactory());
                frame5.setSessionFactoryCIT(sqlServerConnectorCIT.getSessionFactory());

                InitializeStudentsFrame frame6 = new InitializeStudentsFrame("Students");
                frame6.setSessionFactoryFPMI(sqlServerConnectorFMPI.getSessionFactory());
                frame6.setSessionFactoryCIT(sqlServerConnectorCIT.getSessionFactory());
            }
        });
    }
}
