import java.util.List;
import java.util.Objects;

/**
 * Search command searches through the all the book entries and print the
 * the matching results
 */
public class SearchCmd extends LibraryCommand {

    /** This will hold the value to be searched for */
    private String searchValue;

    /**
     * Create an search command
     *
     * @param argInput is expected to hold the value to be searched for within all book entries
     */
    public SearchCmd(String argInput) {
        super(CommandType.SEARCH, argInput);
    }

    /**
     * This will check if the argument of the command is just one word
     * and it is not empty and if so it will be parsed into the instance field
     *
     * @param argumentInput argument input for this command
     * @return true if its not empty and its one word else false
     */
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

    /**
     * This will run the command. It will search through all the books and
     * determine if the it has the search value in the title of that book,
     * if so print the book title
     *
     * @param data book data to be considered for command execution.
     */
    @Override
    public void execute(LibraryData data) {
        Objects.requireNonNull(data, "Given data must not be null.");
        List<BookEntry> books = data.getBookData();
        int counter = 0;

        for (BookEntry book : books) {
            if (book.getTitle().toLowerCase().contains(searchValue.toLowerCase())) {
                System.out.println(book.getTitle());
                counter++;
            }
        }
        if (counter == 0) {
            System.out.println("No hits found for search term: " + searchValue);
        }
    }

    /**
     * Check if the given string is one word or not
     *
     * @param value string to be checked
     * @return true if its one word else false
     */
    private boolean isOneWord(String value) {
        return value.lastIndexOf(" ") == -1;
    }
}
