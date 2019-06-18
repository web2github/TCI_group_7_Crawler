import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/crawl")
public class ServiceForCrawler {
    Pointer pointer = new Pointer(""); // TODO: add url
    @GET
    @Path("/all")
    @Produces("text/plain")
//    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        pointer.crawlWholeSite();
        return Response
                .status(200)
                .entity("").build();
    }
    @GET
    @Path("/query")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSpecificContent(
            @QueryParam("type") String type,
            @QueryParam("keyword") String keyword) {
        pointer.crawlForAContent(type, keyword);
        return Response
                .status(200)
                .entity("").build();

    }
    @GET
    @Path("/info")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInfoForContent() {
        pointer.getCrawlerInfo();
        return Response
                .status(200)
                .entity("").build();

    }
}
