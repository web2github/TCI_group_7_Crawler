public abstract class AbstractContent {

    // We created an abstract class called AbstractContent which contains the fields of the website.

    //Genre
    //Format
    //Year
    //Director

    private String genre;
    private String format;
    private int year;
    private String director;
    private String title;


    //Method which returns the genre.
    public String getGenre() {
        return genre;
    }


    // Method which returns the format.
    public String getFormat() {
        return format;
    }


    //Method which returns the year.
    public int getYear() {
        return year;
    }


    public String getDirector() {
        return director;
    }

    public String getTitle() {
        return title;
    }
}
