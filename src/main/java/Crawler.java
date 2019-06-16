import jdk.nashorn.internal.parser.JSONParser;
import org.jsoup.Jsoup;


public class Crawler {

    //public static void main (String[] args){}

    private String urlLink;
    private double time;
    private Jsoup jsoup;
    private JSONParser json;

    public Crawler(String url, double timeElapsed)
    {
        urlLink = url;
        time = timeElapsed;
    }

    public void connect(String url) {

    }


    public Object getWebPageContent() {
        //return document, use facebook link
        return null;
    }

    public Void OpenUrl() {
        //opens the url
        return null;
    }

    public double getTimeElapsed() {
        //returns the time it has spent crawling
        return 12.13;
    }

    public void crawl() {
        //starts the crawling
    }

    public int getNumberOfPages() {
        //returns the number of pages that it has crawled through
        return 1;
    }


    public int getDepth() {
        //returns the depth value of the crawl
        return 1;
    }
}
