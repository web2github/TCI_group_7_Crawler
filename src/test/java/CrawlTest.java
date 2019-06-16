import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

//CONTENTS OF CRAWLER CLASS, CHANGE NAME
public class CrawlTest {

    private static final String VALID_URL = "https://validurl.com";
    private static final String VALID_PAGE_CONTENT = "Valid content";
    private static final double VALID_TIME_ELAPSED = 12.12;

    @Test
    public void contentOfTheWebPageIsNotNullAndNotEmptyWhenTheUrlIsValid(){
        // ARRANGE
        Crawler crawler = new Crawler(VALID_URL);
        //ACT
        //ASSERT
        //assertEquals(VALID_PAGE_CONTENT, crawler.getPageLink(VALID_URL));
        assertThat(VALID_URL,is(notNullValue()));

    }
    @Test (expected = IllegalArgumentException.class)
    public void illegalArgumentExceptionThrownWhenURLIsInvalidOrNoneExisting() {
        //ARRANGE
        Crawler crawler = new Crawler("https//:validurl.com");
        //ACT
        //ASSERT

    }


    @Test (expected = IllegalArgumentException.class)
    public void TimeElapsedIsInDouble() {
        //ARRANGE
        Crawler crawler = new Crawler(VALID_URL);
        //ACT
        //ASSERT
    }

    @Test (expected = IllegalArgumentException.class)
    public void TimeElapsedIsNotNegative(){
        //ARRANGE
        Crawler crawler = new Crawler(VALID_URL);
        //ACT
        //ASSERT
    }

    /* Main functionality*/
    @Test
    public void whenCrawlingOnePageTheNumberOfUniqueWebpagesIsOne() {
        //ARRANGE
        Crawler crawler = new Crawler(VALID_URL);
        //ACT
        //ASSERT
        assertEquals("site has more than one page",1,crawler.getNumberOfPages());
    }

    @Test
    public void whenCrawlingMultiplePagesTheNumberOfPagesIsNotNullOrOne() {
        //ARRANGE
        Crawler crawler = new Crawler(VALID_URL);
        //ACT
        //ASSERT
        assertThat(crawler.getNumberOfPages(), greaterThan(1));
    }

    /* Main functionality*/
    @Test
    public void keepTrackOfNumberOfPageVisitedWhenCrawlingTheEntireWebsite() {
        //ARRANGE
        Crawler crawler = new Crawler(VALID_URL);
        int min = 0;
        int max = 100;
        //ACT
        //ASSERT
        assertTrue(min <= crawler.getNumberOfPages() && crawler.getNumberOfPages() <= max);
    }


    /*Main functionality*/


    @Test
    public void depthResultsIsZeroWhenCrawlingThroughOnePage() {
        //ARRANGE
        Crawler crawler = new Crawler(VALID_URL);
        //ACT
        crawler.crawl(VALID_URL);
        //ASSERT
        assertEquals("site has more than one page",1,crawler.getNumberOfPages());
        assertEquals(0,crawler.getDepth());
    }



    @Test
    public void timeElapsedAfterCrawlingIsNotNull() {
        //ARRANGE
        Crawler crawler = new Crawler(VALID_URL);
        //ACT
        crawler.crawl(VALID_URL);
        //ASSERT
        assertThat(crawler.getTimeElapsed(), is(notNullValue()));


    }


}
