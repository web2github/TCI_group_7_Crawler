import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;

import static org.apache.http.protocol.HTTP.USER_AGENT;

public class Scraper {
    private Document document;

    public boolean searchContent(final String url,final String keyword) {
        Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
        try {
            document = connection.get();
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid URL/Connection");
        }
        Element table = document.select("table").get(0);
        Elements rows = table.select("tr");
        for (int i = 0; i < rows.size(); i++) {
            if (keyword.contains(rows.select("td").get(i).text()) || keyword.contains(document.title())) {
                return true;
            }
        }
        return false;
    }
    public String getContentAsString(final String url) {
        final Connection connection = Jsoup.connect(url);
        try {
            document = connection.get();
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid URL/Connection");
        }
        Element table = document.select("table").get(0); //select the first table.
        Elements rows = table.select("tr");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        stringBuilder.append("title : " + document.title() + ",");
        for (int i = 0; i < rows.size(); i++) {
            Element th = rows.select("th").get(i);
            Element td = rows.select("td").get(i);
            stringBuilder.append(th.text() + ":" + td.text() + ",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}





