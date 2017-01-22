package anton.konikov.database;


public interface RemindDAO {
    int insertRemindData(Remind remind);
    boolean deleteRemindData(Long chatId);
    Remind findRemindData(Long chatId);
    void init();
}
