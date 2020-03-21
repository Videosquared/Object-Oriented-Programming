import java.util.List;
import java.util.Objects;

public class ListCmd extends LibraryCommand{

    private final String SHORT = "short";
    private final String LONG = "long";

    private String userInput;

    public ListCmd(String argInput) {
        super(CommandType.LIST, argInput);
    }

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

    public void printShort(List<BookEntry> books) {
        for (BookEntry book : books) {
            System.out.println(book.getTitle());
        }
    }

    public void printLong(List<BookEntry> books) {
        for (BookEntry book : books) {
            System.out.println(book.toString());
            System.out.println();
        }
    }

}
