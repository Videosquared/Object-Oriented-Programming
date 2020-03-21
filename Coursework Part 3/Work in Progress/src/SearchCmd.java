import java.util.List;
import java.util.Objects;

public class SearchCmd extends LibraryCommand {

    private String searchValue;

    public SearchCmd(String argInput) {
        super(CommandType.SEARCH, argInput);
    }

    @Override
    protected boolean parseArguments(String argumentInput) {
        Objects.requireNonNull(argumentInput, "Given argumentInput must not be null.");
        boolean output = false;

        if (!argumentInput.isEmpty() && isOneWord(argumentInput)) {
            searchValue = argumentInput;
            output = true;
        }

        return output;
    }

    @Override
    public void execute(LibraryData data) {
        Objects.requireNonNull(data, "Given data must not be null.");
        List<BookEntry> books = data.getBookData();
        int counter = 0;

        for (BookEntry book : books) {
            //System.out.println(book.getTitle().toLowerCase());
            if (book.getTitle().toLowerCase().contains(searchValue.toLowerCase())) {
                System.out.println(book.getTitle());
                counter++;
            }
        }
        if (counter == 0) {
            System.out.println("No hits found for search term: " + searchValue);
        }
    }

    public static boolean isOneWord(String value) {
        return value.lastIndexOf(" ") == -1;
    }
}
