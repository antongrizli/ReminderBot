package anton.konikov.database;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class H2dbRemindDAO implements RemindDAO {
    private static final String CREATE_USER = "CREATE USER IF NOT EXISTS konikov PASSWORD 'P@ssw0rd'";
    private static final String CREATE_REMIND_TABLE = "CREATE TABLE IF NOT EXISTS RemindTable";
    private static final String ALTER_USER_ADMIN = "CREATE TABLE IF NOT EXISTS RemindTable";

    private Connection connection;

    public H2dbRemindDAO() {
        this.connection = H2dbDAOFactory.createConnection();

    }

    @Override
    public int insertRemindData() {
        return 0;
    }

    @Override
    public boolean deleteRemindData(Long chatId) {
        return false;
    }

    @Override
    public Remind findRemindData(Long chatId) {
        return null;
    }

    @Override
    public void init() {
        try {
            Statement statement = connection.createStatement();
            int resultSet1 = statement.executeUpdate(CREATE_USER);
            //int resultSet2 = statement.executeUpdate(CREATE_REMIND_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
