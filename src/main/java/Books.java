import java.util.List;

public class Books extends AbstractContent {

    //Class Books which inherits from AbstractContent.

    //Publisher
    //ISBN

    private String publisher;
    private String isbn;
    private List<String> authors;

    public Books(String context) {

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

}
