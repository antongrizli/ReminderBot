import commands.HelpCommand;
import commands.RemindCommand;
import commands.nonbotcom.FactoryCommand;
import commands.nonbotcom.settime.ForFutureCommand;
import commands.nonbotcom.settime.ForTodayCommand;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import services.Emoji;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReminderHandler extends TelegramLongPollingCommandBot {
    private Map<Long, List<String>> commandMap = new HashMap();
    private FactoryCommand factoryCustomCommand;

    public ReminderHandler() {
        factoryCustomCommand = FactoryCommand.init(this);
        factoryCustomCommand.registerCommand(new ForTodayCommand());
        factoryCustomCommand.registerCommand(new ForFutureCommand());

        HelpCommand helpCommand = new HelpCommand(this);
        register(helpCommand);
        register(new RemindCommand());
        registerDefaultAction((absSender, message) -> {
            SendMessage commandUnknownMessage = new SendMessage();
            commandUnknownMessage.setChatId(message.getChatId());
            commandUnknownMessage.setText("The command '" + message.getText() + "' is not known by this bot. Here comes some help " + Emoji.AMBULANCE);
            try {
                absSender.sendMessage(commandUnknownMessage);
            } catch (TelegramApiException e) {
            }
            helpCommand.execute(absSender, message.getFrom(), message.getChat(), new String[]{});
        });
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        Long chatId = update.getMessage().getChatId();
        String msgText = update.getMessage().getText();
        SendMessage message = new SendMessage();
        if (msgText != null) {
            addElement(update);
            if (factoryCustomCommand.isCommandRegistered(msgText)) {
                if (getSizeRemindCommand(update) >= 1)
                    factoryCustomCommand.executeCommand(msgText, update);
            } else {
                List<String> list = commandMap.get(chatId);
                String lastCommand = list.get(list.size() - 2);
                if (factoryCustomCommand.isCommandRegistered(lastCommand)) {
                    try {
                        factoryCustomCommand.getCommand(lastCommand).subCommand(update);
                    } catch (Exception e) {
                        message.setText(e.getMessage());
                    }
                }
            }
        }

        message.setChatId(chatId);
        try {
            sendMessage(message);
        } catch (TelegramApiException e) {

        }

    }

    private void addElement(Update update) {
        Long chatId = update.getMessage().getChatId();
        String msgText = update.getMessage().getText();
        if (commandMap.containsKey(chatId)) {
            List<String> inputMsg = new ArrayList<>();
            inputMsg.addAll(commandMap.get(chatId));
            inputMsg.add(msgText);
            commandMap.replace(chatId, inputMsg);
        } else {
            List<String> inputMsg = new ArrayList<>();
            inputMsg.add(msgText);
            commandMap.put(chatId, inputMsg);
        }
    }

    private void removeElement(Update update) {
        Long chatId = update.getMessage().getChatId();
        commandMap.remove(chatId);
    }

    private int getSizeRemindCommand(Update update) {
        Long chatId = update.getMessage().getChatId();
        String msgText = update.getMessage().getText();
        int count = 0;
        if (commandMap.containsKey(chatId)) {
            List<String> list = commandMap.get(chatId);
            for (String msg : list) {
                if (msg.contentEquals(msgText)) {
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public String getBotUsername() {
        return BotConfig.REMINDER_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BotConfig.REMINDER_TOKEN;
    }
}
