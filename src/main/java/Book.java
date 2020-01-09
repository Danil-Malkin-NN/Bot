import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;

public class Book {
    private Document document;

    public Book(){
        connect();
    }

    private void connect (){
        try{
            document = (Document) Jsoup.connect("https://www.surgebook.com/Valentine_Belyakov/book/chas-chervey").get();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public String getTitle(){return document.title(); }

    public String getLikes() {
        Element element = document.getElementById("likes");
        return element.text();
    }

    public String getDescription(){
        Elements elements = document.getElementsByClass("genres d-block");
        return elements.text();

    }

    public String getGenres(){
        Elements elements = document.getElementsByClass("genres d-block");
        return elements.text();
    }

    public String getCommentList(){
        Elements elements = document.getElementsByClass("comment_mv1_item");

        String coment = elements.text();
        coment = coment.replaceAll("Ответить", "\n\n");
        coment = coment.replaceAll("Нравится", "");
        coment = coment.replaceAll("\\d{4}-\\d{2}-\\d{2}", "");
        coment = coment.replaceAll("\\d{2}:\\d{2}:\\d{2}", "");
        return coment;
    }

    public String getImg(){
        Elements elements = document.getElementsByClass("cover-book");
        String url = elements.attr("style");
        url = url.replace("background-image: url('", "");
        url = url.replace("');", "");
        return url;
    }

    public String getAuthorName(){
        Elements elements = document.getElementsByClass("text-decoration-none column-author-name bold max-w-140 text-overflow-ellipsis");
        return  elements.text();
    }





}
