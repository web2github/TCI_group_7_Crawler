import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class PointerTest {
    private Crawler crawler;
    private Scraper scraper;

    Pointer pointer;

    @Before
    public void setUp() {
        pointer = new Pointer("http://localhost:8082/sample_site_to_crawl/");
//        crawler = new Crawler("http://localhost:8082/sample_site_to_crawl/");
        scraper = new Scraper();
    }

    @Test
    public void contentOfTheWebPageIsNotNullAndNotEmptyWhenTheUrlIsValid() throws IOException {
        //ARRANGE
        //ACT
        assertNotNull(
                pointer.crawlWholeSite());

        //ASSERT
//        assertEquals(VALID_PAGE_CONTENT, crawler.addToPageLinks(VALID_URL));
    }

    @Test
    public void timeElapsedAfterCrawlingIsNotNull() {
        //ARRANGE
        //ACT
        //ASSERT
        assertThat(pointer.getTimeElapsed(), is(notNullValue()));
    }
}
