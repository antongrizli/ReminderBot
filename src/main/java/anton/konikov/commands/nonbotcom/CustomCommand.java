package anton.konikov.commands.nonbotcom;

import anton.konikov.database.DAOFactory;
import anton.konikov.database.RemindDAO;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public abstract class CustomCommand {
    private final String commandIdentifier;
    private final String description;
    private TelegramLongPollingCommandBot commandBot;
    private DAOFactory factory = null;
    protected RemindDAO remindDAO = null;


    public CustomCommand(String commandIdentifier, String description) {
        this.commandIdentifier = commandIdentifier.toLowerCase();
        this.description = description;
        this.factory = DAOFactory.getDAOFactory(DAOFactory.H2DB);
        this.remindDAO = factory.getRemindDAO();
    }

    public final String getCommandIdentifier() {
        return this.commandIdentifier;
    }

    public final String getDescription() {
        return this.description;
    }

    public String toString() {
        return "<b>/" + this.getCommandIdentifier() + "</b>\n" + this.getDescription();
    }

    public final void setTelegramLongPollingCommandBot(TelegramLongPollingCommandBot commandBot){
        this.commandBot = commandBot;
    }

    public void sendMessage(SendMessage message){
        try {
            commandBot.sendMessage(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public abstract void execute(Update update);

    public abstract void subCommand(Update update) throws Exception;
}
