package commands.nonbotcom.settime;


import commands.nonbotcom.CustomCommand;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

public class ForFutureCommand extends CustomCommand {
    public ForFutureCommand() {
        super("for future", "Set remind for future in the next format:\n" +
                "yyyy-MM-dd HH:mm");
    }

    @Override
    public void execute(Update update) {
        SendMessage message = new SendMessage();
        message.setText(getDescription());
        message.setChatId(update.getMessage().getChatId());
        sendMessage(message);
    }

    @Override
    public void subCommand(Update update) throws ParseException {
        String msg = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = parser.parse(String.valueOf(msg));

        SendMessage message = new SendMessage();
        if (date.before(Calendar.getInstance().getTime())){
            message.setText("You should not set date early than current date and time: " + date);
        } else {
            message.setText("You set remind action for today: " + date);
        }

        message.setChatId(chatId);
        sendMessage(message);
    }

}
