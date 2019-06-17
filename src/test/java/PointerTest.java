import org.junit.Before;
import org.junit.Test;

public class PointerTest {
    private Crawler crawler;
    private Scraper scraper;

    @Before
    public void setUp() {
        crawler = new Crawler("");
        scraper = new Scraper();
    }

    @Test
    public void contentOfTheWebPageIsNotNullAndNotEmptyWhenTheUrlIsValid() {
        // ARRANGE
        //ACT
        //ASSERT
//        assertEquals(VALID_PAGE_CONTENT, crawler.addToPageLinks(VALID_URL));
    }
}
