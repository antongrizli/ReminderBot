package commands.nonbotcom;

import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingCommandBot;

import java.util.HashMap;
import java.util.Map;

public class FactoryCommand {
    private final Map<String, CustomCommand> customCommands;
    private final TelegramLongPollingCommandBot commandBot;

    private FactoryCommand(TelegramLongPollingCommandBot commandBot) {
        this.customCommands = new HashMap<>();
        this.commandBot = commandBot;
    }

    public void registerCommand(CustomCommand customCommand) {
        customCommand.setTelegramLongPollingCommandBot(commandBot);
        customCommands.put(customCommand.getCommandIdentifier(), customCommand);
    }

    public void executeCommand(String customCommandName, Update update) {
        if (customCommands.containsKey(customCommandName)) {
            customCommands.get(customCommandName).execute(update);
        }
    }

    public boolean isCommandRegistered(String msg) {
        return customCommands.containsKey(msg);
    }

    public CustomCommand getCommand(String msg){
        return customCommands.get(msg);
    }

    public static FactoryCommand init(TelegramLongPollingCommandBot bot) {
        return new FactoryCommand(bot);
    }
}
