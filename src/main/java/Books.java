import java.util.List;

public class Books extends AbstractContent {

    //Class Books which inherits from AbstractContent.

    //Publisher
    //ISBN

    private String publisher;
    private String isbn;
    private List<String> authors;

    //public Books(String publisher, String isbn, List<String>authors) {
        //this.publisher=publisher;
        //this.isbn=isbn;
        //this.authors=authors;
    //}

    public Books(String context) {
        super();
    }

    //Method to return the authors of the book.
    public List<String> getAuthors() {
        return authors;
    }

    //Method to return the publisher of the book.
    public String getPublisher() {

        return "Publisher:" + publisher;
    }


    //Method to return the ISBN.
    public String getISBN() {
        return "ISBN:" +  isbn;
    }


    public List<String> setAuthors()
    {
        this.authors=authors;
        return authors;
    }


    public String setPublisher(String publisher)
    {
        this.publisher=publisher;
        return publisher;
    }


    public String setISBN(String isbn)
    {
        this.isbn=isbn;
        return "ISBN:" +  isbn;
    }
}
