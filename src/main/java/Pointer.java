import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.IOException;

import static org.apache.http.protocol.HTTP.USER_AGENT;

public class Pointer {
    private Crawler crawler;
    private Scraper scraper;
    //returns the time it has spent crawling

    private Timer timer;
    private double time;
    private JsonObject jsonObject = new JsonParser().parse("{}").getAsJsonObject();
    private String url;
    JsonArray jsonInfoArray;
    private Document doc;


    public Pointer(String url) {
        this.url = url;
        crawler = new Crawler(url);
        jsonInfoArray = new JsonArray();
    }

    public JsonObject crawlWholeSite() {
        timer = new Timer();
        timer.start();
        crawler.crawl(url);
        timer.stop();
        time = timer.timeElapsed();
        return jsonObject;
    }

    public String crawlForAContent(final String typeOfContent, final String keyword) throws IOException {
        crawlWholeSite();
        if (typeOfContent.contains("books")) {
            for (String str :
                    crawler.getListOfBooks()) {
                if (str.contains(keyword)) {
                    return str;
                }
            }
        }
        if (typeOfContent.contains("movies")) {
            for (String str :
                    crawler.getListOfMovies()) {
                if (str.contains(keyword)) {
                    return str;
                }
            }
        }
        if (typeOfContent.contains("music")) {
            for (String str :
                    crawler.getListOfMusic()) {
                if (str.contains(keyword)) {
                    return str;
                }
            }
        }
        return "";
    }

    public JsonObject getCrawlerInfo() {
        jsonObject = new JsonParser().parse("{" +
                "time_elapse : " + time
                + ",unique_page : " + crawler.getUniquePages()
                + ",total_page : " + crawler.getNumberOfPages()
                + "}"
        ).getAsJsonObject();
        System.out.println(jsonObject.toString());
        return jsonObject;
    }

    public Double getTimeElapsed() {
        return time;
    }
}

