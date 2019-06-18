import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Pointer {
    private Crawler crawler;
    private Scraper scraper;
    private String url;
    JsonArray jsonArray;
    JsonArray jsonInfoArray;


    public Pointer(String url) {
        this.url = url;
        crawler = new Crawler(url);
        scraper = new Scraper();
        jsonArray = new JsonArray();
        jsonInfoArray = new JsonArray();
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
        String depth = gson.toJson(crawler.getDepth());
        String unique = gson.toJson(crawler.getUniquePages());
        String number = gson.toJson(crawler.getNumberOfPages());
        String time =  gson.toJson(crawler.getTimeElapsed());

        JsonObject jsonDepth = new JsonParser().parse(String.valueOf(depth)).getAsJsonObject();
        JsonObject jsonUnique = new JsonParser().parse(String.valueOf(unique)).getAsJsonObject();
        JsonObject jsonNumber = new JsonParser().parse(String.valueOf(number)).getAsJsonObject();
        JsonObject jsonTime = new JsonParser().parse(String.valueOf(time)).getAsJsonObject();

        jsonInfoArray.add(jsonDepth);
        jsonInfoArray.add(jsonUnique);
        jsonInfoArray.add(jsonNumber);
        jsonInfoArray.add(jsonTime);

        JsonObject jsonObject = new JsonParser().parse(String.valueOf(jsonInfoArray)).getAsJsonObject();
        return jsonObject;
    }
}

