package anton.konikov.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2dbDAOFactory extends DAOFactory {
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:file:C:\\JavaProjects\\ReminderBot\\ReminderBotDB.mv.db.h2.db;AUTO_SERVER=TRUE";
    private static final String DB_USER = "konikov";
    private static final String DB_PASSWORD = "P@ssw0rd";

    public static Connection createConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            printConnectInfo(dbConnection);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return dbConnection;
    }


    @Override
    public RemindDAO getRemindDAO() {
        return new H2dbRemindDAO();
    }
}
