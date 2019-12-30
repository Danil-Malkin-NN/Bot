import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {


    public void onUpdateReceived(Update update) {

        update.getUpdateId();
        SendMessage sendMessage = new SendMessage().setChatId(update.getMessage().getChatId());
        if(update.getMessage().getText().equals("Привет")){

            sendMessage.setText("Привет друг");

            try{
                execute(sendMessage);

            }catch (TelegramApiException e){
                e.printStackTrace();
            }
        }

    }


    public String getBotUsername() {

        return "FixProblem_bot";
    }


    public String getBotToken() {
        return "1031029099:AAH7zzhKyngqgN9sy4AO14Sk7L7yYocv-p4";
    }
}
