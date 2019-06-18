import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.apache.http.protocol.HTTP.USER_AGENT;


public class Crawler {

    //public static void main (String[] args){}
    //private Jsoup jsoup;
    //private JSONParser json;
    private List<String> links = new ArrayList<>();
    private List<JsonObject> listJSONObject = new ArrayList<>();
    private Document doc;
    private int depth = 0;
    private Set<String> uniquePages = new HashSet<String>();
    private static final int MAX_DEPTH = 50;
    private int finalDepth;

    public Scraper scraper;
    private String url;


    public Crawler(String url) {
        this.url = url;
        scraper = new Scraper();
    }

    public boolean crawl() {
        //returns a document with the link that the scraper uses to retrieve the information of the link page
        if ((!links.contains(url) && (depth < MAX_DEPTH))) {
            addUniquePage(url);
            System.out.println(">> Depth: " + depth + " [" + url + "]");
            Document doc = getWholeContent();
            Elements linksOnPage = getWholeContent().select("a[href]");
            depth++;
            for (Element link : linksOnPage) {
                this.links.add(link.absUrl("href"));
                listJSONObject.add(
                        scraper.searchContent(doc, doc.title()));
            }
        }
        finalDepth = depth;
        return true;
    }

    private void addUniquePage(String url) {
        //starts the crawling
        for (String uniquePage : uniquePages
                ) {
            if (!uniquePage.equals(url)) {
                this.uniquePages.add(url);
            }
        }

    }

    public boolean isConnect() {
        Connection.Response response = Jsoup.connect(url).response();
        if (response.statusCode() == 200) {
            return true;
        } else {
            throw new IllegalArgumentException("The URL link is unable to connect");
        }
    }

    public Document getWholeContent() {
        try {
            Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
            Document htmlDocument = connection.get();
            this.doc = htmlDocument;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }


    public int getUniquePages() {
        return uniquePages.size();
    }

    //returns the number of pages that it has crawled through
    public int getNumberOfPages() {
        return links.size();
    }

    public int getDepth() {
        return finalDepth;
    }

    private String nextUrl() {
        String nextUrl;
        do {
            nextUrl = this.links.remove(0);
        } while (this.links.contains(nextUrl));
        this.uniquePages.add(nextUrl);
        return nextUrl;
    }

    public List<String> getLinks() {
        return this.links;
    }

    public List<JsonObject> getListJSONObject() {
        JsonArray jsonArray;
        return listJSONObject;
    }
}
