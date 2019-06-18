import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

//CONTENTS OF CRAWLER CLASS, CHANGE NAME
public class CrawlTest {
    private Crawler crawler;
    private static final String VALID_URL = "http://localhost:8082/sample_site_to_crawl/";
    private static final String VALID_PAGE_CONTENT = "Valid content";
    private static final double VALID_TIME_ELAPSED = 12.12;

    @Before
    public void setUp() {

        crawler = new Crawler(VALID_URL);
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void illegalArgumentExceptionThrownWhenURLIsUnableToConnect() {
        //ARRANGE
        crawler = new Crawler("http://localhost:84416514");
        //ACT
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("The URL link is unable to connect");
        crawler.isConnect();
        //ASSERT
    }


    @Test
    public void TimeElapsedIsNotNegative() {
        //ARRANGE
        //ACT
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("Time elapsed must be positive");
        //ASSERT
    }

    /* Main functionality*/
    @Test
    public void whenCrawlingOnePageTheNumberOfUniqueWebpagesIsOne() throws IOException {
        //ARRANGE
        //ACT
        //ASSERT
        crawler.crawl(VALID_URL);
        assertEquals("site has more than one page", 1, crawler.getUniquePages());
    }

    @Test
    public void whenCrawlingMultiplePagesTheNumberOfPagesIsNotNullOrOne() throws IOException {
        //ARRANGE
        //ACT
        //ASSERT
        crawler.crawl(VALID_URL);

        assertThat(crawler.getNumberOfPages(), greaterThan(1));
    }

    /* Main functionality*/
    @Test
    public void keepTrackOfNumberOfPageVisitedWhenCrawlingTheEntireWebsite() {
        //ARRANGE
        int minPage = 0;
        int maxPage = 100;
        //ACT
        //ASSERT
        assertTrue(minPage <= crawler.getNumberOfPages() && crawler.getNumberOfPages() <= maxPage);
    }

    /*Main functionality*/
    @Test
    public void depthResultsIsZeroWhenCrawlingThroughOnePage() throws IOException {
        //ARRANGE
        //ACT
        crawler.crawl("");
        //ASSERT
        assertEquals("site has more than one page", 1, crawler.getNumberOfPages());
        assertEquals(0, crawler.getDepth());
    }


}
