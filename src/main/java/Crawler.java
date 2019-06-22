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
    private List<String> links = new ArrayList<>();
    private Set uniquePages = new HashSet<String>();
    private final List<String> listOfAllContents = new ArrayList<>();
    private Document doc;
    private int depth = 0;
    private static final int MAX_DEPTH = 50;
    private int finalDepth;
    private Scraper scraper;
    private String url;

    public Crawler(String url) {
        this.url = url;
        scraper = new Scraper();
    }

    public String crawl(final String url, final String keyword) {
        links = new ArrayList<>();
        uniquePages = new HashSet<String>();
        Connection connection = Jsoup.connect(url);
        Document doc = null;
        try {
            doc = connection.get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return navigate(doc, keyword);
    }

    private String navigate(Document document, final String search) {
        String results = "";
        Elements navigationBar = document.select("ul.nav li a");
        for (Element nav :
                navigationBar) {
            depth = 1;
            addLinkPage(url + nav.attr("href"));
            document = getWholeContent(url + nav.attr("href"));
            Elements items = document.select("ul.items li a");
            if (search.equals("")) {
                addContent(items);
                depth = 2;
            } else {
                depth = 2;
                results = lookIntoItems(items, search);
                if (!results.equals("")) {

                    break;
                }
            }
            addLinkPage(url + nav.attr("href"));
        }
        return results;
    }

    private String lookIntoItems(final Elements items, final String keyword) {
        String detailLink;
        String results = "";
        depth = 2;
        for (Element itemlink : items) {
            detailLink = url + "/" + itemlink.attr("href");
            addLinkPage(detailLink);
            if (scraper.searchContent(detailLink, keyword)) {
                results = scraper.getContentAsString(detailLink);
                break;
            }
        }
        return results;
    }

    private void addLinkPage(final String url) {
        System.out.println(url);
        this.links.add(url);
        uniquePages.addAll(links);
    }

    private Document getWholeContent(final String url) {
        try {
            Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
            Document htmlDocument = connection.get();
            this.doc = htmlDocument;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }

    private void addContent(final Elements itemsElement) {
        depth = 2;
        for (Element item : itemsElement
                ) {
            addLinkPage(url + "/" + item.attr("href"));
            String content = scraper.getContentAsString(url + "/" + item.attr("href"));
            System.out.println(content);
            listOfAllContents.add(content);
        }
    }

    public List<String> getContents() {
        return this.listOfAllContents;
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

    public boolean isConnect() {
        Connection.Response response = Jsoup.connect(url).response();
        if (response.statusCode() == 200) {
            return true;
        } else {
            throw new IllegalArgumentException("Invalid URL/Connection");
        }
    }

}
