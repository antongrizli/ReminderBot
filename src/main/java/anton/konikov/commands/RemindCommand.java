package anton.konikov.commands;

import anton.konikov.Utils;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.bots.commands.BotCommand;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class RemindCommand extends BotCommand {
    Utils utils = new Utils();
    public RemindCommand() {
        super("remind", "Set remind for you");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        SendMessage answer = new SendMessage();
        StringBuilder messageBuilder = new StringBuilder();

        String userName = user.getFirstName() + " " + user.getLastName();

        messageBuilder.append("Hi ").append(userName).append("\n");
        messageBuilder.append("Please select day, on which you want create reminder: ");

        List<List<String>> rowKeyboard = new ArrayList<>();
        List<String> lineKeyboard = new ArrayList<>();
        ReplyKeyboardMarkup keyboardMarkup;
        lineKeyboard.add("for today");
        lineKeyboard.add("for future");
        rowKeyboard.add(lineKeyboard);
        keyboardMarkup = utils.generateKeyboard(rowKeyboard);
        answer.setReplyMarkup(keyboardMarkup);

        answer.setChatId(chat.getId().toString());
        answer.setText(messageBuilder.toString());

        try {
            absSender.sendMessage(answer);
        } catch (TelegramApiException e) {

        }
    }
}
