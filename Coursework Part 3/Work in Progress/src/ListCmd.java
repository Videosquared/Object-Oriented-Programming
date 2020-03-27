import java.util.List;
import java.util.Objects;

public class ListCmd extends LibraryCommand{

    /** This is to hold the format of the short to be compared to*/
    private final String SHORT = "short";
    /** This is to hold the format of the long to be compared to*/
    private final String LONG = "long";

    /** This holds the user's choice of what type of list the selected*/
    private String userInput;

    /**
     * Create an List command
     *
     * @param argInput is expected to hold the the choice of long or short chosen by the user
     */
    public ListCmd(String argInput) {
        super(CommandType.LIST, argInput);
    }

    /**
     * This will parse the given argument given by the user and check if it is
     * long or short, if so parse it to the instance field
     *
     * @param argumentInput argument input for this command
     * @return true if the user chose either long or short else false
     */
    @Override
    protected boolean parseArguments(String argumentInput) {
        Objects.requireNonNull(argumentInput, "Given argumentInput must not be null.");
        boolean output = false;

        if (argumentInput.equals(LONG) ) {
            userInput = LONG;
            output = true;
        } else if (argumentInput.equals(SHORT) || argumentInput.isEmpty()) {
            userInput = SHORT;
            output = true;
        }

        return output;
    }

    /**
     * This will execute the list command by printing out the correct information for
     * short or long selected by the user
     *
     * @param data book data to be considered for command execution.
     */
    @Override
    public void execute(LibraryData data) {
        Objects.requireNonNull(data, "Error: given data must not be null.");
        List<BookEntry> books = data.getBookData();
        System.out.println(books.size() + " books in library:");
        if (userInput.equals(SHORT)) {
            printShort(books);
        } else {
            printLong(books);
        }
    }

    /**
     * This will print the title of each book
     *
     * @param books list of book entries
     */
    public void printShort(List<BookEntry> books) {
        for (BookEntry book : books) {
            System.out.println(book.getTitle());
        }
    }

    /**
     * This will call the toSting() of BookEntry and print it for each book
     *
     * @param books list of all the book entries
     */
    public void printLong(List<BookEntry> books) {
        for (BookEntry book : books) {
            System.out.println(book.toString());
            System.out.println();
        }
    }

}
