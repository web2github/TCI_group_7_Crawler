import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

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
//        assertEquals(VALID_PAGE_CONTENT, crawler.getPageLink(VALID_URL));
    }
}
