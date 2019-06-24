
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;


import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ScraperTest {
    private String URL_FOR_TITLE;
    private String TYPE_OF_CONTENT;
    private String CONTEXT_BOOK;
    private String CONTEXT_TITLE;
    private Scraper mockedScraper;

    private JsonParser jsonParser;

    @Before
    public void setUp() {
        mockedScraper = mock(Scraper.class);
        jsonParser = new JsonParser();
        TYPE_OF_CONTENT = "";
        CONTEXT_BOOK = "The Clean Coder: A Code of Conduct for Professional Programmers";
        //URL_FOR_TITLE = "http://localhost:8082/sample_site_to_crawl/details.php?id=302";
        URL_FOR_TITLE = "http://192.168.64.2/sample/details.php?id=302";
        CONTEXT_TITLE = "Elvis Forever";
    }

    //CONTENTS OF SCRAPER CLASS, CHANGE NAME

    /* 1.get content from Scraper.class
     *  2.search through content
     *  3.return results to the user
     * */

    @Test
    public void shouldReturnEmptyStringWhenCrawlResultsIsEmpty() {
        final Document document = new Document("");
        when(mockedScraper.getContentAsString("")).thenReturn("");
    }

    @Test
    public void shouldReturnTrueWhenContentIsFound() {
        //ACT
        when(mockedScraper.searchContent(URL_FOR_TITLE, CONTEXT_TITLE)).thenReturn(true);
        //ASSERT
    }

    @Test
    public void assertThatBookContentFromScraperIsEqualToBookContentWhenCreate() {
        // arrange
        final JsonObject book = ContentFactory.createContent(TYPE_OF_CONTENT, CONTEXT_BOOK);
        // act
        // assert
        assertEquals("", book.getAsString());
    }

    @Test
    public void assertThatMovieContentFromScraperIsEqualToMovieContentWhenCreate() {
        // arrange
        JsonObject movie = ContentFactory.createContent(TYPE_OF_CONTENT, CONTEXT_BOOK);
        // act
        // assert
        assertEquals("", movie.getAsString());
    }

    @Test
    public void assertThatMusicContentFromScraperIsEqualToMusicContentWhenCreated() {
        // arrange
        JsonObject music = ContentFactory.createContent(TYPE_OF_CONTENT, CONTEXT_BOOK);
        // act
        // assert
        assertEquals("", music.getAsString());
    }
}

