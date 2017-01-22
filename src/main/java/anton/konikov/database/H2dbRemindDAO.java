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
