import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
/*
 * Gets and creates an JsonObject using the AbstractContent class.
 * Uses enumeration to obtain the create object.
 *
 *@author Robbert van den Berg
 * */
public class ContentFactory {
    private static final JsonParser parser = new JsonParser();
    private static JsonObject jsonObject;
    private String typeOfContent;
    private String context;

    private ContentFactory() {
    }

    public static JsonObject createContent(final String typeOfContent, final String context) {

        if (typeOfContent.equals(ContentEnum.BOOK)) {
            Books books = new Books(context);
            jsonObject = parser.parse(books.getTitle()
                    + ContentEnum.BOOK
                    + books.getGenre()
                    + books.getFormat()
                    + books.getYear()
                    + books.getAuthors()
                    + books.getISBN()
                    + books.getPublisher()
            ).getAsJsonObject();
            return jsonObject;
        }
        if (typeOfContent.equals(ContentEnum.MOVIE)) {
            return jsonObject;
        }
        if (typeOfContent.equals(ContentEnum.MUSIC)) {
            return jsonObject;
        } else {
            jsonObject = parser.parse("").getAsJsonObject();
            return jsonObject;
        }
    }


}
