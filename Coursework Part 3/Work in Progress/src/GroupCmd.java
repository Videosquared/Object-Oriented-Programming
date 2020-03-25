import java.awt.print.Book;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class GroupCmd extends LibraryCommand{

    private final String TITLE = "TITLE";
    private final String AUTHOR = "AUTHOR";

    private String type;


    public GroupCmd(String argInput) {
        super(CommandType.GROUP, argInput);
    }

    @Override
    protected boolean parseArguments(String argumentInput) {
        Objects.requireNonNull(argumentInput, "Given argument must not be null.");

        if (argumentInput.equals(TITLE)) {
            type = TITLE;
            return true;
        } else if (argumentInput.equals(AUTHOR)) {
            type = AUTHOR;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void execute(LibraryData data) {
        Objects.requireNonNull(data, "Given data must not be null.");
        List<BookEntry> books = data.getBookData();

        System.out.println("Grouped data by: " + type);
        if (type.equals(TITLE)) {
            groupByTitle(books);
        } else {
            groupByAuthor(books);
        }
    }

    public void groupByTitle(List<BookEntry> books) {
        Objects.requireNonNull(books, "Given books must not be null.");

        HashMap<Character, String> titleMap = new HashMap<>();
        for (BookEntry book : books) {
            titleMap.put(book.getTitle().charAt(0), book.getTitle());
        }
    }

    public void groupByAuthor(List<BookEntry> books) {
        Objects.requireNonNull(books, "Given books must not be null.");
    }
}
