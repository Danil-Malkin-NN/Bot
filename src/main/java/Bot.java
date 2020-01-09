import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.print.DocFlavor;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Bot extends TelegramLongPollingBot {
    Book book = new Book();
    private long chat_id;


    public void onUpdateReceived(Update update) {

        update.getUpdateId();
        SendMessage sendMessage = new SendMessage().setChatId(update.getMessage().getChatId());
        chat_id = update.getMessage().getChatId();
        sendMessage.setText(input(update.getMessage().getText()));


        try {
            execute(sendMessage);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }


    }


    public String getBotUsername() { return "FixProblem_bot"; }


    public String getBotToken() { return "1031029099:AAH7zzhKyngqgN9sy4AO14Sk7L7yYocv-p4"; }

    public String input (String msg){
        if (msg.contains("Hi") || msg.contains("Hello") || msg.contains("Привет")){
            return "Добрый день! ";
        }
        if(msg.contains("Информация о книге")){
            return getInfoBook();
        }
        if(msg.contains("/person")){
            msg = msg.replace("/person ", "");
            return getInfoPerson(msg);
        }
        return "Ээээ, не понял";
    }
    public String getInfoBook(){
        SendPhoto sendPhotoRequest = new SendPhoto();

        try(InputStream in = new URL(book.getImg()).openStream()){
            Files.copy(in, Paths.get("F:\\img"));
            sendPhotoRequest.setChatId(chat_id);
            sendPhotoRequest.setPhoto(new File("F:\\img"));
            execute(sendPhotoRequest);
            Files.delete(Paths.get("F:\\img"));


        } catch (IOException e) {
            System.out.println("File not found");
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        String info = book.getTitle()
                + "\nАвтор " + book.getAuthorName()
                + "\nЖанр " + book.getGenres()
                + "\n\nОписание " + book.getDescription()
                + "\n\nКоличество лайков " + book.getLikes()
                + "\n\nПоследние коментарии\n" + book.getCommentList();
        return info;


    }

    public String getInfoPerson(String msg){
        Autor aut = new Autor(msg);

        SendPhoto sendPhotoRequest = new SendPhoto();
        try(InputStream in = new URL(aut.getImg()).openStream()){
            Files.copy(in, Paths.get("F:\\img"));
            sendPhotoRequest.setChatId(chat_id);
            sendPhotoRequest.setPhoto(new File("F:\\img"));
            execute(sendPhotoRequest);
            Files.delete(Paths.get("F:\\img"));

        }catch (TelegramApiException e){
            e.printStackTrace();
        }catch (IOException  ex) {
            System.out.println("File not found");
        }


        return aut.getInfoPerson();
    }



}
