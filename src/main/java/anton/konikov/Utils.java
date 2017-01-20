package anton.konikov;

import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static ReplyKeyboardMarkup generateKeyboard(List<List<String>> inputRow) {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        for (int i = 0; i < inputRow.size(); i++) {
            KeyboardRow row = new KeyboardRow();
            for (int j = 0; j < inputRow.get(i).size(); j++) {
                row.add(inputRow.get(i).get(j));
            }
            keyboard.add(row);
        }
        keyboardMarkup.setKeyboard(keyboard);

        return keyboardMarkup;
    }
}
