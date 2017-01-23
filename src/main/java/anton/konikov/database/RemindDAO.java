package anton.konikov.database;


import java.sql.Connection;

public interface RemindDAO {
    int insertRemindData(Remind remind);
    int addNewUser();
    boolean deleteRemindData(Long chatId);
    Remind findRemindData(Long chatId);
    void init();
}
