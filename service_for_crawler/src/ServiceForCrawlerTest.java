import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

public class ServiceForCrawlerTest {
    @Test
    public void testConnection(){
        ServiceForCrawler serviceForCrawler = new ServiceForCrawler();
        serviceForCrawler.getAll();
    }

}
