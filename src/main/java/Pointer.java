import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

import static org.apache.http.protocol.HTTP.USER_AGENT;

public class Pointer {
    private Crawler crawler;
    private Scraper scraper;
    private String url;
    JsonArray jsonArray;
    JsonArray jsonInfoArray;
    private Document doc;


    public Pointer(String url) {
        this.url = url;
        crawler = new Crawler(url);
        scraper = new Scraper();
        jsonArray = new JsonArray();
        jsonInfoArray = new JsonArray();
    }

    public Document connect(String url)
    {
        try {
            Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
            Document htmlDocument = connection.get();
            this.doc = htmlDocument;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }

    public JsonObject crawlWholeSite() {


        crawler.addToPageLinks(url + "/catalog.php");
        for (int i = 0; i < crawler.getLinks().size(); i++) {
//            scraper.setContent((url + "/" + crawler.getLinks().get(i)));
//            scraper.getContent();
//            jsonArray.add(scraper.getContent());
        }
        JsonObject jsonObject = new JsonParser().parse(String.valueOf(jsonArray)).getAsJsonObject();
        return jsonObject;
    }

    public JsonObject crawlForAContent(String typeOfContent, String keyword) {
        return new JsonObject();
    }

    public JsonObject getCrawlerInfo() {
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();

        //JsonObject jsonObject = new JsonParser().parse(String.valueOf(jsonInfoArray)).getAsJsonObject();
        return jsonObject;
    }
}

