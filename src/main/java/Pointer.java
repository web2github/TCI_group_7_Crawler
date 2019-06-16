import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Pointer {
    private Crawler crawler;
    private Scraper scraper;
    private String url;
    JsonArray jsonArray;


    public Pointer(String url) {
        this.url = url;
        crawler = new Crawler(url);
        scraper = new Scraper();
        jsonArray = new JsonArray();
    }

    public JsonObject crawlWholeSite() {
        crawler.getPageLink(url + "/catalog.php");
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
        return new JsonObject();
    }
}
