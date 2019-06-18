import com.google.gson.JsonObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static sun.plugin.javascript.navig.JSType.URL;


public class Crawler {

    //public static void main (String[] args){}

    private String url;
    private double time;
    //private Jsoup jsoup;
    //private JSONParser json;
    private List<String> links;
    private List<JsonObject> ListJSONObject;
    private Document doc;
    private int depth = 0;
    private Set<String> uniquePages = new HashSet<String>();
    private static final int MAX_DEPTH = 50;
    private int finalDepth;;

    public Timer timer = new Timer();
    public Pointer pointer;
    public Scraper scraper;


    public Crawler(String url) {
        this.url = url;
        pointer = new Pointer(this.url);
        scraper = new Scraper();
    }


    public void crawl(String url) {
        //starts the crawling
        timer.start();
        while(this.uniquePages.size() < links.size()) {
            String currentUrl;
            if (this.links.isEmpty()) {
                currentUrl = url;
                this.uniquePages.add(url);
            } else {
                currentUrl = this.nextUrl();
            }
            addToPageLinks(currentUrl);
            this.links.addAll(getLinks());

        }
        //getUniquePages();
        //getNumberOfPages();
        //getDepth();
        timer.stop();
        //getTimeElapsed();

    }

    public boolean isConnect() {
        Connection.Response response = Jsoup.connect(url).response();
        if (response.statusCode() == 200) {
            return true;
        } else {
            throw new IllegalArgumentException("The URL link is unable to connect");
        }
    }


    //change to addtoPageLinks() so it can be used in CrawlWholeSite()
    public void addToPageLinks(String newLink) {
        //returns a document with the link that the scraper uses to retrieve the information of the link page
        if ((!links.contains(URL) && (depth < MAX_DEPTH))) {
            System.out.println(">> Depth: " + depth + " [" + URL + "]");

                Document doc = pointer.connect(url);
                Elements linksOnPage = pointer.connect(url).select("a[href]");
                //System.out.println("Found (" + linksOnPage.size() + ") links");
                depth++;
                for (Element link : linksOnPage) {
                    this.links.add(link.absUrl("href"));
                    scraper.searchContent(pointer.connect(url), doc.title());
                }


           /*
            try {
                Connection connection = Jsoup.connect(newLink).userAgent(USER_AGENT);
                Document htmlDocument = connection.get();
                this.doc = htmlDocument;

                Elements linksOnPage = htmlDocument.select("a[href]");
                //System.out.println("Found (" + linksOnPage.size() + ") links");
                depth++;
                for (Element link : linksOnPage) {
                    this.links.add(link.absUrl("href"));

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            */

        }
        finalDepth = depth;
    }

    public double getTimeElapsed() {
        //returns the time it has spent crawling
        time = timer.timeElapsed();
        return time;
    }

    public int getUniquePages() {
        return uniquePages.size();
    }

    public int getNumberOfPages() {
        //returns the number of pages that it has crawled through
        return links.size();
    }

    public int getDepth()
    {
        return finalDepth;
    }

    private String nextUrl()
    {
        String nextUrl;
        do
        {
            nextUrl = this.links.remove(0);
        } while(this.links.contains(nextUrl));
        this.uniquePages.add(nextUrl);
        return nextUrl;
    }

    public List<String> getLinks() {
        return this.links;
    }

}
