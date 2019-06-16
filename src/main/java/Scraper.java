import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class Scraper {

    public String getContentAsString(Document doc) {

//        Document doc = Jsoup.connect("https://www.codetriage.com/?language=Java").get();
        // With the document fetched, we use JSoup's title() method to fetch the title
        System.out.printf("Title: %s\n", doc.title());
        // Get the list of repositories
        Elements elements = doc.getElementsByClass("media-details");
        Element table = doc.select("table").get(0); //select the first table.
        Elements rows = table.select("tr");
        for (int i = 0; i < rows.size(); i++) { //first row is the col names so skip it.
            Element th = rows.select("th").get(i);
            System.out.println(th.text()); // print th value
            if (th.is("Category")) {
                Element col = rows.select("td").get(i);
                //Books books = new Books("").setCategory(col.toString());
            }
            if (th.is("Genre")) {
                Element col = rows.select("td").get(i);
            }
            if (th.is("Format")) {
                Element col = rows.select("td").get(i);
            }

            if (th.is("Authors")) {
                Element col = rows.select("td").get(i);
            }

            if (th.is("Publisher")) {
                Element col = rows.select("td").get(i);
            }
            if (th.is("ISBN")) {
                Element col = rows.select("td").get(i);
            }
        }
        return "";
    }
}





