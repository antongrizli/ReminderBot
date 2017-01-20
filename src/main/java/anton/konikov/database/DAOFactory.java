package anton.konikov.database;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class DAOFactory {
    public static final int H2DB = 1;
    public static final int MYSQL = 2;

    public abstract RemindDAO getRemindDAO();

    public static DAOFactory getDAOFactory(int whichFactory) {
        switch (whichFactory) {
            case H2DB:
                return new H2dbDAOFactory();
            case MYSQL:
                return null;
            default:
                return null;
        }
    }

    public static void printConnectInfo(Connection connection) {
        try {
            System.out.println("DB name: " + connection.getMetaData().getDatabaseProductName());
            System.out.println("DB version: " + connection.getMetaData().getDatabaseProductVersion());
            System.out.println("Driver: " + connection.getMetaData().getDriverName());
            System.out.println("Autocommit: " + connection.getAutoCommit());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
