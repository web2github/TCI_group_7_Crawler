import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.IOException;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class PointerTest {
    private final String CRAWL_KEYWORD = "The Very Thought of You";
    private final String CRAWL_INFO_TEST = "{time_elapse:53.0,unique_page:15,total_page:17}";
    private Pointer pointer;

    @Before
    public void setUp() {
        pointer = new Pointer("http://localhost:8082/sample_site_to_crawl/");

    }

    @Test
    public void resultFoundAfterContentSearched() {
        //ARRANGE
        //ACT
        //ASSERT
        assertNotNull(
                pointer.crawlForAContent("The Clean Coder: A Code of Conduct for Professional Programmers")
        );
    }

    @Test
    public void showInfoOfCrawler() {
        //ARRANGE
        //ACT
        System.out.println(pointer.crawlForAContent(CRAWL_KEYWORD));
        pointer.getCrawlerInfo();
        //ASSERT
        assertEquals(CRAWL_INFO_TEST, pointer.getCrawlerInfo());
    }

    @Test
    public void timeElapsedAfterCrawlingIsNotNullAndMoreThanZero() {
        //ARRANGE
        //ACT
        pointer.crawlWholeSite();
        //ASSERT
        assertThat(pointer.getTimeElapsed(), is(notNullValue()));
        assertTrue(pointer.getTimeElapsed() > 0);
    }

}
