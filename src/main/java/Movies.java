public class Movies extends AbstractContent {


    //Class Movies inherits from AbstractContent.
    private String writers;
    private String stars;

    public Movies(String context) {

    }


    //Method to return the Writers of the movie.
    public String getWriters(){
        return writers;
    }

    //Method to return the Stars of the movie.
    public String getStars(){
        return stars;
    }
}
