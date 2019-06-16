
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ScraperTest {
    private String TYPE_OF_CONTENT;
    private String CONTEXT_BOOK;
    private
    JsonParser jsonParser;

    @Before
    public void setUp() {
        jsonParser = new JsonParser();
        TYPE_OF_CONTENT = "";
        CONTEXT_BOOK = "The Clean Coder: A Code of Conduct for Professional Programmers";
    }

    //CONTENTS OF SCRAPER CLASS, CHANGE NAME

    /* 1.get content from Scraper.class
     *  2.search through content
     *  3.return results to the user
     * */

    /*Main functionality*/
    @Test
    public void shouldReturnEmptyStringWhenCrawlResultsIsEmpty() {
//        Scraper mockedScraper = mock(Scraper.class);
//        when(mockedScraper.getContentAsString()).thenReturn("");

    }

    @Test
    public void assertThatBookContentFromScraperIsEqualToBookContentWhenCreate() {
        // arrange
        JsonObject book = ContentFactory.createContent(TYPE_OF_CONTENT, CONTEXT_BOOK);
        // act
        // assert
        assertEquals("", book.getAsString());
    }

    @Test
    public void assertThatMovieContentFromScraperIsEqualToMovieContentWhenCreate() {

    }

    @Test
    public void assertThatMusicContentFromScraperIsEqualToMusicContentWhenCreated() {

    }
}

