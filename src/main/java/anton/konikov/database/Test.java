package anton.konikov.database;

public class Test {
    public static void main(String[] args) {
        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.H2DB);
        RemindDAO remindDAO = factory.getRemindDAO();
    }
}
