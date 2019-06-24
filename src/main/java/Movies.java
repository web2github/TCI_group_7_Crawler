public class Movies extends AbstractContent {


    //Class Movies inherits from AbstractContent.
    private String writers;
    private String stars;

    //public Movies(String writers, String stars) {
      //  this.writers=writers;
        //this.stars = stars;
    //}

    public Movies(String context) {
        super();
    }


    //Method to return the Writers of the movie.
    public String getWriters(){
        return writers;
    }

    //Method to return the Stars of the movie.
    public String getStars(){
        return stars;
    }

    public String setWriters(String writers)
    {
        this.writers=writers;
        return writers;
    }

    public String setStars(String stars)
    {
        this.stars=stars;
        return stars;
    }
}
