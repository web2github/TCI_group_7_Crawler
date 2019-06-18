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

    public JsonObject crawlWholeSite() throws IOException {
        timer = new Timer();
        timer.start();
        while (true) {
            crawler.crawl();
            if (crawler.getUniquePages() < crawler.getNumberOfPages()) {
                break;
            }
            timer.stop();
            time = timer.timeElapsed();
            jsonObject = new JsonParser().parse("{" + crawler.getListJSONObject().toString() + "}").getAsJsonObject();
        }
        return jsonObject;
    }

    public JsonObject crawlForAContent(String typeOfContent, String keyword) {
        if (crawler.getListJSONObject().size() != 0) {

            return crawler.getListJSONObject().get(0);
        } else {
            return jsonObject;
        }
    }

    public JsonObject getCrawlerInfo() {
        jsonObject = new JsonParser().parse("{" +
                "time_elapse : " + time
                + "unique_page : " + crawler.getUniquePages()
                + "total_page : " + crawler.getNumberOfPages()
                + "}"
        ).getAsJsonObject();
        return jsonObject;
    }

    @Test
    public void TimeElapsedIsInDouble() {
        //ARRANGE
        //ACT
        //ASSERT
//        assertEquals(Double.class, crawler.getTimeElapsed());
    }

    public Double getTimeElapsed() {
        return time;
    }
}

