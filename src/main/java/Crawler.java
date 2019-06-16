import jdk.nashorn.internal.parser.JSONParser;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

import static org.apache.http.protocol.HTTP.USER_AGENT;


public class Crawler {

    //public static void main (String[] args){}

    private String urlLink;
    private double time;
    private Jsoup jsoup;
    private JSONParser json;
    private List<String> links;
    private Document doc;
    private int depth;

    public Timer timer = new Timer();

    public Crawler(String url)
    {
        urlLink = url;
    }


    public void getPageLink(String newLink) {
        //returns a document with the links that the scraper uses to retrieve the information of the link page
        try {
            Connection connection = Jsoup.connect(newLink).userAgent(USER_AGENT);
            Document htmlDocument = connection.get();
            this.doc = htmlDocument;

            Elements linksOnPage = htmlDocument.select("a[href]");
            System.out.println("Found (" + linksOnPage.size() + ") links");
            for(Element link : linksOnPage)
            {
                this.links.add(link.absUrl("href"));
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double getTimeElapsed() {
        //returns the time it has spent crawling
        time = timer.timeElapsed();
        return time;
    }

    public void crawl(String url) {
        //starts the crawling

        timer.start();
            getPageLink(url);
            getLinks();
        timer.stop();

        getNumberOfPages();
        getDepth();
        getTimeElapsed();
        getUniquePages();

    }

    private int getUniquePages() {
        return 0;
    }

    public int getNumberOfPages() {
        //returns the number of pages that it has crawled through
        return links.size();
    }


    public int getDepth() {
        //returns the depth value of the crawl
        return 1;
    }

    public List<String> getLinks()
    {
        return this.links;
    }
}
