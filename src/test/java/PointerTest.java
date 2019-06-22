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
        scraper = new Scraper();
    }

    @Test
    public void showInfoOfCrawler() {
        pointer.crawlWholeSite();
        pointer.getCrawlerInfo();
    }

    @Test
    public void resultFoundAfterContentSearched() throws IOException {
        assertNotNull(
                pointer.crawlForAContent("books", "The Clean Coder: A Code of Conduct for Professional Programmers")
        );
        System.out.println(pointer.crawlForAContent("books", "The Clean Coder: A Code of Conduct for Professional Programmers"));
    }

    @Test
    public void contentOfTheWebPageIsNotNullAndNotEmptyWhenTheUrlIsValid() throws IOException {
        //ARRANGE
        //ACT
        //ASSERT
        assertNotNull(
                pointer.crawlWholeSite());
    }

    @Test
    public void timeElapsedAfterCrawlingIsNotNull() {
        //ARRANGE
        //ACT
        //ASSERT
        assertThat(pointer.getTimeElapsed(), is(notNullValue()));
    }
}
