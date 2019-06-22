import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.apache.http.protocol.HTTP.USER_AGENT;

public class Crawler {
    private List<String> links = new ArrayList<>();

    private final List<String> listOfMovies = new ArrayList<>();
    private final List<String> listOfMusic = new ArrayList<>();
    private final List<String> listOfBooks = new ArrayList<>();
    private final List<String> listOfAllContents = new ArrayList<>();
    private Document doc;
    private int depth = 0;
    private Set<String> uniquePages = new HashSet<String>();
    private static final int MAX_DEPTH = 50;
    private int finalDepth;
    private Scraper scraper;
    private String url;

    public Crawler(String url) {
        this.url = url;
        scraper = new Scraper();
    }

    public void crawl(String url) {
        //returns a document with the link that the scraper uses to retrieve the information of the link page
        if ((!links.contains(url) && (depth < MAX_DEPTH))) {
            addUniquePage(url);
            Document doc = getWholeContent(url);
            Elements linksOnPage = doc.select("a[href]");
            depth = 1;
            for (Element link : linksOnPage) {
                this.links.add(url + "/" + link.attr("href"));
                addUniquePage(url+ "/" + link.attr("href"));
                depth = 2;
                if (link.attr("href").contains("books")) {
                    doc = getWholeContent(url + link.attr("href"));
                    Elements linksForItems = doc.select("ul.items li a");
                    addBookContent(linksForItems);
                }
                if (link.attr("href").contains("movies")) {

                    doc = getWholeContent(url + link.attr("href"));
                    Elements linksForItems = doc.select("ul.items li a");
                    addMoviesContent(linksForItems);
                }

                if (link.attr("href").contains("music")) {

                    doc = getWholeContent(url + link.attr("href"));
                    Elements linksForItems = doc.select("ul.items li a");
                    addMusicContent(linksForItems);
                }
            }

        }
        finalDepth = depth;
    }

    private void addMusicContent(Elements itemsElement) {

        for (Element item : itemsElement
                ) {
            String content = scraper.getContentAsString(url + "/" + item.attr("href"));
            listOfMusic.add(content);
            listOfAllContents.add(content);
        }
    }

    public List<String> getListOfMusic() {
        return listOfMusic;
    }

    private void addMoviesContent(Elements itemsElement) {

        for (Element item : itemsElement
                ) {

            String content = scraper.getContentAsString(url + "/" + item.attr("href"));
            listOfMovies.add(content);
            listOfAllContents.add(content);
        }
    }

    public List<String> getListOfMovies() {
        return listOfMovies;
    }


    private void addBookContent(Elements itemsElement) {

        for (Element item : itemsElement
                ) {

            String content = scraper.getContentAsString(url + "/" + item.attr("href"));
            listOfBooks.add(content);
            listOfAllContents.add(content);
        }
    }

    public List<String> getListOfBooks() {
        return listOfBooks;
    }

    private void addUniquePage(String url) {
        for (String uniquePage : links
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
            throw new IllegalArgumentException("Invalid URL/Connection");
        }
    }

    public Document getWholeContent(String url) {
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

//    public List<String> getListJSONObject() {
//        JsonArray jsonArray;
//        return listJSONObject;
//    }
}
