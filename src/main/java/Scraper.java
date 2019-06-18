import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Scraper {
    //use Crawler to call ListJSONObject.add(Scraper.getContentAsString(getHrefElement()))
    public JsonObject searchContent(Document doc, String keyword) {
        JsonObject jsonObject = new JsonObject();
        Element table = doc.select("table").get(0);
        Elements rows = table.select("tr");
        for (int i = 0; i < rows.size(); i++) {
            if (keyword.equals(rows.select("td").get(i)) || keyword.equals(doc.title())) {
//                jsonObject = getContentAsString(doc);
                break;
            }
        }
        return jsonObject;
    }

    public String getContentAsString(String url ) throws IOException {
        Connection connection = Jsoup.connect(url);
        Document doc = connection.get();
        Elements elements = doc.getElementsByClass("media-details");
        Element table = doc.select("table").get(0); //select the first table.
        Elements rows = table.select("tr");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        stringBuilder.append("title : "+ doc.title()+ ",");
        for (int i = 0; i < rows.size(); i++) {
            Element th = rows.select("th").get(i);
            Element td = rows.select("td").get(i);
            stringBuilder.append(th.text() + ":" + td.text() + ",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        stringBuilder.append("}");
        System.out.println(stringBuilder);
        return stringBuilder.toString();
    }
}





