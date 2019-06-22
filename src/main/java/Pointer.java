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

    private Timer timer = new Timer();
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
        timer.start();
        crawler.crawl(url, "");
        timer.stop();
        time = timer.timeElapsed();
        return jsonObject;
    }

    public String crawlForAContent(final String keyword) {

        timer.start();
        crawler.crawl(url, keyword);
        timer.stop();
        time = timer.timeElapsed();
        return crawler.crawl(url, keyword);

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

