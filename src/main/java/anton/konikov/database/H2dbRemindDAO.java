package anton.konikov.database;


import java.sql.*;

public class H2dbRemindDAO implements RemindDAO {

    private Connection connection;

    public H2dbRemindDAO() {
        this.connection = H2dbDAOFactory.createConnection();

    }

    @Override
    public int insertRemindData(Remind remind) {
        try {
            String sql = "INSERT INTO REMIND_INFO(CHAT_ID, USER_ID, REMIND_DATE, REMIND_MESSAGE) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, 12345l);
            statement.setLong(2, 4321l);
            statement.setDate(3, new Date(1l));
            Clob message = connection.createClob();
            message.setString(1, "message");
            statement.setClob(4, message);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int addNewUser() {
        try {
            String sql = "INSERT INTO USERDETAILS(USER_ID, FIRST_NAME, LAST_NAME, MOB_NUMBER) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, 12345);
            statement.setString(2, "Anton");
            statement.setString(3, "Konikov");
            statement.setString(4, "+380953900921");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
    }
}
