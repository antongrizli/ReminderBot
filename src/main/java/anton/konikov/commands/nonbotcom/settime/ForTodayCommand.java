package anton.konikov.commands.nonbotcom.settime;

import anton.konikov.commands.nonbotcom.CustomCommand;
import anton.konikov.database.Remind;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ForTodayCommand extends CustomCommand {
    public ForTodayCommand() {
        super("for today", "Set remind for today in the next format:\n" +
                "HH:mm");
    }

    @Override
    public void execute(Update update) {
        SendMessage message = new SendMessage();
        message.setText(getDescription());
        message.setChatId(update.getMessage().getChatId());
        sendMessage(message);
    }

    @Override
    public void subCommand(Update update) throws Exception {
        Remind remind = new Remind();
        String msg = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();
        SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
        Date date = parser.parse(msg);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY));
        currentCalendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE));

        SendMessage message = new SendMessage();
        message.setText("You set remind action for today: " + currentCalendar.getTime());
        message.setChatId(chatId);
        sendMessage(message);

        remind.setRemindDate(new java.sql.Date(currentCalendar.getTimeInMillis()));
        remind.setChatId(chatId);
        remind.setRemindMessage(message.getText());
        remind.setUserId(update.getMessage().getFrom().getId());
       // remindDAO.insertRemindData(remind);
    }
}
