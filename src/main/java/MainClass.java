public class MainClass {

    private String baseUrl = "localhost";

    private Crawler crawler = new Crawler(baseUrl);


    public static void main(String[] args) {


    }

        public void getData()
    {
        crawler.crawl(baseUrl);
    }

}
