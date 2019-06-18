import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Scraper {
    //use Crawler to call ListJSONObject.add(Scraper.getContentAsString(getHrefElement()))
    public JsonObject searchContent(Document doc, String keyword) {
        JsonObject jsonObject = new JsonObject();
        Element table = doc.select("table").get(0);
        Elements rows = table.select("tr");
        for (int i = 0; i < rows.size(); i++) {
            if (keyword.equals(rows.select("td").get(i)) || keyword.equals(doc.title())) {
                jsonObject = new JsonParser().parse(getContentAsString(doc)).getAsJsonObject();
                break;
            }
        }
        return jsonObject;
    }

    public String getContentAsString(Document doc) {

        // With the document fetched, we use JSoup's title() method to fetch the title
        System.out.printf("Title: %s\n", doc.title());
        // Get the list of repositories
        Elements elements = doc.getElementsByClass("media-details");
        Element table = doc.select("table").get(0); //select the first table.
        Elements rows = table.select("tr");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        for (int i = 0; i < rows.size(); i++) {
            Element th = rows.select("th").get(i);
            Element td = rows.select("td").get(i);
            System.out.println(th.text()); // print th value
            System.out.println(td.text()); // print td value
            stringBuilder.append(th.text() + ":" + td.text());
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}





